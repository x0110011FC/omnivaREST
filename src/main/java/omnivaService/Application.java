package omnivaService;

import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

@SpringBootApplication
@ClientCacheApplication(name = "AccessingGemFireDataRestApplication", logLevel = "error")
@EnableEntityDefinedRegions(basePackageClasses = Invoice.class,
        clientRegionShortcut = ClientRegionShortcut.LOCAL)
@EnableGemfireRepositories
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ApplicationRunner run(InvoiceRepository invoiceRepository) {
        return args -> {

            String message = "Invoice payed - ";
            Invoice invoice1 = new Invoice(2029998099091L, "Sam", "Jones", true, "Nike snikers");
            Invoice invoice2 = new Invoice(2029998099092L, "Mike", "Stone", false, "Excel for dummies");
            Invoice invoice3 = new Invoice(2029998099093L, "Paul", "Waterproof", true, "Sprite");

            invoiceRepository.save(invoice1);
            invoiceRepository.save(invoice2);
            invoiceRepository.save(invoice3);

            invoiceRepository.findAll().forEach(t -> System.out.println(t.toString()));
        };
    }
}


