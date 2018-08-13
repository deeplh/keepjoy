package com.dance.vos.app.statistic;

import com.dance.security.weixin.DanceUserHolder;
import jsontag.JsonTagContext;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.interf.IDoAction;
import org.jtsecurity.factory.JtSecurityFactory;
import org.jtsecurity.proxy.RedisSsoProxy;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;


@JtOnlineApi(describe="统计在线人数",name="")
@JsonTagAnnotation(actionValue="/user",namespace="/app/count")
public class CountUserNum implements IDoAction {

    @Override
    public Object doAction() throws Exception {
        RedisTemplate et = (RedisTemplate)JsonTagContext.getSpringContext().getBean("redisTemplate");
        Set<String> keys = et.keys("*_jtSecurityUser");

        return keys.size();
    }
}
