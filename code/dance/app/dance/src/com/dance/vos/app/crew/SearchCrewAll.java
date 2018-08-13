package com.dance.vos.app.crew;


import com.dance.entity.TblDanceCrew;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.pagination.JsonTagPaginationAction;
import jsontag.annotation.pagination.PaginationField;
import jsontag.annotation.validation.MyValidation;


@JtOnlineApi(describe="查询所有的创建crew申请,只有对应权限的可以看",name="")
@JsonTagAnnotation(actionValue="/search",namespace="app/crew/all",responseHideFieldArray = {"crewImg"})
@JsonTagPaginationAction(listBeanClass=TblDanceCrew.class
		,sql="select new TblDanceCrew(tdc.id,tdc.name,tdc.createUserId,tdc.createDateTime," +
		" tdc.status,tdu.aka,tdu.avatarUrl) from TblDanceCrew tdc,TblDanceUser tdu where tdu.userId=tdc.createUserId "
		,sqlTail = " order by tdc.id desc ")
public class SearchCrewAll {

	@MyValidation(exceptionDesc="status格式异常",maxlength = 2)
	@PaginationField(sql=" and tdc.status=:status ")
	private String status;

	@MyValidation(exceptionDesc="name格式异常",maxlength = 30)
	@PaginationField(sql=" and tdc.name like:name ")
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
