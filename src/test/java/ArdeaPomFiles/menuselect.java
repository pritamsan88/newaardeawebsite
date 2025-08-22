package ArdeaPomFiles;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class menuselect {

    WebDriver driver;
    String url = "https://www.aardea.com/";
    @FindBy(css = "li.navitem.has-dropdown > a")
    WebElement productmenu;
    @FindBy(css = " div.menuiteam.item-3 > div > ul > li")
    List<WebElement> listofsize;
    // @FindBy(css = "div.menuiteam.item-3 > div > h6 > a")
    // WebElement dinnerware;
    @FindBy(xpath = "//button[@type='submit']")
    List<WebElement> addtocart;
    @FindBy(xpath = "//h3[@class='card__heading h5']/a")
    List<WebElement> productname;
    @FindBy(css = " div.menuiteam.item-4 > div > ul>li")
    List<WebElement> listofsize2;
    @FindBy(css = " div.menuiteam.item-6 > div > ul >li")
    List<WebElement> listofsize3;
    // @FindBy(css = ".cart-count-bubble .visually-hidden")
    //  WebElement cartquantity;
    @FindBy(css = "#cart-icon-bubble")
    WebElement cartdetails;
    @FindBy(css = " td.cart-item__details > div")
    List<WebElement> perunitpricelist;

    @FindBy(xpath = "//input[@class='quantity__input']")
    List<WebElement> productquatity;

    @FindBy(css = "td.cart-item__details > a")
    List<WebElement> cartname;

    @FindBy(css = "td.cart-item__totals.right.small-hide  div.cart-item__price-wrapper > span")
    List<WebElement> expectedtotalprice;

    @FindBy(xpath = "//div[@class='totals']/p")
    WebElement estimatetotal;
    @FindBy(xpath = "//input[@class='quantity__input']")
    List<WebElement> addedproductcartquantityinput;
    @FindBy(xpath = " //button[@name='plus']")
    List<WebElement> plusicon;
    @FindBy(css = "#cart-icon-bubble")
    WebElement uppermenucarticon;

    public menuselect(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void openurl() {
        driver.get(url);
        driver.manage().window().maximize();
        //driver.navigate().refresh();

    }

    public void menuselect() throws InterruptedException {
        Actions act = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {


            for (int i = 1; i < listofsize.size(); i++) {
                act.moveToElement(productmenu).pause(Duration.ofMillis(500)).perform();
                //Thread.sleep(1000);
                //act.moveToElement(dinnerware).pause(Duration.ofMillis(500)).click().perform();
                // Thread.sleep(1000);
                String cssSelector = "div.menuiteam.item-3 > div > ul > li:nth-child(" + i + ") > a";
                WebElement ele = driver.findElement(By.cssSelector(cssSelector));
                act.moveToElement(ele).pause(Duration.ofMillis(500)).click().perform();
                Thread.sleep(2000);
                if (addtocart.size() > 20) {
                    WebElement product = productname.get(i);
                    System.out.println("Added product name : - " + product.getText() + " Index of the product : - " + i);
                    WebElement productaddcart = addtocart.get(i);
                    js.executeScript("arguments[0].scrollIntoView();", productaddcart);
                    Thread.sleep(2000);
                    js.executeScript("arguments[0].click();", productaddcart);
                    driver.navigate().back();
                } else {

                    System.out.println("addtocart list has less than 10 elements. Size: " + addtocart.size());


                }


                Thread.sleep(500);
                driver.navigate().back();
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }


    }

    public void menuselect2() throws InterruptedException {
        Actions act2 = new Actions(driver);
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        try {


            for (int i = 1; i < listofsize2.size(); i++) {
                act2.moveToElement(productmenu).pause(Duration.ofMillis(500)).perform();
                String product = "div.menuiteam.item-4 > div > ul > li:nth-child(" + i + ") > a";
                WebElement ele2 = driver.findElement(By.cssSelector(product));
                act2.moveToElement(ele2).pause(Duration.ofMillis(500)).click().perform();
                Thread.sleep(1000);
                if (addtocart.size() > 5) {
                    WebElement product2 = productname.get(i);
                    System.out.println("Added product name : - " + product2.getText() + " Index of the product : - " + i);
                    WebElement productaddcart = addtocart.get(i);
                    /*js1.executeScript("arguments[0].scrollIntoView();", productaddcart);
                    Thread.sleep(500);
                    js1.executeScript("arguments[0].click();", productaddcart);*/
                    Thread.sleep(1000);
                    js1.executeScript("arguments[0].scrollIntoView({block: 'center'});", productaddcart);
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
                    wait.until(ExpectedConditions.elementToBeClickable(productaddcart)).click();

                    driver.navigate().back();

                } else {

                    System.out.println("addtocart list has less than 5 elements. Size: " + addtocart.size());

                }
                Thread.sleep(500);
                driver.navigate().back();
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }


    }

    public void proprouct() throws InterruptedException {
        Actions act3 = new Actions(driver);
        JavascriptExecutor js3 = (JavascriptExecutor) driver;
        try {


            for (int k = 1; k < listofsize3.size(); k++) {
                act3.moveToElement(productmenu).pause(Duration.ofMillis(500)).perform();
                String product = "div.menuiteam.item-6 > div > ul > li:nth-child(" + k + ") > a";
                WebElement ele3 = driver.findElement(By.cssSelector(product));
                act3.moveToElement(ele3).pause(Duration.ofMillis(500)).click().perform();
                Thread.sleep(1000);
                if (addtocart.size() > 10) {
                    WebElement product3 = productname.get(k);
                    System.out.println("Added product name : - " + product3.getText() + " Index of the product : - " + k);
                    WebElement productaddcart = addtocart.get(k);
                   /* js3.executeScript("arguments[0].scrollIntoView();", productaddcart);
                    Thread.sleep(500);
                    js3.executeScript("arguments[0].click();", productaddcart);*/
                    Thread.sleep(2000);
                    js3.executeScript("arguments[0].scrollIntoView({block: 'center'});", productaddcart);
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
                    wait.until(ExpectedConditions.elementToBeClickable(productaddcart)).click();

                    //js3.executeScript("arguments[0].click();", productaddcart);
                    driver.navigate().back();

                } else {

                    System.out.println("addtocart list has less than 10 elements. Size: " + addtocart.size());

                }
                Thread.sleep(500);
                driver.navigate().back();


            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }


    }

    public void gotocartpage() throws InterruptedException {
        //Actions act4 = new Actions(driver);
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        Thread.sleep(1000);
        JavascriptExecutor js4 = (JavascriptExecutor) driver;

        //js4.executeScript("arguments[0].scrollIntoView(true);", cartdetails);
        //Thread.sleep(1000);
        //js4.executeScript("arguments[0].click();", cartdetails);
        //wait.until(ExpectedConditions.elementToBeClickable(cartdetails)).click();
        //driver.navigate().refresh();
        driver.navigate().to("https://www.aardea.com/cart");


        String hiddenText = (String) js4.executeScript(
                "return document.querySelector('.cart-count-bubble .visually-hidden')?.textContent.trim();"
        );
        System.out.println("Quantity of the cart :- " + hiddenText);
        System.out.println("Welcome to mycart page ");


    }

    public void quantity_increse() throws InterruptedException {
        Thread.sleep(500);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < plusicon.size(); i++) {
            try {
                WebElement ele = plusicon.get(i);
                js.executeScript("arguments[0].scrollIntoView(true);", ele);
                Thread.sleep(1000);
                js.executeScript("arguments[0].click();", ele);
                Thread.sleep(1000);
                js.executeScript("arguments[0].scrollIntoView(true);", uppermenucarticon);
                Thread.sleep(1000);

                String hiddenText = (String) js.executeScript(
                        "return document.querySelector('.cart-count-bubble .visually-hidden')?.textContent.trim();"
                );
                System.out.println("Quantity of the product added to the cart after increase quantity  :- " + hiddenText);
                //ele.click();


              /*  for (int i = 0; i < plusicon.size(); i++) {

                 js.executeScript("arguments[0].scrollIntoView(true);",ele);
                    Thread.sleep(500);
                 js.executeScript("arguments[0].click();",ele);
                    //ele.click();



                }*/
            } catch (StaleElementReferenceException e) {
                System.out.println(e.getStackTrace());


            }


        }

    }


    public void catproductquatityverify() throws InterruptedException {
        double actualtotal = 0;
        double total = 0;

        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < cartname.size(); i++) {
            WebElement name = cartname.get(i);
            WebElement properunitprice = perunitpricelist.get(i);
            WebElement quantity = productquatity.get(i);
            WebElement totalprice = expectedtotalprice.get(i);

            String cartaddedproductname = name.getText();
            String cartaddedproperunitprice = properunitprice.getText();
            String cartaddedquantity = quantity.getAttribute("value");
            String expectedtotalprice = totalprice.getText();
            String price2 = expectedtotalprice.replaceAll("[₹,\\s]", "");
            String price = cartaddedproperunitprice.replaceAll("[₹,\\s]", "");

            double amount = Double.parseDouble(price);
            double expectedamount = Double.parseDouble(price2);
            //System.out.println(expectedamount);


            actualtotal = amount * Double.parseDouble(cartaddedquantity);

            total += actualtotal;


            Assert.assertEquals(expectedamount, actualtotal, "Amount not matched");


            System.out.println(" Product name : --> " + cartaddedproductname + " Per unit price : --> " + cartaddedproperunitprice + " Quantity :--> " + cartaddedquantity + " Expectedtotalprice(per unit) :--> " + expectedtotalprice + " Actual price (per unit) :--> " + actualtotal);
            System.out.println(" Actual Test result All total product price : ----->" + total);


        }


        Thread.sleep(500);
        js.executeScript("arguments[0].scrollIntoView();", estimatetotal);
        String expectedalltotalproduct = estimatetotal.getText();
        String Estimatedtotal = expectedalltotalproduct.replaceAll("[₹,\\s]", "");
        double Estimatedtotalprice = Double.parseDouble(Estimatedtotal);

        System.out.println(" Expected test result All total price :-----> " + Estimatedtotalprice);

        Assert.assertEquals(total, Estimatedtotalprice, "price not matched");


    }


}
