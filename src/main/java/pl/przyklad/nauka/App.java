package pl.przyklad.nauka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
 * to głowna adnotacja uruchamiająca aplikacje Spring Boot 
 * Laczy trzy inne adnotacje:
 * - @Configuration // specjalna wersja @Configuration – pozwala definiować beany
 * - @EnableAutoConfiguration   // włącza automatyczną konfigurację na podstawie zależności w classpath
 * - @ComponentScan   // skanuje pakiety w poszukiwaniu komponentów (@Component, @Service, @Repository, @Controller)
 * */
@SpringBootApplication
public class App 
{
    public static void main( String[] args ){
        SpringApplication.run(App.class, args);
    	System.out.println("Hello World!" );
    }
}
