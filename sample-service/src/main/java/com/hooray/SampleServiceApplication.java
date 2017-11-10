package com.hooray;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableFeignClients
@EnableWebSocket
public class SampleServiceApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(SampleServiceApplication.class, args);
	}

	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

//	@Bean(initMethod = "start", destroyMethod = "stop")
//	public HoorayPushClient client() {
//		HoorayPushClient client = new ClientBootstrap().addMessageListener(new SimpleClientTextMessageListener())
//				.build();
//		return client;
//	}

}
