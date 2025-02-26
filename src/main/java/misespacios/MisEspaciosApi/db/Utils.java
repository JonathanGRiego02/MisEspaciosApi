package misespacios.MisEspaciosApi.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

    private final String CONFIG_FILE = "db.properties";
    private Properties properties = new Properties();

    public Utils() {
        loadProperties();
    }

    public void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}