package com.kevinyin.learnsao.activityMQ;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by kevinyin on 2017/8/1.
 */
public class Receiver {

    public static void main(String[] args) {
        //连接工厂
        ConnectionFactory connectionFactory;
        //Provider 的连接
        Connection connection = null;

        Session session;
        //消息发送者
        Destination destination;
        MessageConsumer consumer;

        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER
                ,ActiveMQConnectionFactory.DEFAULT_PASSWORD,"tcp://192.168.137.155:61616");
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            //获取操作连接
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("FirstQueue");
            consumer = session.createConsumer(destination);
            while (true){
                TextMessage message = (TextMessage) consumer.receive(100000);
                if (null != message){
                    System.out.println("接收消息: " + message.getText());
                }else {
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                }
            }
        }
    }

}
