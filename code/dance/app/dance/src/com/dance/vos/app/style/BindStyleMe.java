package com.dance.vos.app.style;


import com.dance.entity.TblDanceStyle;
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
@JsonTagAnnotation(actionValue="/bind",namespace="app/style/me",responseHideFieldArray = {"img"})
public class BindStyleMe extends JsonTagTemplateDaoImpl implements IDoService<Object> {


	
	@MyValidation(exceptionDesc="styleId异常",required = true,getFrom = TblDanceStyle.class)

	private Integer styleId;
	


	@Override
	public Object doService() throws Exception {
		TblDanceUserStyleRelation t=findObjectFromListByHql(TblDanceUserStyleRelation.class,
				" from TblDanceUserStyleRelation where styleId=? and userId=?  ",
				this.styleId,DanceUserHolder.getDanceUser().getUserId());
		if(null!=t)throw new JsonTagException("请勿重复绑定");

		t=new TblDanceUserStyleRelation();
		t.setStyleId(styleId);
		t.setUserId(DanceUserHolder.getDanceUser().getUserId());
		t.setCreateDateTime(DanceCommonService.getNow());
		t.setCreateUserId(DanceUserHolder.getDanceUser().getUserId());
		save(t);

		DanceUser du=DanceUserHolder.getDanceUser();
		DanceCommonService.setDanceUserStyles(du.getDanceUserInfo());
		RedisSsoProxy.setUserToRedis(du);

		return null;
	}

	public Integer getStyleId() {
		return styleId;
	}

	public void setStyleId(Integer styleId) {
		this.styleId = styleId;
	}
}
