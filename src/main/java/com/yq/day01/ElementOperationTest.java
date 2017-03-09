package com.yq.day01;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 操作网页上的元素
 * Created by YQ on 2017/3/3.
 */
public class ElementOperationTest {
    WebDriver driver;
    @BeforeMethod
    public  void  openBrowser(){
        System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.baidu.com");
    }

    /**
     * 点击糯米连接
     */
    @Test
    public void clickTest() {
        //定位并操作  tj_trnuomi
        driver.findElement(By.name("tj_trnuomi")).click();
    }
    @Test
    public void sendKeysTest() throws InterruptedException {
        driver.findElement(By.id("kw")).sendKeys("苏大 杨群");
        Thread.sleep(3000);
        driver.findElement(By.id("kw")).clear();
    }

    /**
     * text是标签里的文本
     */
    @Test
    public void getTextTest() {
        List<WebElement> elements =driver.findElements(By.xpath(".//*[@id='u1']/a"));
        for (WebElement element : elements) {//遍历获得的每个元素
            System.out.println(element.getText());//对每个元素进行操作
        }
    }

    @Test
    public void getTagNameTest() {
        String tagName = driver.findElement(By.id("kw")).getTagName();
        System.out.println(tagName);
        Assert.assertEquals(tagName, "input");
    }

    @Test
    public void getAttributeTest() {
        String attribute = driver.findElement(By.id("kw")).getAttribute("maxlength");
        System.out.println(attribute);
        Assert.assertEquals(attribute, "255");
    }

    @Test
    public void isDisplayTest() {
        boolean isplayed = driver.findElement(By.id("kw")).isDisplayed();
        System.out.println(isplayed);
        Assert.assertFalse(isplayed,"错误时的log");
    }
    @Test
    public void isSelectedTest() {
        //复选框类似
        driver.get("?????");
        WebElement element = driver.findElement(By.xpath("?????"));
        element.click();
        boolean isSelected = element.isSelected();
        System.out.println(isSelected);
        Assert.assertTrue(isSelected);
    }
    @Test
    public void isEnabledTest() {
        driver.get("?????");
        boolean b = driver.findElement(By.name("buttonhtml")).isEnabled();
        System.out.println(b);
        Assert.assertFalse(b);
    }

    /**
     *
     */
    @Test
    public void screenShotTest() {
        long currentTimeMillis = System.currentTimeMillis();
        //需要引入一个接口TakesScreenshot
        //  接口转换是什么原理？？？
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file,new File(".\\logs\\screenshots\\screenshot"+currentTimeMillis+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
