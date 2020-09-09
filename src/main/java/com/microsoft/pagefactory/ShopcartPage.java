package com.microsoft.pagefactory;

import java.util.List;

import com.microsoft.Microsoft;
import com.microsoft.bean.ShopcartItem;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ShopcartPage extends Microsoft {

    @FindBy(className = "cart-items")
    List<WebElement> cartItems;

    @FindBy(css = "._3LWrsBIG .XsLPvCL_")
    List<WebElement> shopcartSummaryList;

    public ShopcartPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public double getProductPrice(ShopcartItem item){

        double price = 0.0;
        for(WebElement el: cartItems){

            if(el.findElement(By.cssSelector(".item-description > .c-heading-6")).getText().equals(item.getName())){

                String strPrice = el.findElement(By.cssSelector("span[aria-hidden]")).getText();
                strPrice = strPrice.replace("$","").replaceAll(",","").replace("\"","");
                price = Double.parseDouble(strPrice);
                break;
            }
        }

        return price;

    }

    public double getShopCartSubtotal(){

        double price = 0.0;
        String strPrice = shopcartSummaryList.get(0).findElement(By.cssSelector("span[itemprop='price']")).getText().replace("$", "").replaceAll(",", "");
        price = Double.parseDouble(strPrice);

        return price;

    }

    public double getShopcartTotal(){

        double price = 0.0;
        String strPrice = shopcartSummaryList.get(1).findElement(By.cssSelector("span[itemprop='price']")).getText().replace("$", "").replaceAll(",", "");
        price = Double.parseDouble(strPrice);
        return price;


    }

    public void setQuantity(ShopcartItem item, int quantity){

        for(WebElement el: cartItems){

            if(el.findElement(By.cssSelector(".item-description > .c-heading-6")).getText().equals(item.getName())){

                WebElement weQuantiy = el.findElement(By.cssSelector("select[aria-label$='Quantity selection']"));
                Select quantitySelector = new Select(weQuantiy);
                quantitySelector.selectByValue(Integer.toString(quantity));
                setExplicitWait(2000);
                break;
            }
        }


    }


    
}
