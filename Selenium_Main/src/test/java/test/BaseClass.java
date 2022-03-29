//package test;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
//import java.util.concurrent.TimeUnit;
//
//public class BaseClass {
//
//    public static WebDriver driver;
//
//    @BeforeTest
//    public WebDriver initialSetup(){
//        System.setProperty("webdriver.chrome.driver","C:\\Users\\jagats\\Downloads\\chromedriver.exe");
//        driver=new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
//        return driver;
//    }
//
//
//}