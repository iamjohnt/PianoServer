package com.piano.server.stomp;

import com.piano.server.game.session.GameSession;
import com.piano.server.game.session.GameSessionContainer;
import com.piano.server.game.util.GameState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.piano.server.game.stomp")
public class BeanConfig {

    @Bean
    public GameSessionContainer gatGameSessionContainer() {
        return new GameSessionContainer();
    }

    @Bean
    public GameState getGameState() {
        return new GameState(GameState.State.UNSTARTED);
    }

}
