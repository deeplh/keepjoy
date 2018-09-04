package com.station.vos;

import org.springframework.beans.factory.annotation.Autowired;

import com.shmetro2.service.dubbo.IProviderService;

import jsontag.annotation.JsonTagAnnotation;
import jsontag.interf.IDoService;

@JsonTagAnnotation(actionValue = "/test_consume",namespace = "test")
public class Consumer implements IDoService {

    @Autowired()
    public IProviderService providerService;


    private String name;

    @Override
    public Object doService() throws Exception {
        return providerService.doService();
//        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
//        return "Hello " + name + ", response from provider: " + RpcContext.getContext().getLocalAddress();
    }




}
