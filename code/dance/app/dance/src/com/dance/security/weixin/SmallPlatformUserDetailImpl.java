package com.dance.security.weixin;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import com.dance.DanceConstant;
import com.dance.entity.TblDanceUser;
import com.dance.security.DanceUser;


import com.dance.service.DanceCommonService;
import jsontag.JsonTagContext;
import org.jtsecurity.JtSecurityConstants;
import org.jtsecurity.po.BasUser;
import org.jtsecurity.security.impl.UserDetailImpl;
import org.jtweixin.security.smallplatform.ISmallPlatformUserDetailsService;
import org.jtweixin.security.smallplatform.SmallPlatformUser;
import org.springframework.beans.BeanUtils;


public class SmallPlatformUserDetailImpl implements ISmallPlatformUserDetailsService {

	@Override
	public SmallPlatformUser loadUser(SmallPlatformUser suser) throws Exception {

		DanceUser du= new DanceUser();

		BeanUtils.copyProperties(suser,du);
		TblDanceUser tdu=JsonTagContext.getJsonTagDao().findObjectFromListByHql(TblDanceUser.class,
				" from TblDanceUser where jtWeixinSmallPlatformUserId=? ", suser.getSmallPlatformUserId());

		BasUser bu=null;
		if(null==tdu){//第一次
			Date now=new Date();

			bu=new BasUser();

			bu.setCreateTime(now);
			JsonTagContext.getJsonTagDao().save(bu);

			tdu=new TblDanceUser();
			tdu.setUserId(bu.getUserId());
			tdu.setJtWeixinSmallPlatformUserId(suser.getSmallPlatformUserId());
			tdu.setCreateDateTime(now);
			tdu.setAvatarUrl(suser.getWeixinUser().getAvatarUrl());
			JsonTagContext.getJsonTagDao().save(tdu);

			bu.setUserName(suser.getWeixinUser().getNickName());
			JsonTagContext.getJsonTagDao().update(bu);
		}else{
			bu=JsonTagContext.getJsonTagDao().get(BasUser.class,tdu.getUserId());

		}
		if(!suser.getWeixinUser().getAvatarUrl().equals(tdu.getAvatarUrl())){
			tdu.setAvatarUrl(suser.getWeixinUser().getAvatarUrl());
			JsonTagContext.getJsonTagDao().update(tdu);
		}

		UserDetailImpl.setRoleMenuResource(du,bu.getUserId());

		if(null!=du.getRoles() && du.getRoles().size()>0){

			du.setAdmin(true);

			if(du.getRoles().containsKey(JtSecurityConstants.SUPER_ADMIN_ROLE)){
				du.setSuperAdmin(true);
				du.setCrewAdmin(true);
				du.setStudioAdmin(true);
			}

			if(du.getRoles().containsKey(DanceConstant.ROLE_CREW_ADMIN)){
				du.setCrewAdmin(true);
			}
			if(du.getRoles().containsKey(DanceConstant.ROLE_STUDIO_ADMIN)){
				du.setStudioAdmin(true);
			}
		}

		du.setUserId(tdu.getUserId());
		DanceCommonService.setDanceUser(tdu);
		du.setDanceUserInfo(tdu);



		return du;

	}
	

}
