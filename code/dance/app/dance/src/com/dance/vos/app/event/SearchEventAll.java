package com.dance.vos.app.event;


import com.dance.DanceConstant;
import com.dance.entity.TblDanceEvent;
import com.dance.entity.TblDanceUserEventRelation;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.pagination.JsonTagPaginationAction;
import jsontag.annotation.pagination.PaginationField;
import jsontag.annotation.validation.MyValidation;


@JtOnlineApi(describe="查询所有的活动申请,只有对应权限的可以看",name="")
@JsonTagAnnotation(actionValue="/search",namespace="app/event/all",responseHideFieldArray = {"eventImg"})
@JsonTagPaginationAction(listBeanClass=TblDanceEvent.class
		,sql="select new TblDanceEvent(tde.id,tde.name,tde.createUserId,tde.createDateTime," +
		" tde.address,tde.stageDate,tde.beginTime,tde.endTime, " +
		" tde.eventType,tde.maxPersonNum,tde.status," +
		" tdu.aka,tdu.avatarUrl) from TblDanceEvent tde,TblDanceUser tdu where tdu.userId=tde.createUserId ",
		sqlTail = " order by tde.id desc ")
public class SearchEventAll {

	@MyValidation(exceptionDesc="status格式异常",maxlength = 2)
	@PaginationField(sql=" and tde.status=:status ")
	private String status;

	@MyValidation(exceptionDesc="name格式异常",maxlength = 30)
	@PaginationField(sql=" and tde.name like:name ")
	private String name;


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
			return "%"+this.name+"%";
	}
		public void setName(String name) {
			this.name = name;
		}

}
