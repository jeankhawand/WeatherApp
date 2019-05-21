import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;


/**
 * MainViewController class provides link between the GUI and Local variables
 */

public class MainViewController {
    /**
     * used to show up the a File Chooser
     * used to show up the a File Chooser
     */
    @FXML
    private Button btnFilePicker;

    /**
     * used to show the file's absolute path
     */

    @FXML
    private Label lblPath;

    /**
     * that calls the function which return result for a specific input values
     */
    @FXML
    private Button btnSearch;

    /**
     * used to enter Temperature
     */
    @FXML
    private TextField tfTemperature;

    /**
     * used to enter TotalPrecipitation
     */
    @FXML
    private TextField tfTotalPrecipitation;

    /**
     * used to enter WindSpeed
     */
    @FXML
    private TextField tfWindSpeed;

    /**
     * used to enter WindDirection
     */
    @FXML
    private TextField tfWindDirection;

    /**
     * used to enter Hours
     */
    @FXML
    private TextField tfHours;

    /**
     * used to enter Minutes
     */
    @FXML
    private TextField tfMinutes;

    /**
     * used to enter Days
     */
    @FXML
    private TextField tfDay;

    /**
     * used to enter Year
     */
    @FXML
    private TextField tfYear;

    /**
     * used to enter Month
     */
    @FXML
    private TextField tfMonth;

    /**
     * is a blueprint of the class ReadCSV
     */
    private ReadCSV csv;

    /**
     * is a blueprint of the class ReadCSV
     */
    private ReadJSON json;

    /**
     * is a blueprint of the class File
     */
    private File f;

    /**
     * get temperature over json api
     */
    @FXML
    private TextField temp_api;

    /**
     * get windDirection over json api
     */
    @FXML
    private TextField windDirectionAPI;

    /**
     * get windSpeed over json api
     */
    @FXML
    private TextField windSpeedAPI;

    /**
     * get weather icon state over json api
     */
    @FXML
    private ImageView weatherICON;

    /**
     * the weather data extracted from the browsed file
     */
    private ArrayList<Line> weatherDatalist;

    /**
     * initialize function provide weather data when apps starts
     */
    @FXML
    void initialize() {
        String projectPath = new File("").getAbsolutePath();

        getWeatherOverAPI WeatherApi = new getWeatherOverAPI();
        temp_api.setText(String.valueOf(WeatherApi.weatherobj.main.temp));
        windDirectionAPI.setText(String.valueOf(WeatherApi.weatherobj.wind.deg));
        windSpeedAPI.setText(String.valueOf(WeatherApi.weatherobj.wind.speed));
        weatherICON.setImage(new Image(WeatherApi.WEATHER_IMAGE_BASE_URL + WeatherApi.weatherobj.weather[0].icon + ".png"));

        switch(WeatherApi.weatherobj.weather[0].main.toLowerCase()){
            case "thunderstorm":
                try{
                    File audio = new File("media/Storm.wav");
                    AudioInputStream audioInput = AudioSystem.getAudioInputStream(audio);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInput);
                    clip.start();
                }catch(Exception e){
                    e.printStackTrace();
                }
                break;

            case "rain":
                try{
                    File audio = new File("media/Rain.wav");
                    AudioInputStream audioInput = AudioSystem.getAudioInputStream(audio);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInput);
                    clip.start();
                }catch(Exception e){
                    e.printStackTrace();
                }
                break;

            default:
                try{
                    File audio = new File("media/Birds.wav");
                    AudioInputStream audioInput = AudioSystem.getAudioInputStream(audio);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInput);
                    clip.start();
                }catch(Exception e){
                    e.printStackTrace();
                }
                break;
        }
    }

    /**
     * This function shows a File chooser window which limits the selection of the file to CSV or JSON File only
     * and calls the respective Read class associated with it
     */
    @FXML
    void fileReader() {

        FileChooser fc = new FileChooser();
        // filter windows chooser for csv and json
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON file (*.json)", "*.json"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV file (*.csv)", "*.csv"));
        f = fc.showOpenDialog(null); // show pop up file picker

        if (f == null) {
            return;
        }

        lblPath.setText(f.getAbsolutePath());
        if (f.getName().matches("(.*).csv")) {
            csv = new ReadCSV(f.getAbsolutePath());
            weatherDatalist = csv.GetWeatherDatalist();
        } else if (f.getName().matches("(.*).json")) {
            json = new ReadJSON(f.getAbsolutePath());
            weatherDatalist = json.GetWeatherDatalist();
        }
    }

    /**
     * this function searches the weather Datalist based on the user's input
     */
    @FXML
    void search() {
        if (weatherDatalist == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("No file selected");
            alert.setContentText("please browse and enter csv or json file");
            alert.showAndWait();
            fileReader();
            return;
        }
        int year = -1, month = -1, day = -1, hour = -1, minute = -1;
        try {
            year = Integer.parseInt(tfYear.getText());
        } catch (Exception e) {
        }

        try {
            month = Integer.parseInt(tfMonth.getText());
        } catch (Exception e) {
        }

        try {
            day = Integer.parseInt(tfDay.getText());
        } catch (Exception e) {
        }

        try {
            hour = Integer.parseInt(tfHours.getText());
        } catch (Exception e) {
        }

        try {
            minute = Integer.parseInt(tfMinutes.getText());
        } catch (Exception e) {
        }

        searchResult output = searchResult.SearchDataList(weatherDatalist, year, month, day, hour, minute);
        tfTemperature.setText(String.valueOf(output.temperature));
        tfTotalPrecipitation.setText(String.valueOf(output.totalPrecipitation));
        tfWindDirection.setText(String.valueOf(output.windDirection));
        tfWindSpeed.setText(String.valueOf(output.windSpeed));
    }

    /**
     * This function clears All Text Fields
     */
    @FXML
    void Clear() {
        tfWindDirection.clear();
        tfTemperature.clear();
        tfTotalPrecipitation.clear();
        tfWindDirection.clear();
        tfWindSpeed.clear();
        tfDay.clear();
        tfHours.clear();
        tfMinutes.clear();
        tfMonth.clear();
        tfYear.clear();
    }

}


