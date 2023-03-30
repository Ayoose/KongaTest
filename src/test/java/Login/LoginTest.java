package Login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginTest {

    public void setUp() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");


        //to fix the error "data;" URL onLaunch
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        //instantiation
        WebDriver webDriver = new ChromeDriver(options);

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
        Thread.sleep(3000);

        //To input username
        webDriver.findElement(By.id("username")).sendKeys("******@gmail.com");

        //3 seconds delay for page load
        Thread.sleep(2000);

        //To input password
        webDriver.findElement(By.id("password")).sendKeys("******");

        //3 seconds delay for page load
        Thread.sleep(2000);

        //To click the Login Button
        webDriver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();

        //7 seconds delay for successful login
        Thread.sleep(5000);


        //To Logout user
        webDriver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div/div/div/div[4]/div/a/span")).click();

        //3 seconds delay for page load
        Thread.sleep(3000);

        //Clicking Logout Button
        webDriver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div/div/div/div[4]/div/ul/li[7]/a")).click();

        //5 seconds delay to confirm logout status
        Thread.sleep(5000);

        //To close the Chrome Driver
        webDriver.quit();


    }


    //main method declaration
    public static void main(String[] args) throws InterruptedException {
        //create an object of the class LoginTest
        LoginTest executeTest = new LoginTest();

        //call setUp method
        executeTest.setUp();
    }
}
