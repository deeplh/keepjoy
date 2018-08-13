package com.dance.vos.app.studio;


import com.dance.entity.TblDanceStudio;
import com.dance.security.weixin.DanceUserHolder;
import com.dance.service.DanceCommonService;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.validation.MyValidation;
import jsontag.dao.JsonTagTemplateDaoImpl;
import jsontag.exception.JsonTagException;
import jsontag.interf.IDoService;


@JtOnlineApi(describe="修改studio的状态",name="")
@JsonTagAnnotation(actionValue="/approve",namespace="app/studio/create")
public class ApproveCreateStudio extends JsonTagTemplateDaoImpl implements IDoService<String> {


	
	@MyValidation(exceptionDesc="id异常",required = true)
	private Integer id;

	@MyValidation(exceptionDesc="status异常",required = true,maxlength = 1)
	private String status;


	@Override
	public String doService() throws Exception {
		TblDanceStudio t=get(TblDanceStudio.class,this.id);

		if(null==t)throw new JsonTagException("id数据异常");

		t.setStatus(this.status);
		t.setApproveDateTime(DanceCommonService.getNow());
		t.setApproveUserId(DanceUserHolder.getDanceUser().getUserId());
		update(t);
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
