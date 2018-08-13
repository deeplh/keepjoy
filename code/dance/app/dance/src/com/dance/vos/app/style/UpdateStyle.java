package com.dance.vos.app.style;


import com.dance.DanceConstant;
import com.dance.entity.TblDanceEventAttach;
import com.dance.entity.TblDanceStudio;
import com.dance.entity.TblDanceStyle;
import com.dance.security.weixin.DanceUserHolder;
import com.dance.service.DanceCommonService;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.action.JsonTagFromJsonString;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.validation.MyValidation;
import jsontag.bean.handler.file.upload.FileAttrBean;
import jsontag.dao.JsonTagTemplateDaoImpl;
import jsontag.exception.JsonTagException;
import jsontag.interf.IDoService;
import org.apache.commons.lang.StringUtils;

import java.util.List;

@JtOnlineApi(describe="",name="")
@JsonTagAnnotation(actionValue="/update",namespace="app/style")
public class UpdateStyle extends JsonTagTemplateDaoImpl implements IDoService<Object>{

	@JtOnlineApi(describe="",name="")
	@MyValidation(exceptionDesc="id格式异常",required = true,maxlength = 11)
	private Integer id;



	@JtOnlineApi(describe="",name="")
	@JsonTagFromJsonString(jsonToListClass=FileAttrBean.class)
	private List<FileAttrBean> fileAttrBeanList;

	
	@JtOnlineApi(describe="",name="")
	@MyValidation(exceptionDesc="info格式异常",maxlength = 300)
	private String info;

	
	@Override
	public Object doService() throws Exception {

		if(id==null){
			throw new JsonTagException("id格式异常");
		}

		TblDanceStyle obj = get(TblDanceStyle.class,id);
		if(null==obj){
			throw new JsonTagException("id数据异常");
		}

		boolean isUpdate=false;
		if(StringUtils.isNotEmpty(info) && !info.equals(obj.getInfo())){
			obj.setInfo(info);
			isUpdate=true;
		}

		if (null!=fileAttrBeanList && fileAttrBeanList.size()>0) {
			obj.setImg(fileAttrBeanList.get(0).getNewName());
			isUpdate=true;
		}

		if(isUpdate)update(obj);

		return "修改成功";
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
