package di.uniba.map.b.adventure.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class is used to get the weather code and description of Joliet,
 * Illinois It uses the API of open-meteo.com
 */
public class Weather {

    /**
     * This method returns the weather code for Joliet, Illinois
     *
     * @return
     */
    public static int getWeatherCode() {
        int weatherCode = -1;  // Default value for error
        try {
            // URL per ottenere le condizioni meteo a Joliet (Illinois)
            URL url = new URL("https://api.open-meteo.com/v1/forecast?latitude=41.5250&longitude=-88.0817&current_weather=true&timezone=America%2FChicago");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();

            // Estrazione del codice delle condizioni meteo
            String weatherCodeStr = content.toString().replaceAll(".*\"weathercode\":(\\d+).*", "$1");
            weatherCode = Integer.parseInt(weatherCodeStr);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return weatherCode;
    }

    /**
     * This method maps the weather code to a readable weather description.
     *
     * @param code
     * @return
     */
    public static String getWeatherDescription(int code) {
        return switch (code) {
            case 0 ->
                "Cielo sereno";
            case 1 ->
                "Poche nuvole";
            case 2 ->
                "Nuvoloso";
            case 3 ->
                "Nuvoloso con pioggia leggera";
            case 4 ->
                "Pioggia moderata";
            case 5 ->
                "Pioggia forte";
            case 6 ->
                "Temporale";
            case 7 ->
                "Neve leggera";
            case 8 ->
                "Neve moderata";
            case 9 ->
                "Neve forte";
            case 10 ->
                "Nebbia";
            case 11 ->
                "Nuvoloso con pioggia forte";
            case 12 ->
                "Pioggia intensa";
            case 13 ->
                "Temporale con pioggia";
            case 14 ->
                "Temporale con neve";
            case 15 ->
                "Pioggia e neve";
            default ->
                "Condizione meteo sconosciuta";
        };
    }
}
