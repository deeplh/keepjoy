package com.dance.vos.app.studio;


import com.dance.entity.TblDanceStudio;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.pagination.JsonTagPaginationAction;
import jsontag.annotation.pagination.PaginationField;
import jsontag.annotation.validation.MyValidation;


@JtOnlineApi(describe="查询所有的创建studio申请,只有对应权限的可以看",name="")
@JsonTagAnnotation(actionValue="/search",namespace="app/studio/all",responseHideFieldArray = {"studioImg"})
@JsonTagPaginationAction(listBeanClass=TblDanceStudio.class
		,sql="select new TblDanceStudio(tds.id,tds.name,tds.createUserId,tds.createDateTime," +
		" tds.status,tdu.aka,tdu.avatarUrl) from TblDanceStudio tds,TblDanceUser tdu where tdu.userId=tds.createUserId "
		,sqlTail = " order by tds.id desc ")
public class SearchStudioAll {

	@MyValidation(exceptionDesc="status格式异常",maxlength = 2)
	@PaginationField(sql=" and tds.status=:status ")
	private String status;

	@MyValidation(exceptionDesc="name格式异常",maxlength = 30)
	@PaginationField(sql=" and tds.name like:name ")
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
