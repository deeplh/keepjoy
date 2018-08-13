package com.dance.vos.app.user;


import com.dance.entity.TblDanceCrew;
import com.dance.entity.TblDanceStudio;
import com.dance.entity.TblDanceStyle;
import com.dance.entity.TblDanceUser;
import com.dance.security.DanceUser;
import com.dance.security.weixin.DanceUserHolder;
import com.dance.service.DanceCommonService;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.action.JsonTagFromJsonString;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.validation.MyValidation;
import jsontag.dao.JsonTagTemplateDaoImpl;
import jsontag.exception.JsonTagException;
import jsontag.interf.IDoService;
import jsontag.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.jtsecurity.proxy.RedisSsoProxy;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

@JtOnlineApi(describe="",name="")
@JsonTagAnnotation(actionValue="/update",namespace="app/user/me")
public class UpdateUserMe extends JsonTagTemplateDaoImpl implements IDoService<Object>{



	@JtOnlineApi(describe="",name="")
	@MyValidation(exceptionDesc="img格式异常")
	private String img;

	@JtOnlineApi(describe="",name="")
	@MyValidation(exceptionDesc="trueName格式异常")
	private String trueName;

	@JtOnlineApi(describe="",name="")
	@MyValidation(exceptionDesc="age格式异常")
	private Integer age;

	@JtOnlineApi(describe="",name="")
	@MyValidation(exceptionDesc="sex格式异常")
	private String sex;

	@JtOnlineApi(describe="",name="")
	@MyValidation(exceptionDesc="beginDanceDate格式异常")
	private String beginDanceDate;

	@JtOnlineApi(describe="",name="")
	@MyValidation(exceptionDesc="aka格式异常")
	private String aka;



	@Override
	public Object doService() throws Exception {
		DanceUser du=DanceUserHolder.getDanceUser();
		TblDanceUser obj=get(TblDanceUser.class,du.getDanceUserInfo().getUserId());

		if(null==obj){
			throw new JsonTagException("数据异常");
		}


		if(StringUtils.isNotEmpty(img)){
			obj.setImg(img);
			du.getDanceUserInfo().setImg(img);
		}
		if(StringUtils.isNotEmpty(trueName)){
			obj.setTrueName(trueName);
			du.getDanceUserInfo().setTrueName(trueName);
		}
		if(null!=age){
			obj.setAge(age);
			du.getDanceUserInfo().setAge(age);

		}
		if(StringUtils.isNotEmpty(sex)){
			obj.setSex(sex);
			du.getDanceUserInfo().setSex(sex);
		}
		if(StringUtils.isNotEmpty(beginDanceDate)){
			Date d=DateUtil.stringToDateByDateFormat(beginDanceDate,DateUtil.DATEFORMAT_YYYY_MM_DD);
			obj.setBeginDanceDate(d);
			du.getDanceUserInfo().setBeginDanceDate(d);
		}
		if(StringUtils.isNotEmpty(aka)){
			obj.setAka(aka);
			du.getDanceUserInfo().setAka(aka);

		}
		update(obj);

		RedisSsoProxy.setUserToRedis(du);

		return du.getDanceUserInfo();
	}


	public String getImg() {
		return this.img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTrueName() {
		return this.trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public Integer getAge() {
		return this.age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return this.sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBeginDanceDate() {
		return this.beginDanceDate;
	}
	public void setBeginDanceDate(String beginDanceDate) {
		this.beginDanceDate = beginDanceDate;
	}
	public String getAka() {
		return this.aka;
	}
	public void setAka(String aka) {
		this.aka = aka;
	}


}
