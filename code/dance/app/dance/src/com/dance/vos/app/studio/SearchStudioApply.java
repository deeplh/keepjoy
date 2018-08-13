package com.dance.vos.app.studio;


import com.dance.DanceConstant;
import com.dance.entity.TblDanceUserStudioRelation;
import com.dance.security.weixin.DanceUserHolder;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.pagination.JsonTagPaginationAction;
import jsontag.annotation.pagination.PaginationField;
import jsontag.annotation.validation.MyValidation;


@JtOnlineApi(describe="查询所有的舞团申请",name="")
@JsonTagAnnotation(actionValue="/search",namespace="app/studio/apply",responseHideFieldArray = {"studioImg"})
@JsonTagPaginationAction(listBeanClass=TblDanceUserStudioRelation.class
		,sql=" select new TblDanceUserStudioRelation(tdusr.id, tdusr.studioId,tdc.name, tdc.img," +
		" tdu.aka, tdu.avatarUrl,tdusr.userId,tdusr.createDateTime) from TblDanceUserStudioRelation tdusr," +
		" TblDanceStudio tdc,TblDanceUser tdu where tdusr.userId=tdu.userId and tdusr.studioId=tdc.id" +
		" and  tdusr.status= "+DanceConstant.STATUS_0_ING,sqlTail = " order by tdusr.id desc " )
public class SearchStudioApply {


	@MyValidation(exceptionDesc="name格式异常",maxlength = 30)
	@PaginationField(sql=" and tdc.name like:name ")
	private String name;
	
	@MyValidation(exceptionDesc="studioId格式异常")
	@PaginationField(sql=" and tdusr.studioId in :studioId ",dataTypeWhenWithIn=Integer.class)
	private String studioId;

	

	public String getName() {
			return "%"+this.name+"%";
	}
		public void setName(String name) {
			this.name = name;
		}

	public String getStudioId() {
		return DanceUserHolder.getDanceUser().getStudioIdsForRole();
	}

	public void setStudioId(String studioId) {
		this.studioId = studioId;
	}
}
