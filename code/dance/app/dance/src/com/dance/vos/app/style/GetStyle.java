package com.dance.vos.app.style;


import com.dance.entity.TblDanceStyle;
import com.dance.entity.TblDanceUserStyleRelation;
import com.dance.security.weixin.DanceUserHolder;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.validation.MyValidation;
import jsontag.dao.JsonTagTemplateDaoImpl;
import jsontag.exception.JsonTagException;
import jsontag.interf.IDoService;

@JtOnlineApi(describe="",name="")
@JsonTagAnnotation(actionValue="/get",namespace="app/style",responseHideFieldArray = {"img"})
public class GetStyle extends JsonTagTemplateDaoImpl implements IDoService<TblDanceStyle> {


	
	@MyValidation(exceptionDesc="id格式异常",required = true)

	private Integer id;
	


	public Integer getId() {
			return this.id;
		}
		public void setId(Integer id) {
			this.id = id;
		}

	@Override
	public TblDanceStyle doService() throws Exception {
		TblDanceStyle tds=get(TblDanceStyle.class,this.id);
		if(null==tds){
			throw new JsonTagException("id数据异常");
		}

		TblDanceUserStyleRelation td=findObjectFromListByHql(TblDanceUserStyleRelation.class,
				" from TblDanceUserStyleRelation where styleId=? and userId=? ",this.id,DanceUserHolder.getDanceUser().getUserId());


		if(null!=td){
			tds.setMine(true);
		}

		return tds;
	}
}
