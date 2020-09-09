package com.microsoft;

import com.microsoft.common.HeaderActions;

import org.openqa.selenium.WebDriver;


public abstract class Microsoft extends HeaderActions {
    
    private WebDriver driver;

    private String pageUrl;

    public Microsoft(WebDriver driver){
        super(driver);
        this.driver = driver;               
    }

    public Microsoft(WebDriver driver, String pageString){
        super(driver);
        this.driver = driver;
        this.pageUrl = pageString;
    }

    public void open(){
        driver.get(pageUrl);
    }

    public void dismissPopup(){}

    public void close(){
        driver.close();
    }

    public void setExplicitWait(int millis ){

        try{
            Thread.sleep(millis);
        }catch(Exception ex){
            System.out.println("setExplicitWait Exception: "  + ex.getMessage());
        }

    }



}
