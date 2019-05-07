package com.viewhigh.socket;

public interface MessageHandler {
    public void onReceive(Connection connection, String message);
}