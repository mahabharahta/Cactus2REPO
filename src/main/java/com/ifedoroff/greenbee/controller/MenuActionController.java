package com.ifedoroff.greenbee.controller;

import com.ifedoroff.greenbee.SpringBootApplication;
import com.ifedoroff.greenbee.model.*;
import com.ifedoroff.greenbee.service.ChartService;
import com.ifedoroff.greenbee.service.RealTimeService;
import com.twilio.base.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
/**
 * Created by Rostik on 13.05.2017.
 */
@RestController
public class MenuActionController {


    //@Autowired
    //DevicesRepository devicesRepository;


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
        RealTimeService realTimeService = SpringBootApplication.ctx.getBean(RealTimeService.class);
        SensorData sensorData = realTimeService.getSearchResult(search.getAccount());
        int temperature = sensorData.getTemperature();
        int humidity = sensorData.getHumidity();
        int illumination = sensorData.getLight();
        String page = "<div class=\"all\">\n" +
                "        <table border=\"1\">\n" +
                "            <tr>\n" +
                "                <th>Номер обьекты</th>\n" +
                "                <th>Тип продукции</th>\n" +
                "                <th>Дата посадки</th>\n" +
                "                <th>Текущая температура</th>\n" +
                "                <th>Текущая влажность</th>\n" +
                "                <th>Текущая освещенность</th>\n" +
                "            </tr>\n" +
                "            <tr><td><a class= \"#greenhouseinfo\" data-id=\"Теплица 1\" onclick=\"click_info(this.getAttribute('data-id'));\"><p class=\"greenhouseinfolink\">Теплица 1</p></a></td><td>Салаты</td><td>14.05.2017</td><td>"+temperature+"</td><td>"+humidity+"</td><td>"+illumination+"</td></tr>\n" +
                "            <tr><td><a class= \"#greenhouseinfo\" data-id=\"Теплица 2\" onclick=\"click_info(this.getAttribute('data-id'));\"><p class=\"greenhouseinfolink\">Теплица 2</p></a></td><td>Помидоры</td><td>14.05.2017</td><td>"+(temperature+1)+"</td><td>"+(humidity+1)+"</td><td>"+(illumination+1)+"</td></tr>\n" +
                "            <tr><td><a class= \"#greenhouseinfo\" data-id=\"Теплица 3\" onclick=\"click_info(this.getAttribute('data-id'));\"><p class=\"greenhouseinfolink\">Теплица 3</p></a></td><td>Огурцы</td><td>14.05.2017</td><td>"+(temperature+4)+"</td><td>"+(humidity+2)+"</td><td>"+(illumination+4)+"</td></tr>\n" +
                "\n" +
                "        </table>\n" +
                "    </div>";

        respond.setResult(page);
        respond.setMsg("success");

