package com.dance.vos.app.event;


import com.dance.entity.TblDanceUserEventRelation;
import com.dance.security.weixin.DanceUserHolder;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.pagination.JsonTagPaginationAction;
import jsontag.annotation.pagination.PaginationField;
import jsontag.annotation.validation.MyValidation;


@JtOnlineApi(describe="查询我申请加入的活动",name="")
@JsonTagAnnotation(actionValue="/search",namespace="app/event/apply/me",responseHideFieldArray={"eventImg"})
@JsonTagPaginationAction(listBeanClass=TblDanceUserEventRelation.class,pageSize = 10
		,sql=" select new TblDanceUserEventRelation(tduer.id, tduer.eventId,tdc.name, tdc.img," +
		" tduer.userId,tduer.createDateTime,tduer.status) from TblDanceUserEventRelation tduer," +
		" TblDanceEvent tdc " +
		" where tduer.eventId=tdc.id and tduer.userId=:userId  ",sqlTail = " order by tduer.id desc")
public class SearchEventApplyMe {


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
