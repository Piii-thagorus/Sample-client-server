package client.server.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.*;

public class Main{

    private static ExecutorService pool = Executors.newFixedThreadPool(4);
    

    public static void main(String[] args){
        
        ArrayList<ServerThread> threadList = new ArrayList<>();

        try(ServerSocket serverSocket = new ServerSocket(5000)){
            
            while(true){

                System.out.println("Waiting for client to join...");
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket, threadList);
                threadList.add(serverThread);
                serverThread.start();
                System.out.println(threadList.size() + " client/s have joined.");
            }

        
        }catch(Exception e){

        }
        
    }
}
