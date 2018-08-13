package com.dance.vos.app.studio;


import com.dance.DanceConstant;
import com.dance.entity.TblDanceCrew;
import com.dance.entity.TblDanceStudio;
import com.dance.entity.TblDanceUser;
import com.dance.security.weixin.DanceUserHolder;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.validation.MyValidation;
import jsontag.dao.JsonTagTemplateDaoImpl;
import jsontag.exception.JsonTagException;
import jsontag.interf.IDoService;

import java.util.List;

@JtOnlineApi(describe="",name="")
@JsonTagAnnotation(actionValue="/get",namespace="app/studio",responseHideFieldArray = {"img"})
public class GetStudio extends JsonTagTemplateDaoImpl implements IDoService<TblDanceStudio> {


	
	@MyValidation(exceptionDesc="id格式异常",required = true)

	private Integer id;
	


	public Integer getId() {
			return this.id;
		}
		public void setId(Integer id) {
			this.id = id;
		}

	@Override
	public TblDanceStudio doService() throws Exception {
		TblDanceStudio tds=get(TblDanceStudio.class,this.id);
		if(null==tds){
			throw new JsonTagException("id数据异常");
		}

		List<TblDanceUser> ms=find("select new TblDanceUser(tdu.userId, tdu.aka, tdu.avatarUrl)" +
				" from TblDanceUser tdu,TblDanceUserStudioRelation tdusr " +
				" where tdu.userId=tdusr.userId and tdusr.studioId=? and tdusr.status=? ",
				tds.getId(),DanceConstant.STATUS_2_YES);
		tds.setMembers(ms);

		long allApplySize=this.jsonTagJdbcDao.getJdbcTemplate().queryForObject(
				"select count(id) from TblDanceUserStudioRelation  " +
						" where studioId=? and userId=? and (status=? or status=?) ",
				Long.class,tds.getId(),DanceUserHolder.getDanceUser().getUserId(),
				DanceConstant.STATUS_2_YES,DanceConstant.STATUS_0_ING);

		if(allApplySize>0){
			tds.setIsMeApply(true);
		}

		if(tds.getCreateUserId().equals(DanceUserHolder.getDanceUser().getUserId())){
			tds.setIsMine(true);
		}


		return tds;
	}
}
