package com.piano.server.stomp;

import com.piano.server.game.session.GameSession;
import com.piano.server.game.session.GameSessionContainer;
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

}
