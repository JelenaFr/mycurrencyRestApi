package boost;

//import boost.model.Currency;
import boost.model.CurrencyRate;
import boost.repo.CurrencyRateRepo;
import boost.service.CurrencyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.internal.org.objectweb.asm.TypeReference;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        String rawJson  = new RestTemplate().getForObject("https://api.exchangeratesapi.io/latest", String.class);
        System.out.println(rawJson);

    }


    @Bean
    CommandLineRunner runner(CurrencyService currencyService){
        return args -> {


            ObjectMapper mapper = new ObjectMapper();
            String rawJson  = new RestTemplate().getForObject("https://api.exchangeratesapi.io/latest", String.class);

            com.fasterxml.jackson.core.type.TypeReference<ArrayList<CurrencyRate>> typeReference = new com.fasterxml.jackson.core.type.TypeReference<ArrayList<CurrencyRate>>(){};
            //InputStream inputStream = TypeReference.class.getResourceAsStream("/json/rates.json");
            InputStream inputStream = TypeReference.class.getResourceAsStream(rawJson);
            try{
                ArrayList<CurrencyRate> rates = mapper.readValue(inputStream, typeReference);
                currencyService.save(rates);
                System.out.println("Rates saved!");
            }catch (IOException e){
                System.out.println("Unable to save rates: "+ e.getMessage());
            }

        };
    }

}
