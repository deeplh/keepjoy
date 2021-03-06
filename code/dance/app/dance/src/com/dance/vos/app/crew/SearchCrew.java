package com.dance.vos.app.crew;


import com.dance.DanceConstant;
import com.dance.entity.TblDanceCrew;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.pagination.JsonTagPaginationAction;
import jsontag.annotation.pagination.PaginationField;
import jsontag.annotation.validation.MyValidation;

import java.util.Date;


@JtOnlineApi(describe="查询所有的舞团",name="")
@JsonTagAnnotation(actionValue="/search",namespace="app/crew")
@JsonTagPaginationAction(listBeanClass=TblDanceCrew.class
		,sql=" from TblDanceCrew where status="+DanceConstant.STATUS_2_YES,isPagination = false)
public class SearchCrew {


	@MyValidation(exceptionDesc="name格式异常")
	@PaginationField(sql=" and name=:name ")
	private String name;
	
	@MyValidation(exceptionDesc="id格式异常")
	@PaginationField(sql=" and id=:id ")
	private Integer id;

	
	@MyValidation(exceptionDesc="establishDate格式异常")
	@PaginationField(sql=" and establishDate=:establishDate ")
	private Date establishDate;
	
	@MyValidation(exceptionDesc="founder格式异常")
	@PaginationField(sql=" and founder=:founder ")
	private String founder;

	

		public String getName() {
			return this.name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getId() {
			return this.id;
		}
		public void setId(Integer id) {
			this.id = id;
		}

		public Date getEstablishDate() {
			return this.establishDate;
		}
		public void setEstablishDate(Date establishDate) {
			this.establishDate = establishDate;
		}
		public String getFounder() {
			return this.founder;
		}
		public void setFounder(String founder) {
			this.founder = founder;
		}
}
