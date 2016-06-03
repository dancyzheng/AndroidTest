package Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
 
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
 
public class AppDemo {
    private  AndroidDriver  driver;
 
    @BeforeMethod
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName","Android Emulator");
        capabilities.setCapability("platformVersion", "6.1");
        capabilities.setCapability("udid", "E7NDU15A29001547");//"0815f83b046d2305"
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(url, capabilities);
        // Navigate to the page and interact with the elements on the guinea-pig page using id.
        driver.get("https://passport.weibo.cn/signin/welcome?entry=mweibo&r=http%3A%2F%2Fm.weibo.cn%2F%3Fjumpfrom%3Dwapv4%26tip%3D1&wm=3349&vt=4");

    }
 
    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }
 
    @Test
    public void addContact() throws UiObjectNotFoundException{   	
    	
    	driver.context("NATIVE_APP");//without this sentence, no such element error 
    	String webViewClass = "android.webkit.WebView";
    	String viewClass = "android.view.View";
    	WebElement e = driver.findElement(By.className(webViewClass));
        WebElement loginBtn = e.findElements(By.className(viewClass)).get(3);
        loginBtn.click();
        String editTextClass = "android.widget.EditText";    
        try {
			Thread.sleep(30000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        
        List<WebElement> l = driver.findElements(By.className(editTextClass));
        System.out.println(l.size());
        WebElement usernameE = l.get(0);
        WebElement pwE = l.get(1);
        usernameE.sendKeys("13426489377");
        pwE.sendKeys("Welcome1?!");
        
//        WebElement unE = driver.findElementByAndroidUIAutomator("new UiSelector().description('ÓÊÏä/ÊÖ»úºÅ')");
//        WebElement psE = driver.findElementByAndroidUIAutomator("new UiSelector().resourceId('loginPassword')");
//        unE.sendKeys("13426489377");
//        psE.sendKeys("Welcome1?!");
        String loginXpath = "//android.view.View[@content-desc='µÇÂ¼']";
        WebElement loginBtnE = driver.findElement(By.xpath(loginXpath));
        loginBtnE.click();
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        Assert.assertTrue(driver.getPageSource().contains("Ê×Ò³"));
    }
}