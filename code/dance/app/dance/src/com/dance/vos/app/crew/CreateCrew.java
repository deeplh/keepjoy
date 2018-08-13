package com.dance.vos.app.crew;


import com.dance.DanceConstant;
import com.dance.entity.TblDanceCrew;
import com.dance.entity.TblDanceEventAttach;
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
@JsonTagAnnotation(actionValue="/create",namespace="app/crew")
public class CreateCrew extends JsonTagTemplateDaoImpl implements IDoService<Object>{



	@JsonTagFromJsonString()
	private  TblDanceCrew crew;

	@JtOnlineApi(describe="",name="")
	@JsonTagFromJsonString(jsonToListClass=FileAttrBean.class)
	private List<FileAttrBean> fileAttrBeanList;



	@Override
	public Object doService() throws Exception {
		TblDanceCrew obj=new TblDanceCrew();
		BeanUtils.copyProperties(crew,obj);
		obj.setCreateDateTime(DanceCommonService.getNow());
		obj.setCreateUserId(DanceUserHolder.getDanceUser().getUserId());
		obj.setStatus(DanceConstant.STATUS_0_ING);



		if (null!=fileAttrBeanList && fileAttrBeanList.size()>0) {
			obj.setImg(fileAttrBeanList.get(0).getNewName());
		}

		save(obj);
		return "创建成功";
	}


	public List<FileAttrBean> getFileAttrBeanList() {
		return fileAttrBeanList;
	}

	public void setFileAttrBeanList(List<FileAttrBean> fileAttrBeanList) {
		this.fileAttrBeanList = fileAttrBeanList;
	}

	public TblDanceCrew getCrew() {
		return crew;
	}

	public void setCrew(TblDanceCrew crew) {
		this.crew = crew;
	}
}
