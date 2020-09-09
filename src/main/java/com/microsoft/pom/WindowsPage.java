package com.microsoft.pom;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.Microsoft;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WindowsPage extends Microsoft {


    private WebDriver driver;
    By windows10_menu = By.id("c-shellmenu_52");
    By windows10_menu_ul = By.cssSelector("#c-shellmenu_52 + ul");
    By windows10_menu_ul_li = By.cssSelector("#c-shellmenu_52 + ul li");

    public WindowsPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public WindowsPage(WebDriver driver, String pageUrl){
        super(driver, pageUrl);
        this.driver = driver;
    }

    public void clickOnWindows10Button(){
        driver.findElement(windows10_menu).click();
    }

    public List<String> getSubMenuOfWindows10Menu(){
        
        setExplicitWait(1000);
        List<String> submenu = new ArrayList<>();
        if(driver.findElement(windows10_menu_ul).isDisplayed()){
            
            List<WebElement> weList = driver.findElements(windows10_menu_ul_li);
            for(WebElement el : weList){
                submenu.add(el.getText());
            }

        }


        return submenu;

    }



   
    
}
