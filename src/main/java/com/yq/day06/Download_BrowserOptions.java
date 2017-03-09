package com.yq.day06;


import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * chrome浏览器配置
 * firefox浏览器配置
 * Created by YQ on 2017/3/5.
 */
public class Download_BrowserOptions {
    /**
     * 下载地址不再是默认地址，而是变成D盘下了
     * chrome在升级，很多配置都不生效了
     */
    @Test
    public void chromeOptionsTest() {
        String downloadFilepath = "D:\\";//设置下载地址的根路径
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        //一般会问是否保存在某个位置，这里表示不弹这个窗
        chromePrefs.put("profile.default_content_settings.popups", false);
        chromePrefs.put("down.default_directory", downloadFilepath);//配置下载路径
        /*
        hash里面可以设置很多配置，但是我们只设了两个配置，一直没找到更多的配置
        down.default_directory找到了一个火狐的配置
         */
        ChromeOptions chromeOptions = new ChromeOptions();//配置类
        //prefs:改了就不行！！！！！
        chromeOptions.setExperimentalOption("prefs", chromePrefs);//把要配置的东西放入配置类

        System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(chromeOptions);//实例化浏览器的时候传入配置类对象

//        DesiredCapabilities dCap = DesiredCapabilities.chrome();
//        dCap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//        dCap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        driver.get("http://rj.baidu.com/soft/detail/13478.html?ald");
        driver.findElement(By.className("normal_download")).click();
    }

    @Test
    public void fireFoxDownloadTest() throws InterruptedException, AWTException {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        //设置火狐的默认下载文件，0表示桌面 1表示我的下载 2表示自定义文件夹
        firefoxProfile.setPreference("browser.download.folderList", "2");
        //设置自定义文件夹位置
        firefoxProfile.setPreference("browser.download.dir", "D:\\temp");
        //设置无需确认下载的文件格式,让他不弹框
        firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.ms-excel,text/csv,application/zip,application/exe");
        //打开一个预先配置的火狐
        FirefoxDriver driver = new FirefoxDriver(firefoxProfile);
        driver.get("http://rj.baidu.com/soft/detail/13478.html?ald");
        driver.findElement(By.xpath(".//*[@id='softAbs']/a[2]")).click();
//        Thread.sleep(3000);
//        Robot robot = new Robot();
//        robot.keyPress(KeyEvent.VK_TAB);
//        Thread.sleep(1000);
//        robot.keyPress(KeyEvent.VK_TAB);
//        Thread.sleep(1000);
//        robot.keyPress(KeyEvent.VK_ENTER);
//        Thread.sleep(10000);
        driver.quit();

    }








}
