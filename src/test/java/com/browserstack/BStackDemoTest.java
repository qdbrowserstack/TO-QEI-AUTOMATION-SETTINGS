package com.browserstack;

import com.browserstack.BrowserStackRemoteTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BStackDemoTest extends BrowserStackRemoteTest {
    @Test
    public void addProductToCart() throws Exception {
        driver.get("https://www.bstackdemo.com");
        Assert.assertTrue(driver.getTitle().matches("StackDemo"));
    }
}
