package com.dance.vos.app.event;


import com.dance.DanceConstant;
import com.dance.entity.TblDanceEvent;
import com.dance.entity.TblDanceUserEventRelation;
import com.dance.security.weixin.DanceUserHolder;
import com.dance.service.DanceCommonService;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.validation.MyValidation;
import jsontag.dao.JsonTagTemplateDaoImpl;
import jsontag.exception.JsonTagException;
import jsontag.interf.IDoService;

@JtOnlineApi(describe="",name="")
@JsonTagAnnotation(actionValue="/apply",namespace="app/event/me")
public class ApplyEventMe extends JsonTagTemplateDaoImpl implements IDoService<String> {


	
	@MyValidation(exceptionDesc="eventId异常",required = true,getFrom = TblDanceEvent.class)
	private Integer eventId;
	


	@Override
	public String doService() throws Exception {
		TblDanceUserEventRelation t=findObjectFromListByHql(TblDanceUserEventRelation.class,
				" from TblDanceUserEventRelation where eventId=? and userId=? and (status=? or status=? or status is null or status='') ",
				this.eventId,DanceUserHolder.getDanceUser().getUserId(),DanceConstant.STATUS_0_ING,DanceConstant.STATUS_2_YES);
		if(null!=t)throw new JsonTagException("审批中,请勿重复申请");

		 t=new TblDanceUserEventRelation();
		t.setEventId(eventId);
		t.setUserId(DanceUserHolder.getDanceUser().getUserId());
		t.setCreateDateTime(DanceCommonService.getNow());
		t.setStatus(DanceConstant.STATUS_0_ING);
		t.setCreateUserId(DanceUserHolder.getDanceUser().getUserId());
		save(t);
		return null;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
}
