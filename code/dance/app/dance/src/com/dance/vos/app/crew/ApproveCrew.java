package com.dance.vos.app.crew;


import com.dance.DanceConstant;
import com.dance.entity.TblDanceCrew;
import com.dance.entity.TblDanceUserCrewRelation;
import com.dance.security.DanceUser;
import com.dance.security.weixin.DanceUserHolder;
import com.dance.service.DanceCommonService;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.validation.MyValidation;
import jsontag.dao.JsonTagTemplateDaoImpl;
import jsontag.exception.JsonTagException;
import jsontag.interf.IDoService;
import org.jtsecurity.proxy.RedisSsoProxy;

@JtOnlineApi(describe="",name="")
@JsonTagAnnotation(actionValue="/approve",namespace="app/crew")
public class ApproveCrew extends JsonTagTemplateDaoImpl implements IDoService<String> {


	
	@MyValidation(exceptionDesc="id异常",required = true)
	private Integer id;

	@MyValidation(exceptionDesc="status异常",required = true,maxlength = 1)
	private String status;


	@Override
	public String doService() throws Exception {
		TblDanceUserCrewRelation t=get(TblDanceUserCrewRelation.class,this.id);

		if(null==t)throw new JsonTagException("id数据异常");

		if(!DanceConstant.STATUS_0_ING.equals(t.getStatus()))throw new JsonTagException("数据status异常");

		t.setStatus(this.status);
		t.setApproveDateTime(DanceCommonService.getNow());
		t.setApproveUserId(DanceUserHolder.getDanceUser().getUserId());
		update(t);

		DanceUser du=DanceUserHolder.getDanceUser();
		DanceCommonService.setDanceUserCrews(du.getDanceUserInfo());
		RedisSsoProxy.setUserToRedis(du);

		return null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
