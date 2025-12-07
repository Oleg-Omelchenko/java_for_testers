package tests;

import common.CommonFunc;
import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Properties;

public class TestBase {
    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() throws IOException {
        if (app == null) {
            var properties = new Properties();
            properties.load(new FileReader(System.getProperty("target","local.properties")));
            app = new ApplicationManager();
            app.init(System.getProperty("browser","firefox"), properties);
        }
    }

    public static String randomFile (String dir){
        var fileNames = new File(dir).list();
        var rnd = new Random();
        var index = rnd.nextInt(fileNames.length);
        return Paths.get(dir,fileNames[index]).toString();
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
        result = result + CommonFunc.randomString(10) + " ";
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
