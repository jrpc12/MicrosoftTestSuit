package com.microsoft.bean;

import java.util.List;

public class Config {

    private String basePageUrl;
    private List<String> menuItems;
    private String searchProduct;
    private int printSearchProductNumber;
    private String clickOnMenu;

    
    public String getBasePageUrl() {
        return this.basePageUrl;
    }

    public void setBasePageUrl(String basePageUrl) {
        this.basePageUrl = basePageUrl;
    }

    public String getClickOnMenu() {
        return this.clickOnMenu;
    }

    public void setClickOnMenu(String clickOnMenu) {
        this.clickOnMenu = clickOnMenu;
    }
    

    public List<String> getMenuItems() {
        return this.menuItems;
    }

    public void setMenuItems(List<String> menuItems) {
        this.menuItems = menuItems;
    }

    public String getSearchProduct() {
        return this.searchProduct;
    }

    public void setSearchProduct(String searchProduct) {
        this.searchProduct = searchProduct;
    }

    public int getPrintSearchProductNumber() {
        return this.printSearchProductNumber;
    }

    public void setPrintSearchProductNumber(int printSearchProductNumber) {
        this.printSearchProductNumber = printSearchProductNumber;
    }

    
}
