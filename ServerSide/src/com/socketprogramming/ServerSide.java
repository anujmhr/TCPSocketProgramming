/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socketprogramming;

import com.socketprogramming.listener.ClientListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author anuz
 */
public class ServerSide {

    /**
     * @param args the command line arguments
     * @throws javax.script.ScriptException
     */
    public static void main(String[] args) throws ScriptException {
        // TODO code application logic here
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("connection from :" + clientSocket.getInetAddress());   //display client IP connected to server

                ClientListener clientListener=new ClientListener(clientSocket);
                clientListener.start();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
