package com.microchat.client.service.impl;

import com.corundumstudio.socketio.SocketIOClient;
import com.microchat.client.service.ClientService;
import com.microchat.client.utils.NettyClients;
import com.microchat.commons.redis.utils.RedisPubSubUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户端相关操作类
 *
 * @author pengju.liao
 * @since 2019年03月22日
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private RedisPubSubUtil redisPubSubUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void forcedOff(SocketIOClient client, String clientId, String appId) {
        //取消该用户单聊订阅
        redisPubSubUtil.unSubscribe(clientId);
        //删除该用户本地缓存的客户端
        NettyClients.removeClient(clientId);
        //删除redis该用户缓存
        redisTemplate.delete(clientId);
        //发送强制下线通知并断开连接
        client.disconnect();
    }
}
