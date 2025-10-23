package com.aezen.www.chat;

import com.aezen.www.chat.ChatWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private ChatWebSocketHandler chatWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatWebSocketHandler, "/ws-chat")
                .addInterceptors(new HttpSessionHandshakeInterceptor() {
                    @Override
                    public boolean beforeHandshake(
                            org.springframework.http.server.ServerHttpRequest request,
                            org.springframework.http.server.ServerHttpResponse response,
                            org.springframework.web.socket.WebSocketHandler wsHandler,
                            java.util.Map<String, Object> attributes) throws Exception {

                        if (request instanceof org.springframework.http.server.ServletServerHttpRequest) {
                            javax.servlet.http.HttpServletRequest servletRequest =
                                    ((org.springframework.http.server.ServletServerHttpRequest) request).getServletRequest();
                            attributes.put("HTTP_SESSION", servletRequest.getSession());
                        }

                        return super.beforeHandshake(request, response, wsHandler, attributes);
                    }
                })
                .setAllowedOrigins("*");
    }
}