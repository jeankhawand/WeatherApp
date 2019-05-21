import java.util.ArrayList;

/**
 * This class is used to search the weather data and return the corresponding temperature, total precipitation,
 * wind speed and wind direction in an object called searchResult
 */

public class searchResult {
    double temperature;
    double totalPrecipitation;
    double windSpeed;
    double windDirection;

    /**
     * Search Result Constructor, assign all the attributes the value 0
     */
    searchResult(){
        temperature = 0;
        totalPrecipitation = 0;
        windDirection = 0;
        windSpeed = 0;
    }

    /**
     * Searches the Weather Data and returns result based on user Input
     * @param weatherDatalist Weather Data used
     * @param year  inserted by the user or empty (= -1)
     * @param month inserted by the user or empty (= -1)
     * @param day   inserted by the user or empty (= -1)
     * @param hour  inserted by the user or empty (= -1)
     * @param minute    inserted by the user or empty (= -1)
     * @return Search Result as one object (temperature, total precipitation, wind direction, wind speed)
     */
    static searchResult SearchDataList(ArrayList<Line> weatherDatalist,int year, int month, int day, int hour, int minute){
        searchResult output = new searchResult();

        int numberOfMatchingLines = 0;
        for (Line l: weatherDatalist) {
            if (l.year == year || year == -1){
                if (l.month == month || month == -1){
                    if (l.day == day || day == -1){
                        if (l.hour == hour || hour == -1){
                            if (l.minute == minute || minute == -1){
                                numberOfMatchingLines++;
                                output.temperature += l.temperature;
                                output.totalPrecipitation += l.totalPrecipitation;
                                output.windDirection += l.windDirection;
                                output.windSpeed += l.windSpeed;
                            }
                        }
                    }
                }
            }
        }

        output.temperature /= numberOfMatchingLines;
        output.totalPrecipitation /= numberOfMatchingLines;
        output.windDirection /= numberOfMatchingLines;
        output.windSpeed /= numberOfMatchingLines;
        return output;
    }
}
