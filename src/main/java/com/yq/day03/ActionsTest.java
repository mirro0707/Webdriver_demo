package com.yq.day03;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * Created by YQ on 2017/3/5.
 */
public class ActionsTest {
    WebDriver driver;
    @BeforeMethod
    public void openChrome() {
        System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void contextOrDoubleClickTest() {
        driver.get("http://www.baidu.com");
        WebElement button = driver.findElement(By.id("su"));
        Actions actions = new Actions(driver);//实例化Action类

        actions.contextClick(button).perform();//模拟右键，一定要用perform做执行
        //右键完之后的操作和正常元素一样
        //......
        actions.doubleClick(button).perform();//双击
        actions.doubleClick().perform();//不传入参数，那么就会在鼠标当前的位置双击
    }

    @Test
    public void moveTOElementTest() {
        driver.get("http://www.baidu.com");
        WebElement element = driver.findElement(By.name("tj_briicon"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();//移动到此元素上
    }
    @Test
    public void dragAndDropTest() {
        driver.get("??");
        WebElement element = driver.findElement(By.id("drag"));
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(element, 300, 500).perform();//移动到某个坐标
    }

    /**
     * "点击"某个元素，"拖"到另一个元素上"释放"
     */
    @Test
    public void DragElemnetToElementTest() {
        driver.get("??");
        WebElement element = driver.findElement(By.id("drag"));
        WebElement otherElement = driver.findElement(By.xpath("html/body/h1"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(element).moveToElement(otherElement).release(otherElement).perform();

//        actions.dragAndDrop(element, otherElement);//????这是什么操作？？
    }

    /**
     * 获取到多选下拉框；再利用多选下拉框获取到下面的所有子元素进行操作
     */
    @Test
    public void multipleSelectTest() {
        driver.get("file:///C:/slenium_html/index.html");
        Actions actions = new Actions(driver);
        WebElement multipleSelect = driver.findElement(By.id("selectWithMultipleEqualsMultiple"));
        List<WebElement> optionElement = multipleSelect.findElements(By.tagName("option"));//获取到子元素
        //单选的话用CTRL，没有效果？？？
        actions.keyDown(Keys.SHIFT)//Keys.SHIFT(可以引入键盘的很多操作，唯独没有a,b,c,d这样的基本字母，引入java的robot来解决
                .click(optionElement.get(0))
                .click(optionElement.get(2)).perform();
    }

    /**
     * 打开百度，不使用sendkeys而是使用模拟键盘来输入值
     */
    @Test
    public void robotTest() throws AWTException {
        driver.get("https://www.baidu.com");
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_A);//按下A
        robot.keyRelease(KeyEvent.VK_A);//松开A-----不写释放也没有报错
        robot.keyPress(KeyEvent.VK_B);//按下B
        robot.keyRelease(KeyEvent.VK_B);//松开B

        int s = (int)new Character('S');//获取ACSII码值
//        System.out.println(s);
        robot.keyPress(s);
        robot.keyRelease(s);

        robot.keyPress(KeyEvent.VK_SHIFT);//组合键SHIFT+4
        robot.keyPress(KeyEvent.VK_4);
        robot.keyRelease(KeyEvent.VK_4);
        robot.keyRelease(KeyEvent.VK_SHIFT);

    }

    /**
     * w文件上传
     */
    @Test
    public void UPLoadTest() {
        driver.get("file:///C:/selnium_html/index.html");
        driver.findElement(By.id("load")).sendKeys("D:\\export.html");

    }
    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

}
