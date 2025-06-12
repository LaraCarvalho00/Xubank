package com.xubank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        try {
            String url = "http://localhost:8080/swagger-ui.html";
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (Exception e) {
            System.err.println("❌ Erro ao abrir o navegador: " + e.getMessage());
        }
    }
}
