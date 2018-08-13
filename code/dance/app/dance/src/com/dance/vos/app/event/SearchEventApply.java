package com.dance.vos.app.event;


import com.dance.DanceConstant;
import com.dance.entity.TblDanceUserEventRelation;
import com.dance.security.weixin.DanceUserHolder;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.pagination.JsonTagPaginationAction;
import jsontag.annotation.pagination.PaginationField;
import jsontag.annotation.validation.MyValidation;


@JtOnlineApi(describe="查询我收到的加入我发起的活动的申请",name="")
@JsonTagAnnotation(actionValue="/search",namespace="app/event/apply",responseHideFieldArray = {"eventImg"})
@JsonTagPaginationAction(listBeanClass=TblDanceUserEventRelation.class
		,sql=" select new TblDanceUserEventRelation(tduer.id, tduer.eventId,tde.name, tde.img," +
		" tdu.aka, tdu.avatarUrl,tduer.userId,tduer.createDateTime) from TblDanceUserEventRelation tduer," +
		" TblDanceEvent tde,TblDanceUser tdu where tduer.userId=tdu.userId and tduer.eventId=tde.id" +
		" and tde.createUserId=:createUserId " +
		" and  tduer.status= "+DanceConstant.STATUS_0_ING ,sqlTail = " order by tduer.id desc ")
public class SearchEventApply {


	@MyValidation(exceptionDesc="name格式异常",maxlength = 30)
	@PaginationField(sql=" and tde.name like:name ")
	private String name;

	@PaginationField()
	private Integer createUserId;
	

	public String getName() {
			return "%"+this.name+"%";
	}
		public void setName(String name) {
			this.name = name;
		}

	public Integer getCreateUserId() {
		return DanceUserHolder.getDanceUser().getUserId();
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
}
