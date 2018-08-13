package com.dance.vos.app.style;


import com.dance.DanceConstant;
import com.dance.entity.TblDanceEventAttach;
import com.dance.entity.TblDanceStyle;
import com.dance.security.weixin.DanceUserHolder;
import com.dance.service.DanceCommonService;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.action.JsonTagFromJsonString;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.validation.MyValidation;
import jsontag.bean.handler.file.upload.FileAttrBean;
import jsontag.dao.JsonTagTemplateDaoImpl;
import jsontag.interf.IDoService;

import java.util.List;

@JtOnlineApi(describe="",name="")
@JsonTagAnnotation(actionValue="/create",namespace="app/style")
public class CreateStyle extends JsonTagTemplateDaoImpl implements IDoService<Object>{


	@JtOnlineApi(describe="",name="")
	@MyValidation(exceptionDesc="name格式异常",required = true,maxlength = 20)
	private String name;


	@JtOnlineApi(describe="",name="")
	@JsonTagFromJsonString(jsonToListClass=FileAttrBean.class)
	private List<FileAttrBean> fileAttrBeanList;

	
	@JtOnlineApi(describe="",name="")
	@MyValidation(exceptionDesc="info格式异常",maxlength = 300)
	private String info;

	
	@Override
	public Object doService() throws Exception {
		TblDanceStyle obj=new TblDanceStyle();
		 obj.setName(name);
		 obj.setInfo(info);
		 obj.setCreateDateTime(DanceCommonService.getNow());
		 obj.setCreateUserId(DanceUserHolder.getDanceUser().getUserId());
		obj.setInitial(name.substring(0,1).toUpperCase());
		if (null!=fileAttrBeanList && fileAttrBeanList.size()>0) {
			obj.setImg(fileAttrBeanList.get(0).getNewName());
		}

		save(obj);


		update(obj);
		return "创建成功";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FileAttrBean> getFileAttrBeanList() {
		return fileAttrBeanList;
	}

	public void setFileAttrBeanList(List<FileAttrBean> fileAttrBeanList) {
		this.fileAttrBeanList = fileAttrBeanList;
	}



	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}


}
