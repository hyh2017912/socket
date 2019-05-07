package com.viewhigh.socket.example;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.viewhigh.socket.SocketClient;
import com.viewhigh.socket.SocketServer;

class SocketExample {
    private static Scanner inputScanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        // Start as a server or a client.
        System.out.println("Please input '0' or '1' to start a server or a client.");
        System.out.println("Before starting a client, make sure a server is already running at the same PC.");
        System.out.println("  0: server");
        System.out.println("  1: client");
        System.out.println("  others: close the program.");
        String input = inputScanner.next();
        switch (input) {
            case "0":
                startServer();
                break;
            case "1":
                startClient();
                break;
            default:
                break;
        }
        inputScanner.close();
    }

    private static void startServer() {
        String msg;
        System.out.println("Start a server.");
        SocketServer server = new SocketServer(5556, new EchoHandler());

        System.out.println("When type stop and press enter to close the server...");
        while (true){
            msg = inputScanner.nextLine();
            if ("stop".equalsIgnoreCase(msg)){
                System.out.println("stop server...");
                server.close();
                break;
            }
        }
    }

    private static void startClient() throws UnknownHostException {
        String msg;
        System.out.println("Start a client.");
        SocketClient client = new SocketClient(InetAddress.getLocalHost(), 5556);

        while(true){
            System.out.println("when type stop and enter to close this client or type something to send to the server...");
            msg = inputScanner.nextLine();
            if ("stop".equalsIgnoreCase(msg)){
                System.out.println("stop client...");
                client.close();
                break;
            }
            client.println(msg);

            System.out.println("Got the following message from the server:");
            System.out.println(client.readLine());
        }

    }
}