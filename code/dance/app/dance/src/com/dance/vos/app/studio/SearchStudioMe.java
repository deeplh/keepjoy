package com.dance.vos.app.studio;


import com.dance.entity.TblDanceStudio;
import com.dance.entity.TblDanceUserStudioRelation;
import com.dance.security.weixin.DanceUserHolder;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.pagination.JsonTagPaginationAction;
import jsontag.annotation.pagination.PaginationField;
import jsontag.annotation.validation.MyValidation;


@JtOnlineApi(describe="查询我发布的studio",name="")
@JsonTagAnnotation(actionValue="/search",namespace="app/studio/me",responseHideFieldArray = {"studioImg"})
@JsonTagPaginationAction(listBeanClass=TblDanceStudio.class
		,sql=" from TblDanceStudio  " +
		" where createUserId=:userId "
		,sqlTail = " order by id desc ")
public class SearchStudioMe {


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
