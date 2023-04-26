package org.automation.pom.pages;

import org.automation.pom.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class StorePage extends BasePage {
    By search_field = By.cssSelector("#woocommerce-product-search-field-0");
    By search_btn = By.cssSelector("button[value='Search']");
    By search_banner_title = By.cssSelector(".woocommerce-products-header__title.page-title");
    By add_to_cart_btn = By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']");

    By view_cart_link = By.cssSelector("a[title='View cart']");



    public StorePage(WebDriver driver) {
        super(driver);
    }

    public StorePage enterTextInSearchFiled(String txt){
        driver.findElement(search_field).sendKeys(txt);
        return this;
    }
    public StorePage clickSearchButton(){
        driver.findElement(search_btn).click();
        return this;
    }
    public String getSearchFunctionBannerTitle(){
        return driver.findElement(search_banner_title).getText();
    }

    public StorePage addGivenProductByNameToCart(String ProductName){
        By add_prooduct_to_cart_btn = By.cssSelector("a[aria-label='Add “"+ProductName+"” to your cart']");
        driver.findElement(add_prooduct_to_cart_btn).click();
        return this;
    }

    public StorePage clickViewCartLinkForTheFullProduct(String FullProductName) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfElementLocated(view_cart_link));
        driver.findElement(view_cart_link).click();
        Thread.sleep(2000);

        return this;
    }

    // part of functional method to get prodct as input and then validate that product is searchaed
    // and then add the product to cart
    public CartPage seachForAGivenProductWithPartialName(String partialProductName) throws InterruptedException {
        enterTextInSearchFiled(partialProductName).clickSearchButton();
        Assert.assertNotNull(getSearchFunctionBannerTitle(),"Search results: “"+partialProductName+"”");
        addGivenProductByNameToCart("Blue Shoes");
        Thread.sleep(5000);
        clickViewCartLinkForTheFullProduct("Blue Shoes");

        return new CartPage(driver);

    }

}