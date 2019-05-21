import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
/**
 * This class is used to read the data from CSV file and assign its values to an ArrayList of Type Line
 */
public class ReadCSV {
    ArrayList<Line> weatherDatalist;

    ReadCSV(String file){
        weatherDatalist = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int i=0;

            while (true) {
                String[] line;
                try{
                    line = reader.readLine().split(",");
                }catch (Exception e){
                    //In Case readLine returns null, Exit of the while loop
                    break;
                }

                Line newLine = new Line();

                try {
                    newLine.year = Integer.parseInt(line[0]);
                }catch (Exception e){
                    continue;
                }

                newLine.month = Integer.parseInt(line[1]);
                newLine.day = Integer.parseInt(line[2]);
                newLine.hour = Integer.parseInt(line[3]);
                newLine.minute = Integer.parseInt(line[4]);
                newLine.temperature = Double.parseDouble(line[5]);
                newLine.totalPrecipitation = Double.parseDouble(line[6]);
                newLine.windSpeed = Double.parseDouble(line[7]);
                newLine.windDirection = Double.parseDouble(line[8]);

                weatherDatalist.add(newLine);
            }

            reader.close();
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
