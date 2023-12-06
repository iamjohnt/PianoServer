package com.piano.server.game.session;

import java.util.HashMap;
import java.util.Map;

public class GameSessionContainer {

    private Map<String, GameSession> gameSessionList = new HashMap();

    public void addSession(String session_id, GameSession gameSession) {
        this.gameSessionList.put(session_id, gameSession);
    }

    public void removeSession(String session_id) {
        gameSessionList.remove(session_id);
    }

}
