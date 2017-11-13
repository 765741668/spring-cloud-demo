package com.hooray.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface TestChannel {
	static String TEST_CHANNEL_IN = "user";
	static String TEST_CHANNEL_OUT = "sample";
	
	@Input(TEST_CHANNEL_IN)
	SubscribableChannel inputChannel();
	
	@Output(TEST_CHANNEL_OUT)
	MessageChannel outputChannel();
}
