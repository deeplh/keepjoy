package com.dance.vos.app.crew;


import com.dance.DanceConstant;
import com.dance.entity.TblDanceUserCrewRelation;
import com.dance.security.weixin.DanceUserHolder;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.pagination.JsonTagPaginationAction;
import jsontag.annotation.pagination.PaginationField;
import jsontag.annotation.validation.MyValidation;


@JtOnlineApi(describe="查询所有的加入舞团申请",name="")
@JsonTagAnnotation(actionValue="/search",namespace="app/crew/apply",responseHideFieldArray = {"crewImg"})
@JsonTagPaginationAction(listBeanClass=TblDanceUserCrewRelation.class
		,sql=" select new TblDanceUserCrewRelation(tducr.id, tducr.crewId,tdc.name, tdc.img," +
		" tdu.aka, tdu.avatarUrl,tducr.userId,tducr.createDateTime) from TblDanceUserCrewRelation tducr," +
		" TblDanceCrew tdc,TblDanceUser tdu where tducr.userId=tdu.userId and tducr.crewId=tdc.id" +
		" and  tducr.status= "+DanceConstant.STATUS_0_ING,sqlTail = " order by tducr.id desc " )
public class SearchCrewApply {


	@MyValidation(exceptionDesc="name格式异常",maxlength = 30)
	@PaginationField(sql=" and tdc.name like:name ")
	private String name;
	
	@MyValidation(exceptionDesc="crewId格式异常")
	@PaginationField(sql=" and tducr.crewId in :crewId ",dataTypeWhenWithIn=Integer.class)
	private String crewId;

	

	public String getName() {
			return "%"+this.name+"%";
	}
		public void setName(String name) {
			this.name = name;
		}

	public String getCrewId() {
		return DanceUserHolder.getDanceUser().getCrewIdsForRole();
	}

	public void setCrewId(String crewId) {
		this.crewId = crewId;
	}
}
