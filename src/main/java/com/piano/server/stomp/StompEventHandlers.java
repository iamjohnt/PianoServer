package com.piano.server.stomp;

import com.piano.server.game.session.GameSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Map;

/*
handles some STOMP protocol events, such as connect / disconnect
also prints list of sessions periodically, to help debug game session registration / deregistrations (see GameSessionContainer and GameSession class for further details)

Full list as of 4/5/2024 - https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/socket/messaging/AbstractSubProtocolEvent.html
    SessionConnectedEvent
    SessionConnectEvent
    SessionDisconnectEvent
    SessionSubscribeEvent
    SessionUnsubscribeEvent
*/

@Component
public class StompEventHandlers {

    private Logger log;
    private Map<String, GameSession> gameSessions;

    @Autowired
    public StompEventHandlers(Map<String, GameSession> gameSessions) {
        this.log = LoggerFactory.getLogger(GameSession.class);
        this.gameSessions = gameSessions;
    }

    @EventListener
    private void onSessionConnect(SessionConnectedEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = accessor.getSessionId();
        log.info("Stomp Session connected: " + sessionId);
    }

    @EventListener
    private void onSessionDisconnect(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        log.info("Stomp Session disconnected: " + sessionId);
        this.gameSessions.remove(sessionId);
    }

    @Scheduled(fixedRate = 10000)
    private void printSessions() {
        log.info(this.gameSessions.toString());
    }



}