package com.yq.day05;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 完成163邮箱的注册
 * 别人的产品，验证码无法处理
 * Created by YQ on 2017/3/5.
 */
public class EmailRegister {
    WebDriver driver;

    @BeforeMethod
    public void openChrome() {
        System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }


    @Test
    public void registerTest() {
        long time = System.currentTimeMillis();
        driver.get("http://mail.163.com");
        driver.switchTo().frame("x-URS-iframe");//转交给iframe
        driver.findElement(By.id("changepage")).click();
        String Handle1 = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (handle.equals(Handle1)) {
                continue;
            } else {
                driver.switchTo().window(handle);//转交给新窗口
            }
        }

        driver.findElement(By.xpath(".//*[@id='tabsUl']/li[1]/a")).click();
        driver.findElement(By.id("nameIpt")).sendKeys('e'+String.valueOf(time));
        driver.findElement(By.id("mainPwdIpt")).sendKeys("yq1234");
        driver.findElement(By.id("mainCfmPwdIpt")).sendKeys("yq1234");
        driver.findElement(By.id("mainMobileIpt")).sendKeys(String.valueOf(time/100));

        driver.findElement(By.id("vcodeIpt")).sendKeys("45lh5");
        driver.findElement(By.id("mainAcodeIpt")).sendKeys("123123");

        driver.findElement(By.id("mainRegA")).click();
        String text = driver.findElement(By.xpath(".//*[@id='m_mainAcode']/span")).getText();
        Assert.assertEquals(text, "  手机验证码不正确，请重新填写");
    }

    @AfterMethod
    public void closeChrome() throws InterruptedException {
        Thread.sleep(3000);
//        driver.quit();
    }

}
