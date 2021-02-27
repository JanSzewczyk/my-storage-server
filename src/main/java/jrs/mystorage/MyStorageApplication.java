package jrs.mystorage;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class MyStorageApplication {

    public static void main(String[] args) {
        log.info("STARTING APPLICATION");
        SpringApplication.run(MyStorageApplication.class, args);
    }
}
