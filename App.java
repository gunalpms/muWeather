import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App implements ActionListener{

    JLabel temperature;
    JLabel pressure;
    JLabel humidity;
    JLabel wind_speed;
    JLabel wind_degree;
    JLabel weather_description;
    JLabel city_name;

    String description = "";
    String[] weatherData;
    JFrame frame; // main window
    JTextField textField; // city enter field
    JPanel panel; // main panel
    JLabel cityName; // no city entered default text and city name text
    JButton citySearch; // city search button

    Font font = new Font("Segoe UI", Font.PLAIN, 25); // title font
    Font smallFont = new Font("Segoe UI", Font.PLAIN, 18); // smaller font
    
    App() {

        // frame setup
        frame = new JFrame("muWeather");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 650);
        frame.setLayout(null);

        // search bar setup
        textField = new JTextField();
        textField.setBounds(75, 25, 400, 50);
        textField.setFont(font);

        // city name label setup
        cityName = new JLabel("No city entered");
        cityName.setBounds(75, 150, 400, 50);
        cityName.setFont(font);

        // city search button setup
        citySearch = new JButton("search city");
        citySearch.addActionListener(this);
        citySearch.setBounds(215, 100, 120, 40);
        citySearch.setFont(smallFont);
        citySearch.setFocusable(false);


        // weather data labels
        temperature = new JLabel("temp (C): ");
        pressure = new JLabel("pressure (kPa): ");
        humidity = new JLabel("humidity (%): ");
        wind_speed = new JLabel("wind speed (km/h): ");
        wind_degree = new JLabel("wind direction (degrees): ");
        weather_description = new JLabel("weather description: ");

        temperature.setBounds(75, 200, 300, 50);
        temperature.setFont(smallFont);

        pressure.setBounds(75, 250, 300, 50);
        pressure.setFont(smallFont);

        humidity.setBounds(75, 300, 300, 50);
        humidity.setFont(smallFont);

        wind_speed.setBounds(75, 350, 300, 50);
        wind_speed.setFont(smallFont);
        
        wind_degree.setBounds(75, 400, 300, 50);
        wind_degree.setFont(smallFont);
        
        weather_description.setBounds(75, 450, 475, 50);
        weather_description.setFont(smallFont);   
    

        // frame finalize

        frame.add(citySearch);
        frame.add(textField);
        frame.add(cityName);

        frame.add(temperature);
        frame.add(pressure);
        frame.add(wind_degree);
        frame.add(wind_speed);
        frame.add(humidity);
        frame.add(weather_description);
        frame.setVisible(true);


    }

    @Override 
    public void actionPerformed(ActionEvent e) {

        // city search button event listener
        if (e.getSource()==citySearch) {
            description = "";
            try {
                String data = Api.returnData(textField.getText());
                if (data.equals("City Not Found")) {
                    cityName.setText(data);
                    temperature.setText("temp (C): ");
                    pressure.setText("pressure (kPa): ");
                    humidity.setText("humidity (%): ");
                    wind_speed.setText("wind speed (km/h): ");
                    wind_degree.setText("wind direction (degrees): ");
                    weather_description.setText("weather description: ");
                }
                else if (data != null) {
                    weatherData = data.split(" ");
                    
                    for (int i = 0; i<weatherData.length; i++) {
                        System.out.println(weatherData[i]);
                    }
                    
                    for (int i = 6; i<weatherData.length; i++) {
                        description += weatherData[i] + " ";
                    }

                    cityName.setText(textField.getText().toLowerCase() + ", " + weatherData[5].toLowerCase());
                    temperature.setText("temp (C): " + weatherData[0]);
                    pressure.setText("pressure (kPa): " + weatherData[1]);
                    humidity.setText("humidity (%): " + weatherData[2]);
                    wind_speed.setText("wind speed (km/h): " + weatherData[3]);
                    wind_degree.setText("wind direction (degrees): " + weatherData[4]);
                    weather_description.setText("weather description: " + description);
                }
            }

            catch (Exception ex) {
                System.out.println("Exception");
            }
        }
    } 
}