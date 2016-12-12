package exporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExporterApplication {

    @Autowired
    private CSVExporter exporter;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ExporterApplication.class, args);
    }
}
