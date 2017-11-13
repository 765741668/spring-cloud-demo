package com.hooray.mq.receiver;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import com.hooray.channel.TestChannel;
import com.hooray.entity.User;

@EnableBinding(TestChannel.class)
public class Receiver {
	@StreamListener(TestChannel.TEST_CHANNEL_IN)
	public void receive(Message<User> message) {
		System.out.println(message);
		System.out.println(message.getPayload());
		System.out.println(message.getPayload().getName());
	}
}
