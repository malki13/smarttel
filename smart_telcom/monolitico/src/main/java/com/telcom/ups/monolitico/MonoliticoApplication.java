package com.telcom.ups.monolitico;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackages = "com.telcom.ups.data.entities")
public class MonoliticoApplication implements CommandLineRunner {

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Guayaquil"));
    }

    public static void main(String[] args) {
        SpringApplication.run(MonoliticoApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void run(String... args)  {
//        System.out.println("INICIE");
//        try (CloseableHttpClient httpClient = ClientWebsocket.setViaSocketFactory();
//             CloseableHttpResponse response = httpClient.execute(
//                     new HttpGet("http://104.198.187.189:8080/api/gateways/3235313254003200/frames")
//                     //new HttpGet("http://104.198.187.189:8080/api/devices/250fa0b502000000/events")
//                     //new HttpGet("http://104.198.187.189:8080/api/devices/250fa0b502000000/events")
//             )) {
//            HttpEntity entity = response.getEntity();
//            //System.out.println(entity);
//            EntityUtils.consume(entity);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


}
