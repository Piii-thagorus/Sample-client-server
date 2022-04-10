package client.server.client;

import java.net.Socket;
import java.io.*;
import java.util.Scanner;
public class Main{
    
    public static void main(String[] args){
        try{
            Socket socket = new Socket("localhost", 5000);  

            PrintWriter output = new PrintWriter( socket.getOutputStream(), true );

            Scanner scanner = new Scanner(System.in);

            String userInput, clientName = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            ClientThread clientThread = new ClientThread(socket);

            //clientThread.start();
            (new Thread(clientThread)).start();

            do{
                while(clientName == ""){
                    System.out.println("Enter Name");
                    clientName = scanner.nextLine();
                    output.println(clientName);
                }

                String message = "(" + clientName + ") message: "; 
                System.out.println(message);
                userInput = in.readLine();
                output.println(message + userInput);


            }while(!userInput.equalsIgnoreCase("exit"));
            scanner.close();

        }catch(Exception e){
        }
    }
}
