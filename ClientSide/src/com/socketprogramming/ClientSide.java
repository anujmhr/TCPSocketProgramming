/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socketprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author anuz
 */
public class ClientSide {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        try (
                //name of the computer and the port number to which you want to connect.
                Socket socket = new Socket("localhost", 8888);
                //readers and writers to write characters over the socket.
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);//to send data to server
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));//for response from the server
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));//standard input
                
                ) {
            
            System.out.println(in.readLine());
            String userInput;
            //the loop reads a line at a time from the standard input stream and immediately sends it to the server
            while ((userInput = stdIn.readLine()) != null) {
                if(userInput.equalsIgnoreCase("exit")){
                    break;
                }
                out.println(userInput);
                System.out.println(in.readLine());
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
