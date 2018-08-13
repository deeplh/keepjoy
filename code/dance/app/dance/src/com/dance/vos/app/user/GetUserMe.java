package com.dance.vos.app.user;

import com.dance.security.weixin.DanceUserHolder;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.interf.IDoAction;


@JtOnlineApi(describe="",name="")
@JsonTagAnnotation(actionValue="/get",namespace="app/user/me",
        responseHideFieldArray = {"smallPlatformUserId","sessionId","sessionKey"})
public class GetUserMe implements IDoAction {

    @Override
    public Object doAction() throws Exception {
        return DanceUserHolder.getDanceUser();
    }
}
