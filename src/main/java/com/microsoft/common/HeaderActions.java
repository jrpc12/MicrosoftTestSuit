package com.microsoft.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

public class HeaderActions{

    WebDriver driver;

    @FindBy(id="search")
    WebElement searchButton;

    @FindBy(id="cli_shellHeaderSearchInput")
    WebElement searchInput;

    @FindBy(id="uhf-shopping-cart")
    WebElement shopcartButton;

    @FindBy(id="language-selector")
    WebElement langSelector;

    @FindBy(id="meControl")
    WebElement accountControl;
    

    public HeaderActions(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    } 

    public void searchBy(String name){
        searchButton.click();
        searchInput.sendKeys(name);
        searchInput.submit();
    }

}