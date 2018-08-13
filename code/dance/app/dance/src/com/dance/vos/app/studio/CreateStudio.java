package com.dance.vos.app.studio;


import com.dance.DanceConstant;
import com.dance.entity.TblDanceEventAttach;
import com.dance.entity.TblDanceStudio;
import com.dance.security.weixin.DanceUserHolder;
import com.dance.service.DanceCommonService;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.action.JsonTagFromJsonString;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.bean.handler.file.upload.FileAttrBean;
import jsontag.dao.JsonTagTemplateDaoImpl;
import jsontag.interf.IDoService;
import org.springframework.beans.BeanUtils;

import java.util.List;

@JtOnlineApi(describe="",name="")
@JsonTagAnnotation(actionValue="/create",namespace="app/studio")
public class CreateStudio extends JsonTagTemplateDaoImpl implements IDoService<Object> {

	@JsonTagFromJsonString()
	private TblDanceStudio studio;


	@JtOnlineApi(describe = "", name = "")
	@JsonTagFromJsonString(jsonToListClass = FileAttrBean.class)
	private List<FileAttrBean> fileAttrBeanList;




	@Override
	public Object doService() throws Exception {
		TblDanceStudio obj = new TblDanceStudio();

		BeanUtils.copyProperties(studio, obj);
		obj.setCreateDateTime(DanceCommonService.getNow());
		obj.setCreateUserId(DanceUserHolder.getDanceUser().getUserId());
		obj.setStatus(DanceConstant.STATUS_0_ING);

		if (null!=fileAttrBeanList && fileAttrBeanList.size()>0) {
			obj.setImg(fileAttrBeanList.get(0).getNewName());
		}
		save(obj);

		return "创建成功";
	}

	public TblDanceStudio getStudio() {
		return studio;
	}

	public void setStudio(TblDanceStudio studio) {
		this.studio = studio;
	}

	public List<FileAttrBean> getFileAttrBeanList() {
		return fileAttrBeanList;
	}

	public void setFileAttrBeanList(List<FileAttrBean> fileAttrBeanList) {
		this.fileAttrBeanList = fileAttrBeanList;
	}
}

