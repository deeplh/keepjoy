package com.dance.vos.app.event;


import com.dance.DanceConstant;
import com.dance.entity.TblDanceEvent;
import com.dance.entity.TblDanceEventAttach;
import com.dance.security.weixin.DanceUserHolder;
import com.dance.service.DanceCommonService;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.action.JsonTagFromJsonString;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.validation.MyValidation;
import jsontag.bean.handler.file.upload.FileAttrBean;
import jsontag.dao.JsonTagTemplateDaoImpl;
import jsontag.interf.IDoService;
import jsontag.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.List;

@JtOnlineApi(describe="",name="")
@JsonTagAnnotation(actionValue="/create",namespace="app/event")
public class CreateEvent extends JsonTagTemplateDaoImpl implements IDoService<Object> {


	@JsonTagFromJsonString()
	private TblDanceEvent event;

	@JtOnlineApi(describe="",name="")
	@JsonTagFromJsonString(jsonToListClass=FileAttrBean.class)
	private List<FileAttrBean> fileAttrBeanList;

	@MyValidation(exceptionDesc = "beginTime格式异常",length = 5)
	private String beginTime;

	@MyValidation(exceptionDesc = "endTime格式异常",length = 5)
	private String endTime;

	@Override
	public Object doService() throws Exception {
		TblDanceEvent obj = new TblDanceEvent();
		BeanUtils.copyProperties(event,obj);

		obj.setCreateDateTime(DanceCommonService.getNow());
		obj.setCreateUserId(DanceUserHolder.getDanceUser().getUserId());
		obj.setStatus(DanceConstant.STATUS_0_ING);


		if(StringUtils.isNotEmpty(this.beginTime)){
			obj.setBeginTime(DateUtil.stringToDateByDateFormat(this.beginTime,DateUtil.DATEFORMAT_HH_MM));
		}
		if(StringUtils.isNotEmpty(this.endTime)){
			obj.setEndTime(DateUtil.stringToDateByDateFormat(this.endTime,DateUtil.DATEFORMAT_HH_MM));
		}

		save(obj);

		TblDanceEventAttach tdea=DanceCommonService.saveTblDanceEventAttach(fileAttrBeanList,obj.getId(),DanceConstant.UPLOAD_IMG_TYPE_EVENT);

//		if(null!=tdea)obj.setImg(tdea.getImg());

		return "创建成功";
	}

	public List<FileAttrBean> getFileAttrBeanList() {
		return fileAttrBeanList;
	}

	public void setFileAttrBeanList(List<FileAttrBean> fileAttrBeanList) {
		this.fileAttrBeanList = fileAttrBeanList;
	}


	public TblDanceEvent getEvent() {
		return event;
	}

	public void setEvent(TblDanceEvent event) {
		this.event = event;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
