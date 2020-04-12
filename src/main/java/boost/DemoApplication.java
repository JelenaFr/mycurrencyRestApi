package boost;

import boost.model.Currency;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        System.out.println(new RestTemplate().getForObject("https://api.exchangeratesapi.io/latest", String.class));
    }
}
