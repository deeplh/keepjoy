package com.dance.vos.app.style;


import com.dance.entity.TblDanceStyle;
import com.dance.entity.TblDanceUser;
import com.dance.entity.TblDanceUserStyleRelation;
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
@JsonTagAnnotation(actionValue="/unbind",namespace="app/style/me")
public class UnbindStyleMe extends JsonTagTemplateDaoImpl implements IDoService<Object> {


	
	@MyValidation(exceptionDesc="id格式异常",required = true,getFrom = TblDanceStyle.class)

	private Integer id;



	public Integer getId() {
			return this.id;
		}
		public void setId(Integer id) {
			this.id = id;
		}

	@Override
	public Object doService() throws Exception {

		TblDanceUserStyleRelation td=findObjectFromListByHql(TblDanceUserStyleRelation.class,
				" from TblDanceUserStyleRelation where styleId=? and userId=? ",this.id,DanceUserHolder.getDanceUser().getUserId());
		if(null==td){
			throw new JsonTagException("id数据异常");
		}
		remove(td);
		DanceUser du=DanceUserHolder.getDanceUser();
		DanceCommonService.setDanceUserStyles(du.getDanceUserInfo());
		RedisSsoProxy.setUserToRedis(du);

		return du.getDanceUserInfo();
	}
}
