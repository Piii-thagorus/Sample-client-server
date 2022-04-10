package client.server.server;

import java.net.Socket;
import java.util.ArrayList;
import java.io.*;

public class ServerThread extends Thread{

    private Socket socket;
    private  static ArrayList<ServerThread> threadList;
    private PrintWriter output;
    private BufferedReader input;
    private String userName;

    public ServerThread(Socket socket, ArrayList<ServerThread> threads) throws IOException{

        this.socket = socket;
        threadList = threads;
        input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        output = new PrintWriter(this.socket.getOutputStream(), true);
        userName = input.readLine();
        printToAllClients(userName + " has joined");
    }

    @Override
    public void run(){
        
        try{


            while(true){

                String outputString = input.readLine();

                if(outputString.equalsIgnoreCase("exit")){
                        printToAllClients(userName +  "  has disconnected"); 
                        socket.close();
                        break;
                }

               printToAllClients(outputString); 

               System.out.println("Message delivered");

            }
        
        }catch(IOException e){


        }catch(NullPointerException e){
            printToAllClients(this.userName + " has disconnected");
        }
        
        threadList.remove(this);
        
    }


    private void printToAllClients(String outputString){
        for(ServerThread sT: threadList){
            if(this != sT){
                sT.output.println(outputString);
            }
        }
    }

}
