package com.browserstack;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class FlakyTest extends BrowserStackRemoteTest {
    private static int invocationCount = 0;
    @Test
    public void addProductToCart() throws Exception {
        // navigate to bstackdemo
        driver.get("https://www.bstackdemo.com");

        // Check the title
        Assert.assertTrue(driver.getTitle().matches("StackDemo"));

        // Save the text of the product for later verify
        String productOnScreenText = driver.findElement(By.xpath("//*[@id=\"1\"]/p")).getText();
        // Click on add to cart button
        driver.findElement(By.xpath("//*[@id=\"1\"]/div[4]")).click();

        // See if the cart is opened or not
        Assert.assertTrue(driver.findElement(By.className("float-cart__content")).isDisplayed());

        // Check the product inside the cart is same as of the main page
        String productOnCartText = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div[2]/div[2]/div/div[3]/p[1]")).getText();
        Assert.assertEquals(productOnScreenText, productOnCartText);
    }

    @Test(invocationCount = 2)
    public void flaky() {
        invocationCount++;
        driver.get("https://www.bstackdemo.com");
        Assert.assertTrue(driver.getTitle().matches("StackDemo"));
        if (invocationCount == 2) {
            Assert.fail("Flaky Test Failed on Second Run");
        }
    }
}

