package boost;


import boost.jsonobject.JsonCurrencyResponse;
import boost.model.Currency;
import boost.model.CurrencyRate;
import boost.repo.CurrencyRateRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.util.Map;


@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(CurrencyRateRepo currencyRateRepo) {
        return args -> {
            String rawJson = new RestTemplate().getForObject("https://api.exchangeratesapi.io/latest", String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonCurrencyResponse jsonResponse = mapper.readValue(rawJson, JsonCurrencyResponse.class);

            for (Map.Entry<String, BigDecimal> entries : jsonResponse.getRates().entrySet()) {

                currencyRateRepo.save(new CurrencyRate(new Currency(entries.getKey(), entries.getValue()), jsonResponse.getBase(), jsonResponse.getDate()));
            }
        };
    }
}
