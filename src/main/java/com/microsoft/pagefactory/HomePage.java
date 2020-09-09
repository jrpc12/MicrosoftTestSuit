package com.microsoft.pagefactory;

import java.util.List;

import com.microsoft.Microsoft;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Microsoft {

    @FindBys(
        @FindBy(className = "c-uhf-nav-link")
    )
    List<WebElement> link_menu_items;

    public HomePage(WebDriver driver, String pageUrl){
        super(driver, pageUrl);
        PageFactory.initElements(driver, this);
    }

    public boolean validateMenuItemPresents(List<String> menuItems){

        boolean itemFound = false;        

        for (String menuItem : menuItems) {
            itemFound = false;
            for(WebElement wel : link_menu_items){

                if(menuItem.equals(wel.getText())) {
                    itemFound = true;
                    break;
                }
            }

            if(!itemFound){
                break;
            }
        }           
        
        return itemFound;
    }

    public void clickOnMenuItem(String menuName){

        for(WebElement wel : link_menu_items){

            if(menuName.equals(wel.getText())) {
                wel.click();
                break;
            }
        }

    }
    
}
