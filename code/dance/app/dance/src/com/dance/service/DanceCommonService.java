package com.dance.service;

import com.dance.DanceConstant;
import com.dance.entity.TblDanceEventAttach;
import com.dance.entity.TblDanceUser;
import jsontag.JsonTagContext;
import jsontag.bean.handler.file.upload.FileAttrBean;
import jsontag.exception.JsonTagException;
import jsontag.factory.JsonTagConfigFactory;
import jsontag.util.UrlUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.Date;
import java.util.List;

public class DanceCommonService {


    public static final Date getNow(){
        return new Date();
    }

    public static final String getUploadRootUrl(){
        return UrlUtil.addLastSlash(JsonTagContext.getHttpBeginUrl())+UrlUtil.addLastSlash(JsonTagConfigFactory.getStringConfigByKey("upload-file"));
    }

    public static final String getImgDis(String img,String type,String yearMonth){
        if(StringUtils.isNotEmpty(img)){
            String rootFilePath= DanceCommonService.getUploadRootUrl();
            if(DanceConstant.UPLOAD_IMG_TYPE_EVENT.equals(type)){
                return rootFilePath+type+File.separator+yearMonth+File.separator+img;
            }else{
                return  rootFilePath+type+File.separator+img;
            }

        }else {
            return null;
        }
    }

    public static final String getImgDisMin(String imgDis){
        if(StringUtils.isEmpty(imgDis)){
            return null;
        }else{
            return UrlUtil.addAttachUrlBeforeLastDot(imgDis,"_"+DanceConstant.ATTACH_MIN_IMG);
        }
    }

    public static final String getImgDisMin(String img,String type,String yearMonth){
        return getImgDisMin(getImgDis(img,type,yearMonth));

    }

    public static final TblDanceEventAttach saveTblDanceEventAttach(List<FileAttrBean> fileAttrBeanList, Integer id, String type){
        TblDanceEventAttach tdea = null;
        TblDanceEventAttach tdeaFirst = null;
        if (null!=fileAttrBeanList && fileAttrBeanList.size()>0) {


            for (FileAttrBean fab : fileAttrBeanList) {
                tdea = new TblDanceEventAttach();

                tdea.setSize(fab.getSize());
                tdea.setEventId(id);
                tdea.setType(type);
                tdea.setImg(fab.getNewName());
                String tmp = FilenameUtils.getFullPathNoEndSeparator(fab.getAbsolutePath());
                tdea.setYearMonth(tmp.substring(tmp.length() - 6, tmp.length()));


                JsonTagContext.getJsonTagDao().save(tdea);

                if(null==tdeaFirst)tdeaFirst=tdea;
            }

        }
        return tdeaFirst;
    }

    public static final void setDanceUser(TblDanceUser tdu){
        if(null==tdu){
            throw new JsonTagException("userId数据异常");
        }
        setDanceUserCrews(tdu);
        setDanceUserStudios(tdu);
        setDanceUserStyles(tdu);
    }

    public static final void setDanceUserCrews(TblDanceUser tdu){
        tdu.setCrews(JsonTagContext.getJsonTagDao().find("select new TblDanceUserCrewRelation(tducr.id,tdc.id,tdc.name,tdc.img) from TblDanceCrew tdc,TblDanceUserCrewRelation tducr where tdc.id=tducr.crewId and tducr.userId=? and tducr.status=? ",tdu.getUserId(),DanceConstant.STATUS_2_YES));
    }

    public static final void setDanceUserStudios(TblDanceUser tdu){
        tdu.setStudios(JsonTagContext.getJsonTagDao().find("select new TblDanceUserStudioRelation(tdusr.id,tds.id,tds.name,tds.img)  from TblDanceStudio tds,TblDanceUserStudioRelation tdusr where tds.id=tdusr.studioId and tdusr.userId=? and tdusr.status=? ",tdu.getUserId(),DanceConstant.STATUS_2_YES));
    }

    public static final void setDanceUserStyles(TblDanceUser tdu){
        tdu.setStyles(JsonTagContext.getJsonTagDao().find("select new TblDanceUserStyleRelation(tdusr.id,tds.id,tds.name,tds.img,tds.initial)  from TblDanceStyle tds,TblDanceUserStyleRelation tdusr where tds.id=tdusr.styleId and tdusr.userId=? ",tdu.getUserId()));
    }
}
