
package com.jg.queues;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
public class ReadFromActiveMQ {

	public static void main(String[] args) throws Exception {
		 String url = ActiveMQConnection.DEFAULT_BROKER_URL;
		 String subject = "JCG_QUEUE";
		
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
			Connection connection = (Connection) connectionFactory.createConnection();
			connection.start();
		
			Session session = ((ActiveMQConnection) connection).createSession(false,
					Session.AUTO_ACKNOWLEDGE);
		
			Destination destination = session.createQueue(subject);

			MessageConsumer consumer = session.createConsumer(destination);

			Message message = consumer.receive();

			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				System.out.println("Received message '" + textMessage.getText() + "'");
			}
			  connection.close();
		}
		
		
	}
