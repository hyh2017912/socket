package com.viewhigh.socket.example;

import com.viewhigh.socket.Connection;
import com.viewhigh.socket.MessageHandler;

class EchoHandler implements MessageHandler {
    @Override
    public void onReceive(Connection connection, String message) {
        System.out.println("服务端接收信息：" + message);
        System.out.println("Send back the same message back to the client.");
        connection.println(message);
    }
}