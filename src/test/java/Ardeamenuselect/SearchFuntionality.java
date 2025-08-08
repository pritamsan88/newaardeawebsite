package Ardeamenuselect;

import ArdeaPomFiles.Searchpom;
import ArdeaPomFiles.menuselect;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.ThreadLocalRandom;

public class SearchFuntionality {
    WebDriver driver;
    menuselect select;
    Searchpom search;

    @BeforeTest
    public void startup() {


        WebDriverManager.firefoxdriver().setup();

        driver = new FirefoxDriver();
        select = new menuselect(driver);


    }

    @Test(priority = 1)
    public void projecturl() {
        select.openurl();

    }

    @Test(priority = 2)
    public void headersearch() throws InterruptedException {
        search = new Searchpom(driver);
        Thread.sleep(1000);
        search.searchaddtocart();

    }
}
