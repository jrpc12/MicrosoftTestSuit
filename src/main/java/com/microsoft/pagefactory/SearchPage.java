package com.microsoft.pagefactory;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.Microsoft;
import com.microsoft.bean.ShopcartItem;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends Microsoft {



    @FindBy(id="R1MarketRedirect-1")
    List<WebElement> countryAlert;

    @FindBy(css = ".m-channel-placement ul")
    WebElement resultList;
    WebDriver driver;

    public SearchPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

    public SearchPage(WebDriver driver, String pageUrl) {
        super(driver,pageUrl);
        PageFactory.initElements(driver,this);
    }

    public List<ShopcartItem> getResultList( int selectNumItems){

        List<ShopcartItem> list = new ArrayList<ShopcartItem>();
        List<WebElement> listProducts = resultList.findElements(By.tagName("li"));
        

        if(selectNumItems <= listProducts.size()){

            for(int i=0; i< selectNumItems; i++ ){   

                String id = listProducts.get(i).getAttribute("id");
                String name = listProducts.get(i).findElement(By.className("c-subheading-6")).getText();
                String strPrice = listProducts.get(i).findElement(By.cssSelector("span[itemprop='price']")).getText();
                //removes $ sign
                strPrice = strPrice.replace("$", "").replaceAll(",", "");
                double price = Double.parseDouble(strPrice);
                list.add(new ShopcartItem(id,name,price));
            }
        }

        return list;

    }

    public void clickOnItem(ShopcartItem item){
        driver.findElement(By.id(item.getIdSelector())).click();
    }

    @Override
    public void dismissPopup(){
        
        if(countryAlert.size()>0){

            WebElement popup = countryAlert.get(0);

            if(popup.isDisplayed()){
                popup.findElement(By.id("R1MarketRedirect-close")).click();
            }
        }
    }
    
}
