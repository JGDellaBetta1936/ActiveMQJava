package com.jg.queues;
/*
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;*/

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Destination;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageSender {

	// URL of the JMS server. DEFAULT_BROKER_URL will just mean that JMS server is
	// on localhost
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	private static String subject = "JCG_QUEUE";
	private static String TEXT = "JJGDahLILaQueue";

	public static void main(String[] args) throws JMSException {

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		Destination destination = session.createQueue(subject);

		MessageProducer producer = session.createProducer(destination);

		int min = 0;
		int max = 32;
		System.out.println("Random value of type int between " + min + " to " + max + ":");

		StringBuilder sb = new StringBuilder();
		int count = 0;
		while (count < 6) {
			int b = (int) (Math.random() * (max - min + 1) + min);
			System.out.println(b);
			sb.append(b);
			sb.append(":");
			count++;
		}

	TextMessage message = session.createTextMessage(sb.toString());

	// TextMessage message = session.createTextMessage("Hello !!! I am a happy
	// puppy. Just wanted to let you know I love you and miss you so much.");

	// Here we are sending our message!
	producer.send(message);
	// producers.send(message );

	System.out.println("MessageSender-->"+message.getText()+"'");connection.close();
	}

	/**
	 * @return the subject
	 */
	public static String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public static void setSubject(String subject) {
		MessageSender.subject = subject;
	}

	/**
	 * @return the tEST
	 */
	public static String getText() {
		return TEXT;
	}

	/**
	 * @param tEST the tEST to set
	 */
	public static void setTEST(String text) {
		TEXT = text;
	}

	/**
	 * @return the url
	 */
	public static String getUrl() {
		return url;
	}
}
