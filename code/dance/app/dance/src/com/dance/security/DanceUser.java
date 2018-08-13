package com.dance.security;

import com.dance.DanceConstant;
import com.dance.entity.*;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.jtweixin.security.smallplatform.SmallPlatformUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;




public class DanceUser extends SmallPlatformUser implements Serializable {


	private static final long serialVersionUID = 1L;

	private boolean isAdmin;
	private boolean isSuperAdmin;
	private boolean isCrewAdmin;
	private boolean isStudioAdmin;

	private TblDanceUser danceUserInfo;



	public TblDanceUser getDanceUserInfo() {
		return danceUserInfo;
	}

	public void setDanceUserInfo(TblDanceUser danceUserInfo) {
		this.danceUserInfo = danceUserInfo;
	}

	public boolean isSuperAdmin() {
		return isSuperAdmin;
	}

	public void setSuperAdmin(boolean superAdmin) {
		isSuperAdmin = superAdmin;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}

	public boolean isCrewAdmin() {
		return isCrewAdmin;
	}

	public void setCrewAdmin(boolean crewAdmin) {
		isCrewAdmin = crewAdmin;
	}

	public boolean isStudioAdmin() {
		return isStudioAdmin;
	}

	public void setStudioAdmin(boolean studioAdmin) {
		isStudioAdmin = studioAdmin;
	}

	public String getCrewIdsForIn(){
		if(null==danceUserInfo.getCrews() || danceUserInfo.getCrews().size()==0){
			return null;
		}
		String crewIds="";
		for(TblDanceUserCrewRelation td:danceUserInfo.getCrews()){
			crewIds=crewIds+td.getCrewId()+",";
		}
		return crewIds.substring(0,crewIds.length()-1);
	}

	public String getStudioIdsForIn(){
		if(null==danceUserInfo.getStudios() || danceUserInfo.getStudios().size()==0){
			return null;
		}
		String studioIds="";
		for(TblDanceUserStudioRelation tds:danceUserInfo.getStudios()){
			studioIds=studioIds+"'"+tds.getId()+"',";
		}
		return studioIds.substring(0,studioIds.length()-1);
	}


	public  String getCrewIdsForRole(){
		if(isSuperAdmin){//全都可以查
			return null;
		}else if(isCrewAdmin){//只能查自己的
			String crewIds=getCrewIdsForIn();
			if(StringUtils.isEmpty(crewIds)){
				return DanceConstant.SEARCH_NONE_ID;
			}else{
				return crewIds;
			}
		}else{
			return DanceConstant.SEARCH_NONE_ID;
		}
	}

	public  String getStudioIdsForRole(){
		if(isSuperAdmin){
			return null;
		}else if(isStudioAdmin){
			String studioIds=getStudioIdsForIn();
			if(StringUtils.isEmpty(studioIds)){
				return DanceConstant.SEARCH_NONE_ID;
			}else return studioIds;
		}else {
			return DanceConstant.SEARCH_NONE_ID;
		}
	}
}
