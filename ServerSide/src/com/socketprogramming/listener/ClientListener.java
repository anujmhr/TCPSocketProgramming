/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socketprogramming.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author anuz
 */
public class ClientListener extends Thread {

    private Socket clientSocket;

    public ClientListener(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        
        try{
        PrintStream out = new PrintStream(clientSocket.getOutputStream());
        out.println("welcome to the calculator server");

        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String line;

        while ((line = reader.readLine()) != null) {
            try {
                System.out.println(line);
                if (line.equalsIgnoreCase("exit")) {
                    break;
                }

                ScriptEngineManager mgr = new ScriptEngineManager();
                ScriptEngine engine = mgr.getEngineByName("JavaScript");
                System.out.println("=>" + engine.eval(line));
                out.println("=>" + engine.eval(line));
                System.out.println("to:"+clientSocket.getInetAddress());

            } catch (ScriptException ex) {
                System.out.println(ex.getMessage());
            }
        }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

}
