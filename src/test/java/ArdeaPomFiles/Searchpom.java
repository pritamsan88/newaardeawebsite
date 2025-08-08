package ArdeaPomFiles;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Searchpom {

    WebDriver driver;

    @FindBy(xpath = "//div[@class='last_nav_part']/div[@class='search_bar']/form/input")
    WebElement searchelement;
    @FindBy(xpath = "//div[@class='last_nav_part']/div[@class='search_bar']/form/button")
    WebElement searchbuttonpress;
    @FindBy(xpath = "//div[@class='field']/input[@name='q']")
    WebElement advancesearch;
    @FindBy(xpath = "//li[@class='predictive-search__list-item']")
    List<WebElement> listofproduct;
    @FindBy(id = "SortBy")
    WebElement filterdropdown;
    @FindBy(css = "div.facet-filters.sorting.caption > div")
    WebElement filteroption;
    @FindBy(xpath = "//div[@class='card__information sd2']/div/h3/a")
    List<WebElement> filterproductname;
    @FindBy(xpath = "//div[@class='price__regular']/span[2]")
    List<WebElement> filterproductprice;
    @FindBy(xpath = "//button[@class='add-to-cart-button']")
    List<WebElement> addtocart;


    public Searchpom(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void entersearch() throws InterruptedException {
        List<String> arr = new ArrayList<>();
        arr.add("wine");
        arr.add("kids");

        System.out.println(arr.size());

        for (int i = 0; i < arr.size(); i++) {
            int flag = 0;
            JavascriptExecutor js3 = (JavascriptExecutor) driver;
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOf(searchelement));
            searchelement.sendKeys(arr.get(i));
            System.out.println("product name enter in the search field := " + arr.get(i));
            wait.until(ExpectedConditions.visibilityOf(searchbuttonpress));
            searchbuttonpress.click();
            Thread.sleep(1000);

            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement dropdown = wait1.until(ExpectedConditions.elementToBeClickable(By.id("SortBy")));

            Select select = new Select(dropdown);
            select.selectByValue("price-ascending");

            Thread.sleep(1000);
            for (int k = 0; k < filterproductname.size(); k++) {
                String productname = filterproductname.get(k).getText();
                String productprice = filterproductprice.get(k).getText();
                System.out.println("Filter product name: " + productname + " | Price: " + productprice);
            }
            Thread.sleep(2000);
            advancesearch.clear();
            advancesearch.sendKeys(arr.get(i));

            Thread.sleep(3000);


            for (WebElement ele : listofproduct) {
                JavascriptExecutor js4 = (JavascriptExecutor) driver;
                if (ele.getText().contains("Wine Glass") || ele.getText().contains("Kids Dinnerware")) {
                    ele.click();
                    Thread.sleep(1000);
                    for (WebElement cart : addtocart) {
                        js4.executeScript("arguments[0].scrollIntoView({block: 'center'});", cart);
                        Thread.sleep(500); // Allow scroll animation to complete

                        try {
                            wait.until(ExpectedConditions.elementToBeClickable(cart)).click();
                        } catch (Exception e) {
                            // Fall back to JS click if normal click fails
                            System.out.println("Normal click failed, trying JS click");
                            js4.executeScript("arguments[0].click();", cart);
                            Thread.sleep(500);
                            driver.navigate().back();


                        }
                    }

                    Thread.sleep(500);
                    for (int j = 0; j < 2; j++) {
                        driver.navigate().back();
                    }
                    flag = 1;
                    break;


                }


            }
            if (flag == 0) {
                System.out.println("product not found ");
            }
            searchelement.clear();


        }


    }

    public void endsearch() throws InterruptedException {
        List<String> arr = new ArrayList<>();
        arr.add("wine");
        arr.add("kids");

        System.out.println(arr.size());

        for (int i = 0; i < arr.size(); i++) {
            int flag = 0;

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Wait for and enter search
            wait.until(ExpectedConditions.visibilityOf(searchelement));
            searchelement.clear();
            searchelement.sendKeys(arr.get(i));
            System.out.println("Product name entered in search field: " + arr.get(i));

            wait.until(ExpectedConditions.elementToBeClickable(searchbuttonpress)).click();
            Thread.sleep(1000);

            // Sort by price ascending
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("SortBy")));
            new Select(dropdown).selectByValue("price-ascending");
            Thread.sleep(1000);

            // Print filter product name and price
            for (int k = 0; k < filterproductname.size(); k++) {
                String productname = filterproductname.get(k).getText();
                String productprice = filterproductprice.get(k).getText();
                System.out.println("Filtered product name: " + productname + " | Price: " + productprice);
            }

            Thread.sleep(2000);

            advancesearch.clear();
            advancesearch.sendKeys(arr.get(i));
            Thread.sleep(3000);

            // ✅ Refetch product list fresh (to avoid stale elements)
            List<WebElement> products = driver.findElements(By.xpath("//li[@class='predictive-search__list-item']")); // Replace with actual selector

            for (WebElement ele : products) {
                String text = ele.getText();
                if (text.contains("Wine Glass") || text.contains("Kids Dinnerware")) {
                    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", ele);
                    Thread.sleep(500);

                    ele.click(); // Navigates to product page
                    Thread.sleep(1000);

                    List<WebElement> initialCartButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                            By.xpath("//button[@class='add-to-cart-button']")
                    ));
                    int cartCount = initialCartButtons.size();

                    for (int h = 0; h < cartCount; h++) {
                        // Re-fetch the cart buttons on each loop iteration
                        List<WebElement> cartIcons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                                By.xpath("//button[@class='add-to-cart-button']")
                        ));

                        WebElement cartButton = cartIcons.get(h);
                        // Scroll into view (optional but recommended)
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", cartButton);

                        try {
                            wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
                        } catch (Exception e) {
                            System.out.println("Failed to click button at index " + h + ": " + e.getMessage());
                            continue; // Skip to next iteration if click fails
                        }

                        // Go back and refresh
                        driver.navigate().back();

                        // Wait for page to load before refresh
                        wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.xpath("//button[@class='add-to-cart-button']")
                        ));

                        driver.navigate().refresh();

                        // Give time for refresh to fully render
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    Thread.sleep(500);

                    // ✅ Navigate back and wait after each
                    for (int j = 0; j < 2; j++) {
                        driver.navigate().back();
                        Thread.sleep(1000);
                    }

                    flag = 1;
                    break; // Stop checking more products for this search term
                }
            }

            if (flag == 0) {
                System.out.println("Product not found");
            }

            searchelement.clear(); // Prepare for next search term
        }
    }

    public void searchaddtocart() throws InterruptedException {
        List<String> arr = new ArrayList<>();
        arr.add("Whisky glass");
        arr.add("Glass Holder");
        for (int i = 0; i < arr.size(); i++) {
            int flag = 0;
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            wait.until(ExpectedConditions.visibilityOf(searchelement));
            searchelement.clear();
            searchelement.sendKeys(arr.get(i));
            System.out.println("Product name entered in search field: " + arr.get(i));
            wait.until(ExpectedConditions.elementToBeClickable(searchbuttonpress)).click();
            Thread.sleep(1000);
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("SortBy")));
            new Select(dropdown).selectByValue("price-ascending");
            Thread.sleep(1000);
            for (int k = 0; k < filterproductname.size(); k++) {
                String productname = filterproductname.get(k).getText();
                String productprice = filterproductprice.get(k).getText();
                System.out.println("Filtered product name: " + productname + " | Price: " + productprice);
            }
            Thread.sleep(1000);

            advancesearch.clear();
            advancesearch.sendKeys(arr.get(i));
            Thread.sleep(1000);
            List<WebElement> products = driver.findElements(By.xpath("//li[@class='predictive-search__list-item']"));
            for (WebElement ele : products) {
                String text = ele.getText();
                if (text.contains("Whisky glass") || text.contains("Glass Holder")) {
                    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", ele);
                    Thread.sleep(500);
                    ele.click();
                    Thread.sleep(1000);
                    List<WebElement> initialCartButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                            By.xpath("//button[@class='add-to-cart-button']")
                    ));
                    int cartCount = initialCartButtons.size();
                    for (int h = 0; h < cartCount; h++) {
                        // Re-fetch the cart buttons on each loop iteration
                        List<WebElement> cartIcons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                                By.xpath("//button[@class='add-to-cart-button']")
                        ));
                        WebElement cartButton = cartIcons.get(h);
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", cartButton);

                        try {
                            wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
                        } catch (Exception e) {
                            System.out.println("Failed to click button at index " + h + ": " + e.getMessage());
                            continue; // Skip to next iteration if click fails
                        }
                        // Go back and refresh
                        driver.navigate().back();
                        wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.xpath("//button[@class='add-to-cart-button']")
                        ));
                        driver.navigate().refresh();

                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Thread.sleep(500);
                    for (int j = 0; j < 2; j++) {
                        driver.navigate().back();
                        Thread.sleep(1000);
                    }

                    flag = 1;
                    break; // Stop checking more products for this search term
                }
            }


        }


    }
}



