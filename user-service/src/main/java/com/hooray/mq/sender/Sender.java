package com.hooray.mq.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

import com.hooray.channel.TestChannel;
import com.hooray.domain.User;

@EnableBinding(TestChannel.class)
public class Sender {
	@Autowired
	private TestChannel testChannel;
	
	public void sendMsg(User user) {
		testChannel.outputChannel().send(MessageBuilder.withPayload(user).build());
	}
}
