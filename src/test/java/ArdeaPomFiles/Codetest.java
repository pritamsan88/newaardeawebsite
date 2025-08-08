package ArdeaPomFiles;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Codetest {

    WebDriver driver;


    @BeforeTest
    public void startup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }

    @Test(priority = 1)
    public void openurl() {
        driver.get("https://demoqa.com/checkbox");
        driver.manage().window().maximize();

    }

    @Test(priority = 2)
    public void checkchecklist() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement checkbox = driver.findElement(By.cssSelector("#tree-node > ol > li > span > button > svg"));
        checkbox.click();

        for (int i = 1; i < 4; i++) {


            Thread.sleep(500);
            String ele = "#tree-node > ol > li > ol > li:nth-child(" + i + ") > span > button > svg";
            WebElement maincheckbox = driver.findElement(By.cssSelector(ele));
            js.executeScript("arguments[0].scrollIntoView(true);", maincheckbox);
            Thread.sleep(500);
            maincheckbox.click();

            for (int j = 1; j < 4; j++) {
                try {
                    String ele1 = "#tree-node > ol > li > ol > li:nth-child(" + i + ") > ol > li:nth-child(" + j + ") > span > label > span.rct-title";
                    WebElement submainbox = driver.findElement(By.cssSelector(ele1));
                    submainbox.click();
                } catch (NoSuchElementException e) {
                    System.out.println("No such sub-element at i=" + i + " j=" + j);
                }


            }


        }


    }
}
