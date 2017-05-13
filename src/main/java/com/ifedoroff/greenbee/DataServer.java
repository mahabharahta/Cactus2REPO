package com.ifedoroff.greenbee;

import com.ifedoroff.greenbee.model.HumidityDataRepository;
import com.ifedoroff.greenbee.model.TemperatureDataRepository;
import com.ifedoroff.greenbee.service.DataHandleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.out;

/**
 * Created by Rostik on 13.05.2017.
 */
public class DataServer {


    public  static  void run() throws Exception
    {
        out.println("The capitalization server is running.");
        java.net.InetAddress addr = java.net.InetAddress.getLocalHost();
        ServerSocket listener = new ServerSocket(9090,0,addr);
        try {
            while (true) {
                new Capitalizer(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }
    private static class Capitalizer extends Thread {


        private Socket socket;
        public Capitalizer(Socket socket) {
            this.socket = socket;
            log("New connection with client at " + socket);
        }

        @Autowired
        DataHandleService dataHandleService
                ;
        public void handle(String input) throws Exception
        {
            dataHandleService.addDataToBase(input);
            out.println(input);
        }
        public void run() {
            try {


                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                String input;
                while (true) {
                     input = in.readLine();
                    if (input == null || input.equals(".")) {
                        break;
                    }
                    try {
                        System.out.println(input);
                        //handle(input);
                    }
                    catch (Exception e)
                    {
                        log("Error storage data : " + e);
                    }
                }
            } catch (IOException e) {
                log("Error handling client: " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    log("Couldn't close a socket, what's going on?");
                }
                log("Connection closed");
            }
        }


        private void log(String message) {
            out.println(message);
        }
    }
}
