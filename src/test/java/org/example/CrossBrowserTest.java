package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
 
public class CrossBrowserTest {
    /*  protected static ChromeDriver driver; */
    WebDriver driver = null;
    String URL = "https://lambdatest.github.io/sample-todo-app/";
    public static String status = "passed";
    //String username = "user-name";
   // String access_key = "access-key";
   String username = "essid";
   String access_key = "XCPpq.HS9G2L$sR";
 
    @BeforeClass
    public void testSetUp() throws MalformedURLException {
        /*
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        */
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "Maven Parallel Testing with Jenkins Pipeline [Chrome]");
        capabilities.setCapability("name", "Maven Parallel Testing with Jenkins Pipeline [Chrome]");
        capabilities.setCapability("platform", "Windows 10");
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("version","latest");
        capabilities.setCapability("tunnel",false);
        capabilities.setCapability("network",true);
        capabilities.setCapability("console",true);
        capabilities.setCapability("visual",true);
 
       // driver = new RemoteWebDriver(new URL("http://" + username + ":" + access_key + "@hub.lambdatest.com/wd/hub"), capabilities);
          driver = new RemoteWebDriver(new URL("https://lambdatest.github.io/sample-todo-app/"), capabilities);
        System.out.println("Started session");
    }
 
    @Test
    public void test_Selenium4_ToDoApp() throws InterruptedException {
        driver.navigate().to(URL);
        driver.manage().window().maximize();
 
        try {
            /* Let's mark done first two items in the list. */
            driver.findElement(By.name("li1")).click();
            driver.findElement(By.name("li2")).click();
 
            /* Let's add an item in the list. */
            driver.findElement(By.id("sampletodotext")).sendKeys("Happy Testing at LambdaTest");
            driver.findElement(By.id("addbutton")).click();
 
            /* Let's check that the item we added is added in the list. */
            String enteredText = driver.findElement(By.xpath("/html/body/div/div/div/ul/li[6]/span")).getText();
            if (enteredText.equals("Happy Testing at LambdaTest")) {
                System.out.println("Demonstration of Jenkins is complete");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
 
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}
