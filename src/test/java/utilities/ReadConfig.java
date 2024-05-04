package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

    private final Properties properties;

    public ReadConfig() throws IOException {

        File src = new File("src/test/java/properties/config.properties");

        FileInputStream fis = new FileInputStream(src);

        properties = new Properties();
        properties.load(fis);

    }

    public String getBrowser() {

        return properties.getProperty("BROWSER");

    }

    public String getBaseURL() {

        return properties.getProperty("BASE_URL");

    }

    public String getUsername() {

        return properties.getProperty("USERNAME");

    }

    public String getPassword() {

        return properties.getProperty("PASSWORD");

    }

}
