package com.dance.vos.app.crew;


import com.dance.DanceConstant;
import com.dance.entity.TblDanceCrew;
import com.dance.entity.TblDanceUserCrewRelation;
import com.dance.security.weixin.DanceUserHolder;
import com.dance.service.DanceCommonService;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.validation.MyValidation;
import jsontag.dao.JsonTagTemplateDaoImpl;
import jsontag.exception.JsonTagException;
import jsontag.interf.IDoService;

@JtOnlineApi(describe="",name="")
@JsonTagAnnotation(actionValue="/apply",namespace="app/crew/me")
public class ApplyCrewMe extends JsonTagTemplateDaoImpl implements IDoService<String> {


	
	@MyValidation(exceptionDesc="crewId异常",required = true,getFrom = TblDanceCrew.class)
	private Integer crewId;
	


	@Override
	public String doService() throws Exception {
		TblDanceUserCrewRelation t=findObjectFromListByHql(TblDanceUserCrewRelation.class,
				" from TblDanceUserCrewRelation where crewId=? and userId=? and (status=? or status=? or status is null or status='') ",
				this.crewId,DanceUserHolder.getDanceUser().getUserId(),DanceConstant.STATUS_0_ING,DanceConstant.STATUS_2_YES);
		if(null!=t)throw new JsonTagException("审批中,请勿重复申请");

		 t=new TblDanceUserCrewRelation();
		t.setCrewId(crewId);
		t.setUserId(DanceUserHolder.getDanceUser().getUserId());
		t.setCreateDateTime(DanceCommonService.getNow());
		t.setStatus(DanceConstant.STATUS_0_ING);
		t.setCreateUserId(DanceUserHolder.getDanceUser().getUserId());
		save(t);
		return null;
	}

	public Integer getCrewId() {
		return crewId;
	}

	public void setCrewId(Integer crewId) {
		this.crewId = crewId;
	}
}
