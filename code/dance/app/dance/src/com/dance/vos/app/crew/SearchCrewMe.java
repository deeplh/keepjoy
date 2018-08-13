package com.dance.vos.app.crew;


import com.dance.entity.TblDanceCrew;
import com.dance.security.weixin.DanceUserHolder;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.pagination.JsonTagPaginationAction;
import jsontag.annotation.pagination.PaginationField;
import jsontag.annotation.validation.MyValidation;


@JtOnlineApi(describe="查询我发布的crew",name="")
@JsonTagAnnotation(actionValue="/search",namespace="app/crew/me",responseHideFieldArray = {"crewImg"})
@JsonTagPaginationAction(listBeanClass=TblDanceCrew.class
		,sql=" from TblDanceCrew  " +
		" where createUserId=:userId "
		,sqlTail = " order by id desc ")
public class SearchCrewMe {


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
