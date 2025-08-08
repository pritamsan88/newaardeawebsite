package Ardeamenuselect;

import ArdeaPomFiles.menuselect;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MenuSelectFunctionality {

    WebDriver driver ;
    menuselect select;
    EdgeOptions option;

    @BeforeTest
    public void startup() {


        WebDriverManager.edgedriver().setup();
        option = new EdgeOptions();
        option.addArguments("--incognito");
        driver = new EdgeDriver(option);
        select = new menuselect(driver);


    }

    @Test(priority = 1)
    public void projecturl() {
        select.openurl();
    }

    @Test(priority = 2, enabled = false)
    public void menuselect() throws InterruptedException {

        select.menuselect();

    }

    @Test(priority = 3)
    public void productselect() throws InterruptedException {
        Thread.sleep(1000);
        select.menuselect2();


    }

    @Test(priority = 4)
    public void productselect2() throws InterruptedException {

        select.proprouct();
        select.gotocartpage();
        select.quantity_increse();


        //select.catproductquatityverify();
    }


    @AfterTest
    public void quit() throws InterruptedException {
        Thread.sleep(4000);
        driver.quit();

    }


}
