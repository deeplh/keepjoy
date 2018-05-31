package com.lzj.vos.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.lzj.service.IProviderService;

import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.dao.JsonTagTemplateDaoImpl;
import jsontag.interf.IDoService;

@JsonTagAnnotation(namespace="/test",actionValue="/test_provider")
public class TestProviderVos extends JsonTagTemplateDaoImpl implements IDoService<Object>{

	@JtOnlineApi(isHide=true)
	@Autowired()
	public IProviderService  providerService;
	
	@Override
	public Object doService() throws Exception {
		// TODO Auto-generated method stub
		return providerService.getName("123");
	}


	
}
