package com.ifedoroff.greenbee.controller;

import com.ifedoroff.greenbee.model.DevicesRepository;
import com.ifedoroff.greenbee.model.DevicesSearchCriteria;
import com.ifedoroff.greenbee.model.PageResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rostik on 13.05.2017.
 */
@RestController
public class MenuActionController {


    @Autowired
    DevicesRepository devicesRepository;

    @PostMapping("/api/navigation/all")
    public ResponseEntity<?> getListHouses(@RequestBody DevicesSearchCriteria search, Errors errors)
    {
        System.out.println(search.getAccount() + " request");
        PageResponseBody respond = new PageResponseBody();
        if (errors.hasErrors())
        {
            respond.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(respond);
        }

        //List<Device> devices = devicesRepository.findAll();
        String page = "<div class=\"all\">\n" +
                "        <table border=\"1\">\n" +
                "            <tr>\n" +
                "                <th>Номер обьекты</th>\n" +
                "                <th>Тип продукции</th>\n" +
                "                <th>Дата посадки</th>\n" +
                "                <th>Текущая температура</th>\n" +
                "                <th>Текущая влажность</th>\n" +
                "            </tr>\n" +
                "            <tr><td>Теплица 1</td><td>Салаты</td><td>21.02.2017</td><td>23</td><td>80</td></tr>\n" +
                "            <tr><td>Теплица 2</td><td>Салаты</td><td>21.02.2017</td><td>23</td><td>80</td></tr>\n" +
                "            <tr><td>Теплица 3</td><td>Салаты</td><td>21.02.2017</td><td>23</td><td>80</td></tr>\n" +
                "            <tr><td>Теплица 4</td><td>Салаты</td><td>21.02.2017</td><td>23</td><td>80</td></tr>\n" +
                "            <tr><td>Теплица 5</td><td>Салаты</td><td>21.02.2017</td><td>23</td><td>80</td></tr>\n" +
                "            <tr><td>Теплица 6</td><td>Салаты</td><td>21.02.2017</td><td>23</td><td>80</td></tr>\n" +
                "            <tr><td>Теплица 7</td><td>Салаты</td><td>21.02.2017</td><td>23</td><td>80</td></tr>\n" +
                "            <tr><td>Теплица 8</td><td>Салаты</td><td>21.02.2017</td><td>23</td><td>80</td></tr>\n" +
                "            <tr><td>Теплица 9</td><td>Салаты</td><td>21.02.2017</td><td>23</td><td>80</td></tr>\n" +
                "\n" +
                "        </table>\n" +
                "    </div>";
        // build page

        respond.setResult(page);
        respond.setMsg("success");

        return  ResponseEntity.ok(respond);
    }

    @PostMapping("/api/navigation/feed")
    public ResponseEntity<?> getFeedback(@RequestBody DevicesSearchCriteria search, Errors errors)
    {
        System.out.println(search.getAccount() + " request");
        PageResponseBody respond = new PageResponseBody();
        if (errors.hasErrors())
        {
            respond.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(respond);
        }

            //List<Device> devices = devicesRepository.findAll();
        String page = " <div class=\"feed\">\n" +
                "        <ul class = \"feedmenu\">\n" +
                "            <li class=\"greenhouseitem\">\n" +
                "                <p>Теплица 1</p>\n" +
                "            </li>\n" +
                "            <li class=\"greenhouseitem\">\n" +
                "                <p>Теплица 2</p>\n" +
                "            </li>\n" +
                "            <li class=\"greenhouseitem\">\n" +
                "                <p>Теплица 3</p>\n" +
                "            </li>\n" +
                "            <li class=\"greenhouseitem\">\n" +
                "                <p>Теплица 4</p>\n" +
                "            </li>\n" +
                "            <li class=\"greenhouseitem\">\n" +
                "                <p>Теплица 5</p>\n" +
                "            </li>\n" +
                "            <li class=\"greenhouseitem\">\n" +
                "                <p>Теплица 6</p>\n" +
                "            </li>\n" +
                "            <li class=\"greenhouseitem\">\n" +
                "                <p>Теплица 7</p>\n" +
                "            </li>\n" +
                "            <li class=\"greenhouseitem\">\n" +
                "                <p>Теплица 8</p>\n" +
                "            </li>\n" +
                "        </ul>\n" +
                "</div>";
        // build page

        respond.setResult(page);
        respond.setMsg("success");

        return  ResponseEntity.ok(respond);
    }
}