        return  ResponseEntity.ok(respond);
    }



    @PostMapping("/api/navigation/inforeal")
    public ResponseEntity<?> getInfoUpdate(@RequestBody DevicesNameSearchCriteria search, Errors errors)
    {
        PageDataResponseBody respond = new PageDataResponseBody();
        if (errors.hasErrors())
        {
            respond.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(respond);
        }

        RealTimeService realTimeService = SpringBootApplication.ctx.getBean(RealTimeService.class);
        SensorData sensorData = realTimeService.getSearchResult(search.getAccount());
        int temperature = sensorData.getTemperature();
        int humidity = sensorData.getHumidity();
        int illumination = sensorData.getLight();
        respond.setLight(illumination);
        respond.setHumidity(humidity);
        respond.setTemperature(temperature);
        String page = "        <h1>"+search.getName()+"</h1>\n" +
                "        <div class=\"temperature infoitem\">\n" +
                "            <p class=\"index\">"+temperature+" °C</p>\n" +
                "            <p class=\"name\">Температура</p>\n" +
                "        </div>\n" +
                "        <div class=\"humidity infoitem\">\n" +
                "            <p class=\"index\">"+humidity+"%</p>\n" +
                "            <p class=\"name\">Влажность</p>\n" +
                "        </div>\n" +
                "        <div class=\"illumination infoitem\">\n" +
                "            <p class=\"index\">"+illumination+" лк</p>\n" +
                "            <p class=\"name\">Освещенность</p>\n" +
                "        </div>\n" +
                "      </div>\n";
        respond.setMsg("success");
        respond.setResult(page);
        return  ResponseEntity.ok(respond);
    }

    @PostMapping("/api/navigation/charts")
    public ResponseEntity<?> getChartData(@RequestBody DevicesSearchCriteria search, Errors errors)
    {
        ChartResponseBody respond = new ChartResponseBody();
        if (errors.hasErrors())
        {
            respond.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(respond);
        }
        ChartService chartService = SpringBootApplication.ctx.getBean(ChartService.class);

        List<Temperature> temperatures = chartService.getValues(Temperature.class,60);
        List<TemperatureChart> averageTemperature = new ArrayList();
        int cntr = 0;
        for (int i = 0; i < 6;i++)
        {
            List<Temperature> t = temperatures.subList(i,i+10);
            int sum = 0;
            for (Temperature temp : t)
            {
                sum += temp.getValue();
            }
            int average = sum/10;
            TemperatureChart c = new TemperatureChart();
            c.setTime(cntr + ":00");
            c.setTmp(average);
            averageTemperature.add(c);
            cntr+=4;
        }
        respond.setTemperatures(averageTemperature);

        List<Humidity> humidities = chartService.getValues(Humidity.class,60);
        List<HumidityChart> averageHumidity = new ArrayList();
        cntr = 0;
        for (int i = 0; i < 6;i++)
        {
            List<Humidity> t = humidities.subList(i,i+10);
            int sum = 0;
            for (Humidity temp : t)
            {
                sum += temp.getValue();
            }
            int average = sum/10;
            HumidityChart c = new HumidityChart();
            c.setTime(cntr + ":00");
            c.setHmd(average);
            averageHumidity.add(c);
            cntr+=4;
        }
        respond.setHumilities(averageHumidity);


        List<Light> lights = chartService.getValues(Light.class,60);
        List<LightChart> averageLight = new ArrayList();
        cntr = 0;
        for (int i = 0; i < 6;i++)
        {
            List<Light> t = lights.subList(i,i+10);
            int sum = 0;
            for (Light temp : t)
            {
                sum += temp.getValue();
            }
            int average = sum/10;
            LightChart c = new LightChart();
            c.setTime(cntr + ":00");
            c.setLmt(average);
            averageLight.add(c);
            cntr+=4;
        }
        respond.setLights(averageLight);

        respond.setMsg("success");
        return  ResponseEntity.ok(respond);
    }

    @PostMapping("/api/navigation/info")
    public ResponseEntity<?> getInfo(@RequestBody DevicesNameSearchCriteria search, Errors errors)
    {
        PageResponseBody respond = new PageResponseBody();
        if (errors.hasErrors())
        {
            respond.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(respond);
        }

        RealTimeService realTimeService = SpringBootApplication.ctx.getBean(RealTimeService.class);
        SensorData sensorData = realTimeService.getSearchResult(search.getAccount());
        int temperature = sensorData.getTemperature();
        int humidity = sensorData.getHumidity();
        int illumination = sensorData.getLight();
        String page = "<div class=\"greenhouseinfo\" id=\"greenhouseinfo\">\n" +
                "        <h1>"+search.getName()+"</h1>\n" +
                "        <div class=\"temperature infoitem\">\n" +
                "            <p class=\"index\">"+humidity+" °C</p>\n" +
                "            <p class=\"name\">Температура</p>\n" +
                "        </div>\n" +
                "        <div class=\"humidity infoitem\">\n" +
                "            <p class=\"index\">"+temperature+"%</p>\n" +
                "            <p class=\"name\">Влажность</p>\n" +
                "        </div>\n" +
                "        <div class=\"illumination infoitem\">\n" +
                "            <p class=\"index\">"+illumination+" лк</p>\n" +
                "            <p class=\"name\">Освещенность</p>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "        <div class=\"hrdiv\"><hr></div>\n" +
                "        <div class=\"gaugetemperature\"></div>\n" +
                "        <div class=\"gaugehumidity\"></div>\n" +
                "        <div class=\"gaugeillumination\"></div>\n" +
                "        <div class=\"hrdiv2\"><hr></div>\n" +
                "      <div id=\"chart-demo\">\n" +
                "            <div id=\"temperaturechart\"></div>\n" +
                "            <div id=\"humiditychart\"></div>\n" +
                "            <div id=\"illuminationchart\"></div>\n" +
                "            <div class=\"center\">\n" +
                "                <div id=\"types\"></div>\n" +
                "            </div>\n" +
                "     <div id=\"map\"></div>\n" +
                "     <div id=\"show-tooltips\"></div>"+
                "      <button onclick=\"make_pdf()\">Test</button>";
        respond.setMsg("success");
        respond.setResult(page);
        return  ResponseEntity.ok(respond);
    }

    @PostMapping("api/pdf/create")
    public  ResponseEntity formPdf(@RequestBody DevicesNameSearchCriteria searchCriteria, Errors errors)
    {
        PageResponseBody response = new PageResponseBody();
        ChartService chartService = SpringBootApplication.ctx.getBean(ChartService.class);
        Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
                Font.BOLD);

        Document document = new Document();
        String file = "d:\\Local\\" + searchCriteria.getName() + ".pdf";
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            Paragraph preface = new Paragraph();
            preface.add(new Paragraph(" "));
            // Lets write a big header
            preface.add(new Paragraph("Report", catFont));
            preface.add(new Paragraph("Account : " + searchCriteria.getAccount() , catFont));
            preface.add(new Paragraph("Name : " + searchCriteria.getName() , catFont));

            preface.add(new Paragraph((" ")));

            preface.add(new Paragraph("Temperatures : " , catFont));
            preface.add(new Paragraph((" ")));

            List<Temperature> temperatures = chartService.getValues(Temperature.class, 10);
            for (Temperature t : temperatures)
            {
                preface.add(new Paragraph("{ date = " +  t.getDate() + ", value = " + t.getValue() + "}" , catFont));
                preface.add(new Paragraph((" ")));
            }

            preface.add(new Paragraph("Light : " , catFont));
            preface.add(new Paragraph((" ")));

            List<Light> lights = chartService.getValues(Light.class, 10);
            for (Light t : lights)
            {
                preface.add(new Paragraph("{ date = " +  t.getDate() + ", value = " + t.getValue() + "}" , catFont));
                preface.add(new Paragraph((" ")));
            }


            preface.add(new Paragraph("Humidity : " , catFont));
            preface.add(new Paragraph((" ")));
            List<Humidity> humidities = chartService.getValues(Humidity.class, 10);
            for (Humidity t : humidities)
            {
                preface.add(new Paragraph("{ date = " +  t.getDate() + ", value = " + t.getValue() + "}" , catFont));
                preface.add(new Paragraph((" ")));
            }
            document.add(preface);
            document.close();
            System.out.println("Ended form document");
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            response.setMsg("bad");
            return ResponseEntity.badRequest().body(response);
        }

        response.setMsg("success");
        response.setResult("sucess");
        return  ResponseEntity.ok(response);
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
                "                <p>Теплица 1 </a></p>\n" +
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
