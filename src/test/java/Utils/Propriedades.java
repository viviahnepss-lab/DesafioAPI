package Utils;

import java.io.*;
import java.util.Properties;

public class Propriedades {
    private static Properties propriedades = new Properties();

    public static Properties getProperties() {
        FileInputStream fileProperties;
        try {
            fileProperties = new FileInputStream("src/main/resources/Sicred.properties");
            propriedades.setProperty("path", System.getProperty("user.home"));
            InputStreamReader readerprop = new InputStreamReader(fileProperties, "UTF-8");
            propriedades.load(readerprop);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propriedades;
    }

}
