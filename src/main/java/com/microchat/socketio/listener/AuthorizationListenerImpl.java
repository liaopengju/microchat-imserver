package com.microchat.socketio.listener;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;

/**
 * IM 权限校验监听
 *
 * @author pengju.liao
 * @since 2019年03月14日
 */
public class AuthorizationListenerImpl implements AuthorizationListener {
    @Override
    public boolean isAuthorized(HandshakeData handshakeData) {
        return true;
    }
}
