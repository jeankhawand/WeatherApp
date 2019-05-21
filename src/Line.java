import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * This class is used to put all the variables of a single line of the file read inside a single object
 */
public class Line {
    @SerializedName("Year")
            @Expose
    int year;
    @SerializedName("Month")
            @Expose
    int month;
    @SerializedName("Day")
            @Expose
    int day;
    @SerializedName("Hour")
            @Expose
    int hour;
    @SerializedName("Minute")
            @Expose
    int minute;
    @SerializedName("Temperature  [2 m above gnd]")
            @Expose
    double temperature;
    @SerializedName("Total Precipitation (high resolution)  [sfc]")
            @Expose
    double totalPrecipitation;
    @SerializedName("Wind Speed  [10 m above gnd]")
            @Expose
    double windSpeed;
    @SerializedName("Wind Direction  [10 m above gnd]")
            @Expose
    double windDirection;


}
