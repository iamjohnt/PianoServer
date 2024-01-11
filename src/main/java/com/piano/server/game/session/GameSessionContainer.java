package com.piano.server.game.session;

import java.util.HashMap;
import java.util.Map;

public class GameSessionContainer {

    private Map<String, GameSession> gameSessions = new HashMap();

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


}
