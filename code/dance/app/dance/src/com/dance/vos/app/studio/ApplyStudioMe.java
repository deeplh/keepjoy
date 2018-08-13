package com.dance.vos.app.studio;


import com.dance.DanceConstant;
import com.dance.entity.TblDanceStudio;
import com.dance.entity.TblDanceUserStudioRelation;
import com.dance.security.weixin.DanceUserHolder;
import com.dance.service.DanceCommonService;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.validation.MyValidation;
import jsontag.dao.JsonTagTemplateDaoImpl;
import jsontag.exception.JsonTagException;
import jsontag.interf.IDoService;

@JtOnlineApi(describe="",name="")
@JsonTagAnnotation(actionValue="/apply",namespace="app/studio/me")
public class ApplyStudioMe extends JsonTagTemplateDaoImpl implements IDoService<String> {


	
	@MyValidation(exceptionDesc="studioId异常",required = true,getFrom = TblDanceStudio.class)
	private Integer studioId;
	


	@Override
	public String doService() throws Exception {
		TblDanceUserStudioRelation t=findObjectFromListByHql(TblDanceUserStudioRelation.class,
				" from TblDanceUserStudioRelation where studioId=? and userId=? and (status=? or status=? or status is null or status='') ",
				this.studioId,DanceUserHolder.getDanceUser().getUserId(),DanceConstant.STATUS_0_ING,DanceConstant.STATUS_2_YES);
		if(null!=t)throw new JsonTagException("审批中,请勿重复申请");

		t=new TblDanceUserStudioRelation();
		t.setStudioId(studioId);
		t.setUserId(DanceUserHolder.getDanceUser().getUserId());
		t.setCreateDateTime(DanceCommonService.getNow());
		t.setStatus(DanceConstant.STATUS_0_ING);
		t.setCreateUserId(DanceUserHolder.getDanceUser().getUserId());
		save(t);
		return null;
	}

	public Integer getStudioId() {
		return studioId;
	}

	public void setStudioId(Integer studioId) {
		this.studioId = studioId;
	}
}
