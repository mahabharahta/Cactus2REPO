package com.ifedoroff.greenbee.controller;

/**
 * Created by Rostik on 13.05.2017.
 */
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/download")
public class DownloadReportController {

    @GetMapping("/pdf/{fileName:.+}")

    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName) {
        String path = "d:\\Local\\";
        System.out.println("Downloading file :- " + fileName);

        Path file = Paths.get(path, fileName);
        if (Files.exists(file)) {

            response.setContentType("application/pdf");
            response.setContentType("application/x-download"); response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException e) {
                System.out.println("Error :- " + e.getMessage());
            }
        } else {
            System.out.println("Sorry File not found!!!!");
        }
    }
}