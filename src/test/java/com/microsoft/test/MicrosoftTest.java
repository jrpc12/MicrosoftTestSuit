package com.microsoft.test;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.bean.ShopcartItem;
import com.microsoft.pagefactory.HomePage;
import com.microsoft.pagefactory.ProductDetailPage;
import com.microsoft.pagefactory.SearchPage;
import com.microsoft.pagefactory.ShopcartPage;
import com.microsoft.pom.WindowsPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MicrosoftTest extends Microsoft {

    public MicrosoftTest() throws Exception {
        super();
    }

    @Test(priority = 1)
    public void validateMenuItems() throws Exception {

        HomePage home = new HomePage(driver, config.getBasePageUrl());
        home.open();

        Microsoft.logger.info("Go to: " + config.getBasePageUrl());
        boolean menuItemsPresent = home.validateMenuItemPresents(config.getMenuItems());
        Microsoft.logger.info("Validate all menu items are present: (" + new Gson().toJson(config.getMenuItems()) + ") = "  + Boolean.toString(menuItemsPresent));

        Assert.assertEquals(true,  menuItemsPresent);
        home.clickOnMenuItem(config.getClickOnMenu());
        
    }

    @Test(priority = 2)
    public void printAllWindows10Submenu() throws Exception{

        WindowsPage windowsPage = new WindowsPage(driver);
        Microsoft.logger.info("Go to Windows:");
        windowsPage.clickOnWindows10Button();
        List<String> submenu =  windowsPage.getSubMenuOfWindows10Menu();
        Assert.assertNotNull(submenu);
        Microsoft.logger.info("Print all elements in the dropdown: " + new Gson().toJson(submenu));
        Assert.assertEquals(submenu.size()>0, true);

    }

    @Test(priority = 3)
    public void validateProductPrices() throws Exception{
        Microsoft.logger.info("Go to search");
        SearchPage searchPage = new SearchPage(driver);
        searchPage.searchBy(config.getSearchProduct());
        Microsoft.logger.info("Search by:" + new Gson().toJson(config.getSearchProduct()));
        searchPage.dismissPopup();
        int selectNumberOfProduct = config.getPrintSearchProductNumber();
        List<ShopcartItem> result =  searchPage.getResultList(selectNumberOfProduct);
        Microsoft.logger.info("Print the price for the 3 first elements listed in Software result list:" + new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(result));
        //Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(result) 

        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(),config.getPrintSearchProductNumber());

        ShopcartItem firstProduct = result.get(0);
        Microsoft.logger.info("Store the price of the first one:" + firstProduct.getPrice());
        searchPage.clickOnItem(firstProduct); 
        Microsoft.logger.info("Click on first product::" + firstProduct.getName());
        
        ProductDetailPage detailPage = new ProductDetailPage(driver);
        detailPage.dismissPopup();
        boolean validatePriceIsSame = firstProduct.getPrice() == detailPage.getProductPrice();
        Microsoft.logger.info("Once in the details page, validate both prices are the same:" + firstProduct.getPrice() + " = "  + detailPage.getProductPrice());
        Assert.assertEquals(true, validatePriceIsSame);
        detailPage.addToCart();
        Microsoft.logger.info("Click Add To Cart");

        ShopcartPage shopcartPage = new ShopcartPage(driver);
        double shopcartSubtotal = shopcartPage.getShopCartSubtotal();
        double shopcartTotal = shopcartPage.getShopcartTotal();
        Assert.assertEquals(firstProduct.getPrice(), shopcartPage.getProductPrice(firstProduct));
        Microsoft.logger.info("Verify all 3 price amounts are the same:" + firstProduct.getPrice()  + " = " + shopcartSubtotal + " = " + shopcartTotal );

        //Assert all prices are the same
        Assert.assertEquals(true, (firstProduct.getPrice() == shopcartSubtotal && firstProduct.getPrice() ==  shopcartTotal));

        //Increase quantity number
        int increaseQuantity = 20; 
        shopcartPage.setQuantity(result.get(0), increaseQuantity);
        shopcartTotal = shopcartPage.getShopcartTotal();
        System.out.println(shopcartTotal);
        Assert.assertEquals((firstProduct.getPrice() * increaseQuantity), shopcartTotal);
        Microsoft.logger.info("Validate Total amount is Unit Price * 20:  item= " + firstProduct.getPrice()  + " *  n=" +  increaseQuantity + " = " + shopcartTotal );

        closeDriver();

    }

    
    
}
