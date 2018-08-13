package com.dance.vos.app.crew;


import com.dance.entity.TblDanceCrew;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.action.JsonTagFromJsonString;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.bean.handler.file.upload.FileAttrBean;
import jsontag.dao.JsonTagTemplateDaoImpl;
import jsontag.exception.JsonTagException;
import jsontag.interf.IDoService;
import org.apache.commons.lang.StringUtils;

import java.util.List;

@JtOnlineApi(describe="",name="")
@JsonTagAnnotation(actionValue="/update",namespace="app/crew")
public class UpdateCrew extends JsonTagTemplateDaoImpl implements IDoService<Object> {

	@JsonTagFromJsonString()
	private TblDanceCrew crew;


	@JtOnlineApi(describe = "", name = "")
	@JsonTagFromJsonString(jsonToListClass = FileAttrBean.class)
	private List<FileAttrBean> fileAttrBeanList;




	@Override
	public Object doService() throws Exception {

		if(crew.getId()==null){
			throw new JsonTagException("id格式异常");
		}

		TblDanceCrew obj = get(TblDanceCrew.class,crew.getId());
		if(null==obj){
			throw new JsonTagException("id数据异常");
		}


		 if(StringUtils.isNotEmpty(crew.getName())) {
			 obj.setName(crew.getName());
		 }

		if(null!=crew.getEstablishDate()) {
			obj.setEstablishDate(crew.getEstablishDate());
		}

		if(StringUtils.isNotEmpty(crew.getFounder())) {
			obj.setFounder(crew.getFounder());
		}

		if(StringUtils.isNotEmpty(crew.getAddress())) {
			obj.setAddress(crew.getAddress());
		}

		if(StringUtils.isNotEmpty(crew.getInfo())) {
			obj.setInfo(crew.getInfo());
		}

		if(StringUtils.isNotEmpty(crew.getImg())) {
			obj.setImg(crew.getImg());
		}

		if(StringUtils.isNotEmpty(crew.getCity())) {
			obj.setCity(crew.getCity());
		}

		if(StringUtils.isNotEmpty(crew.getDistrict())) {
			obj.setDistrict(crew.getDistrict());
		}

		if(StringUtils.isNotEmpty(crew.getLatitude())) {
			obj.setLatitude(crew.getLatitude());
		}

		if(StringUtils.isNotEmpty(crew.getLongitude())) {
			obj.setLongitude(crew.getLongitude());
		}

		if (null!=fileAttrBeanList && fileAttrBeanList.size()>0) {
			obj.setImg(fileAttrBeanList.get(0).getNewName());
		}

		update(obj);

		return "修改成功";
	}

	public TblDanceCrew getCrew() {
		return crew;
	}

	public void setCrew(TblDanceCrew crew) {
		this.crew = crew;
	}

	public List<FileAttrBean> getFileAttrBeanList() {
		return fileAttrBeanList;
	}

	public void setFileAttrBeanList(List<FileAttrBean> fileAttrBeanList) {
		this.fileAttrBeanList = fileAttrBeanList;
	}
}

