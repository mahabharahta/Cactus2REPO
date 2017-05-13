package com.ifedoroff.greenbee;

import org.springframework.boot.SpringApplication;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by Rostik on 13.05.2017.
 */
@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootApplication.class, args);
        System.out.println("Starting server");
        DataServer.run();
    }
}
