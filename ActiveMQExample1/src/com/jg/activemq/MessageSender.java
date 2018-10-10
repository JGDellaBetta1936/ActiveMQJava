package com.jg.activemq;
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
	
	//URL of the JMS server. DEFAULT_BROKER_URL will just mean that JMS server is on localhost
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	// default broker URL is : tcp://localhost:61616"
	private static String subject = "JCG_QUEUE"; // Queue Name.You can create any/many queue names as per your requirement.
	private static String TEXT = "JJGDahLILaQueue"; // Queue Name.You can create any/many queue names as per your requirement.
	
	public static void main(String[] args) throws JMSException {		
		// Getting JMS connection from the server and starting it
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		connection.start();
		
		//Creating a non transactional session to send/receive JMS message.
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);	
		
		//Destination represents here our queue 'JCG_QUEUE' on the JMS server. 
		//The queue will be created automatically on the server.
		Destination destination = session.createQueue(subject);	
		Destination destinationUnknown = session.createQueue(TEXT);	
		
		// MessageProducer is used for sending messages to the queue.
		MessageProducer producer = session.createProducer(destination);
		MessageProducer producers = session.createProducer(destinationUnknown);
		
		// We will send a small text message saying 'Hello World!!!' 
		TextMessage message = session
				.createTextMessage("Hello !!! I am a happy puppy.  Just wanted to let you know I love you and miss you so much.");
		
		// Here we are sending our message!
		producer.send(message);
		producers.send(message );
		
		System.out.println("MessageSender-->" + message.getText() + "'");
		connection.close();
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
