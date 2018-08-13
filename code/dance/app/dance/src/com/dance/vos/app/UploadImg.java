package com.dance.vos.app;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.dance.DanceConstant;
import com.dance.service.DanceCommonService;
import jsontag.JsonTagContext;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.file.JsonTagScaleImage;
import jsontag.annotation.file.JsonTagUploadAction;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.validation.MyValidation;
import jsontag.bean.handler.file.upload.UploadFileBean;
import jsontag.factory.JsonTagConfigFactory;
import jsontag.interf.file.ICustomPath;
import jsontag.interf.file.jt.IJtUpload;
import jsontag.util.DateUtil;
import jsontag.util.UrlUtil;

@JtOnlineApi(name="上传图片")
@JsonTagAnnotation(actionValue="/upload",namespace="app/img")
@JsonTagUploadAction(permitFileExtensionArray={"png","jpg","jpeg"},permitFileSize=1024*8)
@JsonTagScaleImage(scalingWidthsAndHeights=DanceConstant.ATTACH_MIN_IMG)
public class UploadImg implements IJtUpload,ICustomPath{

	@MyValidation(required = true,maxlength = 10)
	private String type;

	@Override
	public Object doUpload(List<UploadFileBean> uploadFileBeanList) throws Exception {
		if(null!=uploadFileBeanList && uploadFileBeanList.size()>0){
			return uploadFileBeanList.get(0).getFileAttrBeanList().get(0);
		}else return null;

	}

	@Override
	public String getCustomPath() throws Exception {
		String path=UrlUtil.addLastSlash(JsonTagContext.getRealPath(JsonTagConfigFactory.getStringConfigByKey("upload-file")))
				+type;
		if(DanceConstant.UPLOAD_IMG_TYPE_EVENT.equals(this.type)){
			path=path+File.separator+DateUtil.dateToStringByDateFormat(DanceCommonService.getNow(),DateUtil.DATEFORMAT_YYYYMM);
		}
		return path ;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
