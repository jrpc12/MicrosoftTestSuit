package com.microsoft.pagefactory;

import com.microsoft.Microsoft;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.JavascriptExecutor;	

public class ProductDetailPage extends Microsoft {

    @FindBy(css = "#ProductPrice_productPrice_PriceContainer ")
    WebElement productPrice;
    @FindBy(id = "email-newsletter-dialog")
    WebElement newsLetterPopup;
    @FindBy(id = "ButtonPanel_buttonPanel")
    WebElement addToCartButton;
    WebDriver driver;

    public ProductDetailPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public double getProductPrice(){

        String strPrice = productPrice.getText().replace("$", "").replaceAll(",", "");
        return Double.parseDouble(strPrice);

    }

    public void addToCart(){

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView()", addToCartButton); 
        addToCartButton.click();
    }

    @Override
    public void dismissPopup(){

        if(newsLetterPopup.isDisplayed()){
            newsLetterPopup.findElement(By.className("glyph-cancel")).click();
        }

    }

    
}
