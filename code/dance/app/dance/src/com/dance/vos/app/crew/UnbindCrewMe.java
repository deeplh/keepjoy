package com.dance.vos.app.crew;


import com.dance.DanceConstant;
import com.dance.entity.*;
import com.dance.security.DanceUser;
import com.dance.security.weixin.DanceUserHolder;
import com.dance.service.DanceCommonService;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.validation.MyValidation;
import jsontag.dao.JsonTagTemplateDaoImpl;
import jsontag.exception.JsonTagException;
import jsontag.interf.IDoService;
import org.jtsecurity.proxy.RedisSsoProxy;

@JtOnlineApi(describe="",name="")
@JsonTagAnnotation(actionValue="/unbind",namespace="app/crew/me")
public class UnbindCrewMe extends JsonTagTemplateDaoImpl implements IDoService<Object> {


	
	@MyValidation(exceptionDesc="id格式异常",required = true,getFrom = TblDanceCrew.class)

	private Integer id;



	public Integer getId() {
			return this.id;
		}
		public void setId(Integer id) {
			this.id = id;
		}

	@Override
	public Object doService() throws Exception {

		TblDanceUserCrewRelation td=findObjectFromListByHql(TblDanceUserCrewRelation.class,
				" from TblDanceUserCrewRelation where crewId=? and userId=? and status=? ",
				this.id,DanceUserHolder.getDanceUser().getUserId(),DanceConstant.STATUS_2_YES);
		if(null==td){
			throw new JsonTagException("id数据异常");
		}
		td.setStatus(DanceConstant.STATUS_3_REMOVE);
		update(td);

		DanceUser du=DanceUserHolder.getDanceUser();
		DanceCommonService.setDanceUserCrews(du.getDanceUserInfo());
		RedisSsoProxy.setUserToRedis(du);

		return du.getDanceUserInfo();
	}
}
