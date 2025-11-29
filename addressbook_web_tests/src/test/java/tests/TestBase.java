package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;

public class TestBase {
    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
        }
        app.init(System.getProperty("browser","firefox"));
    }
    public static String randomString (int n){
        var rnd = new Random();
        var result = "";
        for (int i = 0; i< n ; i++) {
            result = result + (char)('a' + rnd.nextInt(26));
        }
        return result;
    }

    public static String randomMobile(int n){
        var rnd = new Random();
        var result = "+";
        for (int i = 0; i< n ; i++) {
            result = result + (char)('0' + rnd.nextInt(10));
        }
        return result;
    }

    public static String randomAddress (){
        var rnd = new Random();
        var result = "";
        result = result + randomString(10) + " ";
        for (int j = 1; j < 4 ; j++) {
                result = result + (char)('0' + rnd.nextInt(10));
            }
        return result;
    }
    public static String randomEmail (){
        var rnd = new Random();
        var result = "";
        for (int i = 0; i< 7 ; i++) {
            result = result + (char)('a' + rnd.nextInt(26));}
        result = result + "@test.ru";
        return result;
    }

}
