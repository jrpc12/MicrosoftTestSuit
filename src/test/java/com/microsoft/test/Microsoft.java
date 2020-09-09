package com.microsoft.test;

import java.io.FileReader;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.microsoft.bean.Config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.*;


public class Microsoft {

    public WebDriver driver;
    public Config config;
    public static Logger logger;

    public Microsoft(){
        initDriver();
        initConfigValues();
        logger = Logger.getObjectInstance();
    }
    

    public void initDriver(){
        
        System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");        
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    public void initConfigValues() {

        try{
            Gson gson = new Gson();
            config = gson.fromJson(new FileReader("src/main/resources/config.json"), Config.class);
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

    public void closeDriver(){
        driver.close();
    }
    
    

}
