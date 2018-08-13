package com.dance.security.weixin;


import java.util.List;

import com.dance.security.DanceUser;

import jsontag.JsonTagContext;
import jsontag.exception.JsonTagException;
import org.jtweixin.security.smallplatform.SmallPlatformUser;
import org.jtweixin.security.smallplatform.SmallPlatformUserHolder;

public class DanceUserHolder {

	
	
	public static DanceUser getDanceUser(){
		SmallPlatformUser suser=SmallPlatformUserHolder.getUser();
		if(null==suser)throw new JsonTagException("用户信息未绑定",98);
		DanceUser pwu = (DanceUser) suser;
		return pwu;
	}
}
