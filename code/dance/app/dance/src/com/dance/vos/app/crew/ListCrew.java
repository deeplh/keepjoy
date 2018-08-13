package com.dance.vos.app.crew;


import com.dance.entity.TblDanceCrew;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.pagination.JsonTagPaginationAction;
import jsontag.annotation.pagination.PaginationField;
import jsontag.annotation.validation.MyValidation;
import jsontag.dao.JsonTagTemplateDaoImpl;
import jsontag.interf.IDoService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@JtOnlineApi(describe="查询所有的舞团",name="")
@JsonTagAnnotation(actionValue="/list",namespace="app/crew")
public class ListCrew extends JsonTagTemplateDaoImpl implements IDoService<List<List<TblDanceCrew>>> {


	@MyValidation(exceptionDesc="name格式异常")
	@PaginationField(sql=" and name=:name ")
	private String name;

	
	@MyValidation(exceptionDesc="establishDate格式异常")
	@PaginationField(sql=" and establishDate=:establishDate ")
	private Date establishDate;


	@Override
	public List<List<TblDanceCrew>> doService() throws Exception {
		List<TblDanceCrew> crews=find("from TblDanceCrew");
		List<List<TblDanceCrew>> cc=new ArrayList<List<TblDanceCrew>>();
		List<TblDanceCrew> new3Crews=null;
		for(int i=0;i<crews.size();i++){
			if(i%3==0){
				new3Crews=new ArrayList<TblDanceCrew>();
				cc.add(new3Crews);
			}
			new3Crews.add(crews.get(i));
		}

		return cc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEstablishDate() {
		return establishDate;
	}

	public void setEstablishDate(Date establishDate) {
		this.establishDate = establishDate;
	}
}
