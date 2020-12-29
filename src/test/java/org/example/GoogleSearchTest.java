package org.example;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.IExecutionListener;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
 
public class GoogleSearchTest implements IExecutionListener {
    /* protected static EdgeDriver driver; */
    String URL = "https://www.google.com/";
    String search_string = "LambdaTest";
    WebDriver driver = null;
    WebElement search_box;
    String exp_title = "Most Powerful Cross Browser Testing Tool Online | LambdaTest";
    public static String status = "passed";    
    public static String username = "user-name";
    public static String access_key = "access-key";
 
    @Override
    public void onExecutionStart() {
        System.out.println("onExecutionStart");
    }
 
    @BeforeTest
    @Parameters(value={"browser","version","platform", "resolution"})
 
    public void testSetUp(String browser, String version, String platform, String resolution) throws Exception {
        /*
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        */
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "Google Search - Maven Parallel Testing with Jenkins Pipeline");
        capabilities.setCapability("name", "Google Search - Maven Parallel Testing with Jenkins Pipeline");
        capabilities.setCapability("platform", platform);
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("version",version);
        capabilities.setCapability("tunnel",false);
        capabilities.setCapability("network",true);
        capabilities.setCapability("console",true);
        capabilities.setCapability("visual",true);
 
        try {
            driver = new RemoteWebDriver(new URL("http://" + username + ":" + access_key + "@hub.lambdatest.com/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        }
        System.out.println("Started session");
    }
 
    @Test
    public void test_Selenium4_GoogleSearch() throws InterruptedException {
        driver.navigate().to(URL);
        driver.manage().window().maximize();
 
        try {
            /* Enter the search term in the Google Search Box */
            search_box = driver.findElement(By.xpath("//input[@name='q']"));
            search_box.sendKeys(search_string);
 
            search_box.submit();
 
            /* driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); */
            /* Not a good programming practice, added for demonstration */
            Thread.sleep(3000);
 
            /* Click on the first result which will open up the LambdaTest homepage */
            WebElement lt_link = driver.findElement(By.xpath("//h3[.='LambdaTest: Most Powerful Cross Browser Testing Tool Online']"));
            lt_link.click();
 
            /* driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); */
            /* Not a good programming practice, added for demonstration */
            Thread.sleep(5000);
 
            String curr_window_title = driver.getTitle();
            Assert.assertEquals(curr_window_title, exp_title);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
 
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
 
    @Override
    public void onExecutionFinish() {
        System.out.println("onExecutionFinish");
    }
}