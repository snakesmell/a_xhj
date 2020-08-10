package com.util;

import java.util.Properties;

import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.udpUtil.MqHelper;
import com.udpUtil.TopicFactory;

public  class ServerStart extends HttpServlet{
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		begin();
	}
	
	public static void begin(){
		new Thread(new Runnable() {
			public void run() {
				
//				MqHelper mq = new MqHelper("admin","admin","tcp://192.168.10.224:61616");
		     // TODO Auto-generated method stub
//				Properties pro = Common.getProperties();
//				pro = Common.getProperties();
//				String mqurl = pro.getProperty("mqurl");
//				String topic = pro.getProperty("topic");
				try {
					TopicFactory topicFactory = new TopicFactory("admin","admin","tcp://192.168.10.65:61616");
					String topic="ServerXHJ";
					MessageConsumer consumer = topicFactory.CreateConsumerReceive(topic);	
					consumer.setMessageListener(new MessageListener() {
					        public void onMessage(Message message) {
					            	TextMessage textMessage = (TextMessage) message;
									try {
										String jsonString2 = textMessage.getText();
										System.out.println(jsonString2);
//										redis.lpush(Common.ENJOYOR_PASSINF, jsonString2);
									} catch (Exception e) {
										e.printStackTrace();
									}
					        }
					 });
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}).start();;
	}

}
