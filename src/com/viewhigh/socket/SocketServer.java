package com.viewhigh.socket;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketServer {
    private ServerSocket serverSocket;
    private ListeningThread listeningThread;
    private MessageHandler messageHandler;

    /**
     * 创建一个服务端应用
     * @param port 端口
     * @param handler 信息处理器
     */
    public SocketServer(int port, MessageHandler handler) {
        messageHandler = handler;
        try {
            serverSocket = new ServerSocket(port);
            listeningThread = new ListeningThread(this, serverSocket);
            listeningThread.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void setMessageHandler(MessageHandler handler) {
        messageHandler = handler;
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }
    
    /*
     * Ready for use.
     */
    public void close() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                listeningThread.stopRunning();
                listeningThread.suspend(); // TODO 使用interepute 终端标识
                listeningThread.stop();

                serverSocket.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}