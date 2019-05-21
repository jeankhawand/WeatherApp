import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * This class is used to deserialize JSON data from a JSON file and assign its values to an ArrayList of Type Line
 */
public class ReadJSON {
    ArrayList<Line> weatherDatalist;
    Gson gson = new Gson();

    /**
     * this constructor provides reading file, parsing it to json array and as gson doesn't provide generic types which in this case we used
     * array list of type line, we had to create a type token of type array list and then use the fromjson() to extract the data
     * @param file file used to read weather data
     */
    ReadJSON(String file) {
        weatherDatalist = new ArrayList<>();
        try {
            JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(file)));
            JsonParser jsonParser = new JsonParser();
            /**
             *  parse it to jsonarray since the following json is a root object
             */
            JsonArray weatherarray= jsonParser.parse(reader).getAsJsonArray();
            /**
             * Create generic type
             */
            Type type = new TypeToken<ArrayList<Line>>() {}.getType();
            /**
             * creating instance of weatherDatalist which transform the JSON from the file to a type of ArrayList
             */
            weatherDatalist = gson.fromJson(weatherarray, type);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the weatherDataList of type ArrayList
     * @return An ArrayList of type Line containing Weather Data
     */
    ArrayList<Line> GetWeatherDatalist(){
        return weatherDatalist;
    }
}
