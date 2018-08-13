package com.dance.vos.app.user;

import com.dance.entity.TblDanceUser;
import com.dance.security.DanceUser;
import com.dance.security.weixin.DanceUserHolder;
import com.dance.service.DanceCommonService;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.validation.MyValidation;
import jsontag.dao.JsonTagTemplateDaoImpl;
import jsontag.exception.JsonTagException;
import jsontag.interf.IDoAction;
import org.jtsecurity.proxy.RedisSsoProxy;
import org.jtsecurity.security.JtSecurityUser;


@JtOnlineApi(describe="",name="")
@JsonTagAnnotation(actionValue="/get_dance_user_info",namespace="app/user")
public class GetDanceUserInfo extends JsonTagTemplateDaoImpl implements IDoAction {

    @JtOnlineApi(describe="",name="")
    @MyValidation(exceptionDesc="userId格式异常",required = true)
    private Integer userId;

    @Override
    public TblDanceUser doAction() throws Exception {
        JtSecurityUser user= RedisSsoProxy.getUserFromRedis(userId);
        if(null!=user){
            DanceUser du= (DanceUser) user;
            return du.getDanceUserInfo();
        }else {

            TblDanceUser tdu = get(TblDanceUser.class, userId);
            if (null == tdu) {
                throw new JsonTagException("id数据异常");
            }
            DanceCommonService.setDanceUser(tdu);
            return tdu;
        }
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
