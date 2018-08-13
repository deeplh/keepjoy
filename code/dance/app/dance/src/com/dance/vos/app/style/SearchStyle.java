package com.dance.vos.app.style;



import com.dance.entity.TblDanceStyle;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.pagination.JsonTagPaginationAction;
import jsontag.annotation.pagination.PaginationField;
import jsontag.annotation.validation.MyValidation;


@JtOnlineApi(describe="",name="")
@JsonTagAnnotation(actionValue="/search",namespace="app/style")
@JsonTagPaginationAction(listBeanClass=TblDanceStyle.class,sql=" from TblDanceStyle where 1=1 ",isPagination = false)
public class SearchStyle {


	@MyValidation(exceptionDesc="name格式异常")
	@PaginationField(sql=" and name=:name ")
	private String name;
	
	@MyValidation(exceptionDesc="id格式异常")
	@PaginationField(sql=" and id=:id ")
	private Integer id;


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

}
