package Login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {

    private WebDriver webDriver;


    private By ModalButton = By.xpath("//]/div/div[2]/div[3]/div[2]/div/button");
    private By ModalCardbutton = By.xpath("//*[@id=\"channel-template\"]/div[2]/div/div[2]/button/div/span[1]");
    private By ModalCardNumber = By.id("card-number");
    private By ModalDate = By.id("expiry");
    private By ModalCVV = By.id("id=\"cvv\"");
    private By ModalPayNowButton = By.id("validateCardForm");
    private By CloseIframe = By.xpath("/html/body/section/section/section/div[2]/div[1]/aside");

    @BeforeTest
    public void setUp() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");

        //to fix the error "data;" URL onLaunch
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        //instantiation
        webDriver = new ChromeDriver(options);

    /***** Step 1. Visit the URL Konga *****/

        //To Open URL
        webDriver.get("https://www.konga.com/");

        Thread.sleep(5000);

        //To maximize the window
        webDriver.manage().window().maximize();

        //To get the Page Title
        System.out.println(webDriver.getTitle());

        //To delay script execution so that the webpage can load completely
        Thread.sleep(7000);

        //To click the Login slider button
        webDriver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div/div/div/div[4]/a")).click();

        //5 seconds delay for page load
        Thread.sleep(5000);

        //To Logout user
        //webDriver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div/div/div/div[4]/div/a/span")).click();

        //3 seconds delay for page load
        //Thread.sleep(3000);

        //Clicking Logout Button
        //webDriver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div/div/div/div[4]/div/ul/li[7]/a")).click();

    }


    /***** Step 2. Sign in to Konga successfully *****/
    @Test(priority = 0)
    public void userLogin() throws InterruptedException {

        //To input username
        webDriver.findElement(By.id("username")).sendKeys("egunjobiayooluwa@gmail.com");

        //2 seconds delay for page load
        Thread.sleep(2000);

        //To input password
        webDriver.findElement(By.id("password")).sendKeys("waferanmi");

        //2 seconds delay for page load
        Thread.sleep(2000);

        //To click the Login Button
        webDriver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();

        //5 seconds delay for successful login
        Thread.sleep(5000);

        if (webDriver.getCurrentUrl().contains("https://www.konga.com/"))
            //Pass
            System.out.println("successful login");
        else
            //Fail
            System.out.println("incorrect login details");

        Thread.sleep(5000);
    }

    /***** Step 3. From the Categories, click on the “Computers and Accessories” *****/
    @Test(priority = 1)
    public void verifyCategory() throws InterruptedException {

        //To delay code execution
        Thread.sleep(2000);

        //Click computers and accessories category
        webDriver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[2]/div/a[2]")).click();

        //5 seconds delay for page load
        Thread.sleep(5000);

        //Verify current page
        if (webDriver.getCurrentUrl().contains("https://www.konga.com/category/accessories-computing-5227"))
        //if (webDriver.getCurrentUrl().contains("https://www.konga.com/"))
            //Pass
            System.out.println("You are viewing computer and accessories webpage");
        else
            //Fail
            System.out.println("You are on a wrong webpage");

        System.out.println(webDriver.getCurrentUrl());
        Thread.sleep(5000);
    }

    /***** Step 4. Click on the Laptop SubCategory *****/
    @Test(priority = 2)
    public void clickLaptop() throws InterruptedException {

        WebElement myLaptop = webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[3]/a/label/span"));
        myLaptop.click();

        if (webDriver.getCurrentUrl().contains("https://www.konga.com/category/accessories-computing-5227"))
            //Pass
            System.out.println("You are viewing laptop webpage");
        else
            //Fail
            System.out.println("You are on a wrong webpage");

        Thread.sleep(5000);
    }

    /***** Step 5. Click on the Apple MacBooks *****/
    @Test(priority = 3)
    public void clickAppleMacBooks() throws InterruptedException {

        WebElement myAppleMacBooks = webDriver.findElement(By.xpath("/html/body/div[1]/div/section/div[3]/section/main/section[3]/section/div/section/div[2]/div[2]/ul/li[3]/a/ul/li[1]/a/label/span"));
        myAppleMacBooks.click();
        if (webDriver.getCurrentUrl().contains("https://www.konga.com/category/accessories-computing-5227"))
            //Pass
            System.out.println("You are viewing Apple MacBooks webpage");
        else
            //Fail
            System.out.println("You are on a wrong webpage");
        Thread.sleep(5000);
    }

    /***** Step 6. Add an item to the cart *****/
    @Test(priority = 4)
    public void itemCart() throws InterruptedException {

        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/section/section/section/ul/li[1]/div/div/div[2]/form/div[3]/button")).click();
        if (webDriver.getCurrentUrl().contains("https://www.konga.com/category/accessories-computing-5227"))
            //Pass
            System.out.println("MacBook has been added to your cart");
        else
            //Fail
            System.out.println("MacBook failed to be added to cart");
        Thread.sleep(5000);
    }

    @Test(priority = 5)
    public void viewCart() throws InterruptedException {

        //To view my cart

        webDriver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/a[2]/span[1]")).click();
        String expectedUrl = "https://www.konga.com/category/accessories-computing-5227";
        String actualUrl = webDriver.getCurrentUrl();
        if (actualUrl.contains(expectedUrl))
            //Pass
            System.out.println("You are viewing your cart");
        else
            //Fail
            System.out.println("Error viewing cart");
        Thread.sleep(5000);
    }

    @Test(priority = 6)
    public void checkoutCart() throws InterruptedException {

        //To check out cart

        //Click continue to checkout button
        webDriver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[3]/section/section/aside/div[3]/div/div[2]/button")).click();
        String expectedUrl = "https://www.konga.com/category/accessories-computing-5227";
        String actualUrl = webDriver.getCurrentUrl();
        if (actualUrl.contains(expectedUrl))
            //Pass
            System.out.println("contain checkout button");
        else
            //Fail
            System.out.println("not contain checkout button");
        Thread.sleep(5000);
    }

    /***** Step 7. Select Address *****/
    @Test(priority = 7)
    public void selectAddress() throws InterruptedException {

        //10 seconds delay for complete page load
        Thread.sleep(10000);

        //Click change address
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div/div[2]/div/button")).click();

        //2 seconds delay
        Thread.sleep(2000);

        //Click add address
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[2]/div/div[2]/div/div/button")).click();
        Thread.sleep(5000);

        //Select address from list
        webDriver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[2]/div/div/div[2]/div[1]/form/button")).click();
        Thread.sleep(5000);

        //Use selected address
        webDriver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[3]/div/div/div/a")).click();
        Thread.sleep(5000);
    }

    /*
    @Test(priority = 8)
    public void AddDeliveryaddress() throws InterruptedException {
//7.click on add delivery address
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[2]/div[1]/div[2]/div[1]/div/button")).click();
//Select address from address book
        webDriver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[2]/div/div/div[2]/div[1]/form/button")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 9)
    public void UseThisAddress() throws InterruptedException {
//click on use this address
        webDriver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[3]/div/div/div/a")).click();
        Thread.sleep(5000);
    } */

    @Test(priority = 8)
    public void selectCardPayment() throws InterruptedException {
//9.select a card payment method:"Pay Now"
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[1]/div[1]/h2")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 9)
    public void ContinueToPayment() throws InterruptedException {
//10. click on continue to payment button
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[3]/div[2]/div/button")).click();
        Thread.sleep(5000);

    }
    @Test(priority = 10)
    public void ClickCard() throws InterruptedException {
        //click card
        click(ModalButton);
    }

    @Test(priority = 13)
    public void EnterCardDetails() throws InterruptedException {
//input invalid card number
//Test 8. verify that user should not input invalid card number on the field for card number for payment
        setText(ModalCardNumber, 12345678);
        setText(ModalDate, 02 / 25);
        setText(ModalCVV, 205);
        click(ModalPayNowButton);
        String expectedUrl = "https://www.konga.com/checkout/complete-order";
        String actualUrl = webDriver.getCurrentUrl();
        if (actualUrl.contains(expectedUrl))
            //Pass
            System.out.println("invalid card number");
        else
            //Fail
            System.out.println("valid card number");
        Thread.sleep(5000);
    }

    @Test(priority = 14)
    public void closeiframe() throws InterruptedException {
//12. close the iframe that displays the input card modal
        click(CloseIframe);
        Thread.sleep(5000);
    }
    private void setText(By modalCardNumber, int i) {
    }

    private void click(By modalButton) {
    }



    @AfterTest
    public void closeBrowser() {
        //Quit browser
        webDriver.quit();
    }

}
