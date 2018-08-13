package com.dance.vos.app.user;


import com.dance.entity.TblDanceUser;
import com.dance.security.DanceUser;
import com.dance.security.weixin.DanceUserHolder;
import com.dance.service.DanceCommonService;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.dao.JsonTagTemplateDaoImpl;
import jsontag.exception.JsonTagException;
import jsontag.interf.IDoAction;
import org.jtsecurity.proxy.RedisSsoProxy;

@JtOnlineApi(describe="",name="")
@JsonTagAnnotation(actionValue="/refresh",namespace="app/user/me")
public class RefreshUserMe extends JsonTagTemplateDaoImpl implements IDoAction<DanceUser> {

    @Override
    public DanceUser doAction() throws Exception {
        DanceUser du=DanceUserHolder.getDanceUser();
        TblDanceUser tdu = get(TblDanceUser.class, du.getDanceUserInfo().getUserId());

        DanceCommonService.setDanceUser(tdu);

        du.setDanceUserInfo(tdu);

        RedisSsoProxy.setUserToRedis(du);
        return du;
    }
}
