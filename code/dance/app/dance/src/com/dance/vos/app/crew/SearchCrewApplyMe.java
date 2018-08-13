package com.dance.vos.app.crew;


import com.dance.DanceConstant;
import com.dance.entity.TblDanceUserCrewRelation;
import com.dance.security.weixin.DanceUserHolder;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.pagination.JsonTagPaginationAction;
import jsontag.annotation.pagination.PaginationField;
import jsontag.annotation.validation.MyValidation;
import jsontag.module.net.JtHttp;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;


@JtOnlineApi(describe="查询我发的入团申请",name="")
@JsonTagAnnotation(actionValue="/search",namespace="app/crew/apply/me",responseHideFieldArray={"crewImg"})
@JsonTagPaginationAction(listBeanClass=TblDanceUserCrewRelation.class
		,sql=" select new TblDanceUserCrewRelation(tducr.id, tducr.crewId,tdc.name, tdc.img," +
		" tducr.userId,tducr.createDateTime,tducr.status) from TblDanceUserCrewRelation tducr," +
		" TblDanceCrew tdc " +
		" where tducr.crewId=tdc.id and tducr.userId=:userId  ",sqlTail = " order by tducr.id desc")
public class SearchCrewApplyMe {


	@MyValidation(required = true,exceptionDesc = "userId异常")
	@PaginationField
	private Integer userId;

	public Integer getUserId() {
		return DanceUserHolder.getDanceUser().getUserId();
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


}
