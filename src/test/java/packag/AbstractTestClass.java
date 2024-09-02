package packag;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class AbstractTestClass {

    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String username;
    private static String password;
    private static String base_url;
    //private static String full_name;

    @BeforeAll
    static void initTest() throws IOException {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        configFile = new FileInputStream("src/test/resources/autorization.properties");
        prop.load(configFile);

        username =  prop.getProperty("username");
        password = prop.getProperty("password");
        base_url = prop.getProperty("base_url");
        //full_name = prop.getProperty("full_name");

    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

//    public static String getFull_name() {
//        return full_name;
//    }

    public static String getBaseUrl() {
        return base_url;
    }
}
