package boost;

//import boost.model.Currency;
import boost.repo.CurrencyRateRepo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        String rawJson  = new RestTemplate().getForObject("https://api.exchangeratesapi.io/latest", String.class);
        //System.out.println(rawJson);
        JSONObject root = new JSONObject(rawJson);
        JSONArray rates = root.getJSONArray("rates");
        for (int i = 0; i < rates.length(); i++) {
            JSONObject jsonRate = rates.getJSONObject(i);
            CurrencyRateRepo rate = new CurrencyRateRepo();
            System.out.println();

        }
        //System.out.println(root);
    }
}
