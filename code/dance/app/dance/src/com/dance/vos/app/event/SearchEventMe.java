package com.dance.vos.app.event;


import com.dance.entity.TblDanceEvent;
import com.dance.security.weixin.DanceUserHolder;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.pagination.JsonTagPaginationAction;
import jsontag.annotation.pagination.PaginationField;
import jsontag.annotation.validation.MyValidation;


@JtOnlineApi(describe="查询我发布的event",name="")
@JsonTagAnnotation(actionValue="/search",namespace="app/event/me",responseHideFieldArray = {"eventImg"})
@JsonTagPaginationAction(listBeanClass=TblDanceEvent.class
		,sql=" from TblDanceEvent  " +
		" where createUserId=:userId "
		,sqlTail = " order by id desc ")
public class SearchEventMe {


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
