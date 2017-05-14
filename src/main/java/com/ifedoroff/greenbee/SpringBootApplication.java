package com.ifedoroff.greenbee;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

/**
 * Created by Rostik on 13.05.2017.
 */
@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {

    public static ApplicationContext ctx;
    public static final String ACCOUNT_SID = "AC752e49fdd2d76c85704efddde1387514";
    public static final String AUTH_TOKEN = "4a1320a56a0a35616300c5f188979d4b";
    public  static  final  String FROM = "+19728933223";
    public static void main(String[] args) throws Exception {
        ctx = SpringApplication.run(SpringBootApplication.class, args);



        /*String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
        */

        DataServer.run();
    }
}
