/**
 * This class is used to deserialize JSON data from API
 */
public class WeatherAPI {

    WeatherDES[] weather;
    Main main;
    Wind wind;

    public class WeatherDES{
        int id;
        String main;
        String description;
        String icon;
    }
    public class Main {
        double temp ;
    }
    public class Wind {

        double speed;
        double deg;
    }

}
