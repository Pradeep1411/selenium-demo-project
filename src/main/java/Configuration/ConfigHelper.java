package Configuration;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class ConfigHelper {
    public static String PortalEnv;
    public static String Browser;
    public static String BrowserMode;
    public static String URL;


    private static HashMap<String, String> configData = new HashMap<String, String>();

    public static void ReadConfigurationData() throws IOException {
        String projectpath = System.getProperty("user.dir");
        String configproppath = projectpath + "\\Configuration\\Configuration.properties";
        FileReader filereader = new FileReader(configproppath);
        Properties prop = new Properties();
        prop.load(filereader);
        for (Object key : prop.keySet()) {
            configData.put((String) key, prop.getProperty(key.toString()));
        }
    }

    public void SetEnvironmentData() throws IOException {
        ReadConfigurationData();
        Browser = ConfigDataValueperkey("Browser");
        BrowserMode = ConfigDataValueperkey("BrowserMode");
        PortalEnv = ConfigDataValueperkey("PortalEnv");
        URL = ConfigDataValueperkey("Swiggy_URL");


    }

    public String ConfigDataValueperkey(String key) {
        return configData.containsKey(key) ? configData.get(key) : null;
    }
}