package godinner.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;	

@Configuration
@EnableWebSocketMessageBroker
@CrossOrigin(origins = "http://localhost")
public class WSConfig implements WebSocketMessageBrokerConfigurer{

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic");
		registry.setApplicationDestinationPrefixes("/app");
	}
	
	@Override
	@CrossOrigin(origins = "*")
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/wsfortest/socket").setAllowedOrigins("*").withSockJS().setWebSocketEnabled(true);
		registry.addEndpoint("/javatechie").setAllowedOrigins("*").withSockJS();
		System.out.println("Um se registrou");
	}
}
