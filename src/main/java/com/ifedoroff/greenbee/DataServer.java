package com.ifedoroff.greenbee;

import com.ifedoroff.greenbee.model.HumidityDataRepository;
import com.ifedoroff.greenbee.model.TemperatureDataRepository;
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

    @Autowired
    private static TemperatureDataRepository temperatureDataRepository;

    @Autowired
    private static HumidityDataRepository humidityDataRepository;

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
        public void handle(String input)
        {
            // handle logic
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
                    handle(input);
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
