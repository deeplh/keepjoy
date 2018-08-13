package com.dance.vos.app.event;


import com.dance.DanceConstant;
import com.dance.entity.TblDanceEvent;
import com.dance.entity.TblDanceEventAttach;
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
@JsonTagAnnotation(actionValue="/get",namespace="app/event",responseHideFieldArray = {"img"})
public class GetEvent extends JsonTagTemplateDaoImpl implements IDoService<Object> {


	
	@MyValidation(exceptionDesc="id格式异常",required = true)

	private Integer id;
	



	public Integer getId() {
			return this.id;
		}
		public void setId(Integer id) {
			this.id = id;
		}

	@Override
	public Object doService() throws Exception {
		TblDanceEvent tde=get(TblDanceEvent.class,this.id);
		if(null==tde){
			throw new JsonTagException("id数据异常");
		}

		List<TblDanceEventAttach> attachs=find(" from TblDanceEventAttach where eventId=? ",this.id);


		String[] a=new String[attachs.size()];
		String[] am=new String[attachs.size()];
		for(int i=0;i<attachs.size();i++){
			a[i]=attachs.get(i).getImgDis();
			am[i]=attachs.get(i).getImgDisMin();
		}

		tde.setAttachArr(a);
		tde.setAttachArrMin(am);

		List<TblDanceUser> ms=find("select new TblDanceUserEventRelation(tdu.userId, tdu.aka, tdu.avatarUrl,tduer.status)" +
						" from TblDanceUser tdu,TblDanceUserEventRelation tduer " +
						" where tdu.userId=tduer.userId and tduer.eventId=? and (tduer.status=? or tduer.status=?)",
				tde.getId(),DanceConstant.STATUS_2_YES,DanceConstant.STATUS_0_ING);
		tde.setMembers(ms);

		long allApplySize=this.jsonTagJdbcDao.getJdbcTemplate().queryForObject(
				"select count(id) from TblDanceUserEventRelation  " +
						" where eventId=? and userId=? and (status=? or status=?) ",
				Long.class,tde.getId(),DanceUserHolder.getDanceUser().getUserId(),
				DanceConstant.STATUS_2_YES,DanceConstant.STATUS_0_ING);

		if(allApplySize>0){
			tde.setIsMeApply(true);
		}

		if(tde.getCreateUserId().equals(DanceUserHolder.getDanceUser().getUserId())){
			tde.setIsMine(true);
		}
//		RedisTemplate et = (RedisTemplate)JsonTagContext.getSpringContext().getBean("redisTemplate");
//		et.opsFor

		
		return tde;
	}
}
