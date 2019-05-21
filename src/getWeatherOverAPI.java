import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Get weather from Open Weather API and parse it into Java Object
 */

public class getWeatherOverAPI {
        Gson gson = new Gson();
        String cityID = "276781" ;
        String UNITS = "&units=metric";
        String FORMAT = "&mode=json";
        String JSONstr ="";
        String line = "";
        private String APIKey = "b550241d1856b3769f5e5ce8cd8cdc4c";
         String WEATHER_IMAGE_BASE_URL = "http://openweathermap.org/img/w/";
        private String api_weather = "http://api.openweathermap.org/data/2.5/weather?id="+cityID+"&APPID="+ APIKey + UNITS +FORMAT;
        //http://api.openweathermap.org/data/2.5/weather?id=276781&APPID=b550241d1856b3769f5e5ce8cd8cdc4c&units=metric
        URL url_api = null;

        {
            try {
                url_api= new URL(api_weather);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        HttpURLConnection con = null;

        {
            try {
                con = (HttpURLConnection) url_api.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedReader reader;

        {
            try {
                reader = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                while( (line = reader.readLine()) != null ){
                    JSONstr += line;
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    /**
     * creating instance of weatherAPI which transform the JSON from API to a WeatherAPI class
     */
    WeatherAPI weatherobj = gson.fromJson(JSONstr, WeatherAPI.class);


}
