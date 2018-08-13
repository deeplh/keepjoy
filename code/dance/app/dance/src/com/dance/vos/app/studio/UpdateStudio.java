package com.dance.vos.app.studio;


import com.dance.DanceConstant;
import com.dance.entity.TblDanceEventAttach;
import com.dance.entity.TblDanceStudio;
import com.dance.service.DanceCommonService;
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
@JsonTagAnnotation(actionValue="/update",namespace="app/studio")
public class UpdateStudio extends JsonTagTemplateDaoImpl implements IDoService<Object> {

	@JsonTagFromJsonString()
	private TblDanceStudio studio;


	@JtOnlineApi(describe = "", name = "")
	@JsonTagFromJsonString(jsonToListClass = FileAttrBean.class)
	private List<FileAttrBean> fileAttrBeanList;




	@Override
	public Object doService() throws Exception {

		if(studio.getId()==null){
			throw new JsonTagException("id格式异常");
		}

		TblDanceStudio obj = get(TblDanceStudio.class,studio.getId());
		if(null==obj){
			throw new JsonTagException("id数据异常");
		}

		boolean isUpdate=false;

		 if(StringUtils.isNotEmpty(studio.getName()) && !studio.getName().equals(obj.getName())) {
			 isUpdate=true;
			 obj.setName(studio.getName());
		 }

		if(null!=studio.getEstablishDate()) {
		 	if(null!=obj.getEstablishDate()){
		 		if(studio.getEstablishDate().compareTo(obj.getEstablishDate())!=0){
					isUpdate=true;
				}
			}else{
				isUpdate=true;
			}
			obj.setEstablishDate(studio.getEstablishDate());
		}

		if(StringUtils.isNotEmpty(studio.getFounder()) && !studio.getFounder().equals(obj.getFounder())) {
			isUpdate=true;
		 	obj.setFounder(studio.getFounder());
		}

		if(StringUtils.isNotEmpty(studio.getAddress()) && !studio.getAddress().equals(obj.getAddress())) {
			isUpdate=true;
		 	obj.setAddress(studio.getAddress());
		}

		if(StringUtils.isNotEmpty(studio.getInfo()) && !studio.getInfo().equals(obj.getInfo())) {
			isUpdate=true;
		 	obj.setInfo(studio.getInfo());
		}

		if(StringUtils.isNotEmpty(studio.getImg()) && !studio.getImg().equals(obj.getImg())) {
			isUpdate=true;
		 	obj.setImg(studio.getImg());
		}

		if(StringUtils.isNotEmpty(studio.getCity()) && !studio.getCity().equals(obj.getCity())) {
			isUpdate=true;
		 	obj.setCity(studio.getCity());
		}

		if(StringUtils.isNotEmpty(studio.getDistrict()) && !studio.getDistrict().equals(obj.getDistrict())) {
			isUpdate=true;
			obj.setDistrict(studio.getDistrict());
		}

		if(StringUtils.isNotEmpty(studio.getLatitude()) && !studio.getLatitude().equals(obj.getLatitude())) {
			isUpdate=true;
			obj.setLatitude(studio.getLatitude());
		}

		if(StringUtils.isNotEmpty(studio.getLongitude()) && !studio.getLongitude().equals(obj.getLongitude())) {
			isUpdate=true;
			obj.setLongitude(studio.getLongitude());
		}

		if (null!=fileAttrBeanList && fileAttrBeanList.size()>0 ) {
			isUpdate=true;
			obj.setImg(fileAttrBeanList.get(0).getNewName());
		}

		if(isUpdate)update(obj);

		return "修改成功";
	}

	public TblDanceStudio getStudio() {
		return studio;
	}

	public void setStudio(TblDanceStudio studio) {
		this.studio = studio;
	}

	public List<FileAttrBean> getFileAttrBeanList() {
		return fileAttrBeanList;
	}

	public void setFileAttrBeanList(List<FileAttrBean> fileAttrBeanList) {
		this.fileAttrBeanList = fileAttrBeanList;
	}
}

