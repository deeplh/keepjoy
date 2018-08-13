package com.dance.vos.app.crew;


import com.dance.DanceConstant;
import com.dance.entity.TblDanceCrew;
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
@JsonTagAnnotation(actionValue="/get",namespace="app/crew",responseHideFieldArray = {"img"})
public class GetCrew extends JsonTagTemplateDaoImpl implements IDoService<TblDanceCrew> {


	
	@MyValidation(exceptionDesc="id格式异常",required = true)

	private Integer id;
	


	public Integer getId() {
			return this.id;
		}
		public void setId(Integer id) {
			this.id = id;
		}

	@Override
	public TblDanceCrew doService() throws Exception {
		TblDanceCrew tdc=get(TblDanceCrew.class,this.id);
		if(null==tdc){
			throw new JsonTagException("id数据异常");
		}

		List<TblDanceUser> ms=find("select new TblDanceUser(tdu.userId, tdu.aka, tdu.avatarUrl)" +
				" from TblDanceUser tdu,TblDanceUserCrewRelation tducr " +
				" where tdu.userId=tducr.userId and tducr.crewId=? and tducr.status=?",
				tdc.getId(),DanceConstant.STATUS_2_YES);
		tdc.setMembers(ms);

		long allApplySize=this.jsonTagJdbcDao.getJdbcTemplate().queryForObject(
				"select count(tducr.id) from TblDanceUserCrewRelation tducr " +
				" where tducr.crewId=? and tducr.userId=? and (tducr.status=? or tducr.status=?) ",
				Long.class,tdc.getId(),DanceUserHolder.getDanceUser().getUserId(),
				DanceConstant.STATUS_2_YES,DanceConstant.STATUS_0_ING);

		if(allApplySize>0){
			tdc.setIsMeApply(true);
		}

		if(tdc.getCreateUserId().equals(DanceUserHolder.getDanceUser().getUserId())){
			tdc.setIsMine(true);
		}

		return tdc;
	}
}
