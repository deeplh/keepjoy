package com.dance.vos.app.studio;


import com.dance.entity.TblDanceUserCrewRelation;
import com.dance.entity.TblDanceUserStudioRelation;
import com.dance.security.weixin.DanceUserHolder;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.pagination.JsonTagPaginationAction;
import jsontag.annotation.pagination.PaginationField;
import jsontag.annotation.validation.MyValidation;


@JtOnlineApi(describe="查询我发的入团申请",name="")
@JsonTagAnnotation(actionValue="/search",namespace="app/studio/apply/me",responseHideFieldArray = {"studioImg"})
@JsonTagPaginationAction(listBeanClass=TblDanceUserStudioRelation.class
		,sql=" select new TblDanceUserStudioRelation(tdusr.id, tdusr.studioId,tds.name, tds.img," +
		" tdusr.userId,tdusr.createDateTime,tdusr.status) from TblDanceUserStudioRelation tdusr," +
		" TblDanceStudio tds " +
		" where tdusr.studioId=tds.id and tdusr.userId=:userId  ",sqlTail = " order by tdusr.id desc")
public class SearchStudioApplyMe {


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
