package com.udpUtil;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
public class TopicFactory {
	private String username;
	private String password;
	private String url;
	private Connection connection;
	private Session session;
	private MessageConsumer consumer;
	public TopicFactory(String username,String password,String url) {
		// TODO Auto-generated constructor stub
		this.username=username;
		this.password=password;
		this.url= "failover:("+url+")"; 
	}
	/**
	 * 鍏抽棴鏈嶅姟
	 */
	public void close() {
		try {
			consumer.close();
			session.close();
			connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 鑾峰彇Topic娑堟伅
	 * @return
	 * @throws Exception
	 */
	public MessageConsumer CreateConsumerReceive(String topicName) throws Exception {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(this.username,this.password,this.url);
		connection = connectionFactory.createConnection();
		connection.start();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic(topicName);
		consumer = session.createConsumer(topic);
		return consumer;
	}
}
