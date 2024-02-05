package com.piano.server.game.session;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameSessionContainer {

    private Map<String, GameSession> gameSessions = new HashMap();
    private Logger log;

    public GameSessionContainer() {
        log = LoggerFactory.getLogger(GameSession.class);
    }

    public void putSession(String session_id, GameSession gameSession) {
        this.gameSessions.put(session_id, gameSession);
    }

    public void removeSession(String session_id) {
        gameSessions.remove(session_id);
    }

    public GameSession getSession(String session_id) {
        return this.gameSessions.getOrDefault(session_id, null);
    }

    public boolean constainsSession(String sessionId) {
        return this.gameSessions.containsKey(sessionId);
    }

    @Scheduled(fixedRate = 300000)
    public void logHelloWorld() {
        log.info("active game sessions: " + this.gameSessions.toString());
    }
}
