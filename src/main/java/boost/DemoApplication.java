package boost;

import boost.model.Currency;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class);
        String url = "https://api.exchangeratesapi.io/latest";

        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection)new URL(url).openConnection();
            con.setRequestMethod("GET");
            con.setUseCaches(false);
            con.setConnectTimeout(250);
            con.setReadTimeout(250);
            int status = con.getResponseCode();
            //System.out.println(status);
            con.connect();
            StringBuilder sb = new StringBuilder();
            if(HttpURLConnection.HTTP_OK == con.getResponseCode()){
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String str;
                while ((str = in.readLine())!=null){
                    sb.append(str);
                }
                System.out.println(sb.toString());
            }
            else {
                System.out.println("fail: "+con.getResponseCode()+", "+ con.getResponseMessage());
            }

        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }




    }
    //public static String parse (String responseBody){
      //  JSONArray rates = new JSONArray(responseBody);
       // for (int i = 0; i < rates.length(); i++){
         //   JSONObject rate = rates.getJSONObject(i);
            //Currency currency = rate.
            //String currency = rate.getString()
       // }
    //}
}
