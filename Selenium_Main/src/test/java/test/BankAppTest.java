package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import data.DepositWithdrawlData;
import data.ReadNewCustomer;
import data.ScreenShot;
import org.apache.commons.exec.ExecuteWatchdog;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import pages.*;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BankAppTest {

    FirstPage firstPage = new FirstPage();
    CustomerLogin customerLogin = new CustomerLogin();
    CustomerOptions customerOptions = new CustomerOptions();
    CustomerTransactions customerTransactions = new CustomerTransactions();
    BankOptions bankOptions = new BankOptions();
    ReadNewCustomer readNewCustomer = new ReadNewCustomer();
    DepositWithdrawlData depositWithdrawlData = new DepositWithdrawlData();
    ScreenShot screenShot = new ScreenShot();
    ExtentReports extentReports;
    ExtentHtmlReporter htmlReporter;
    ExtentTest testCase;

    @Test(priority = 1)
    public void loginTest() throws IOException, InterruptedException, AWTException {
        WebDriver driver = initialSetup();
        testCase = extentReports.createTest("Verify Add Customer and Open Account");
        bankOptions.clickManagerLogin(driver).click();
        int i=1,id = 6;;
        for(int k=0;k<3;k++){
            bankOptions.clickAddCustomerOperation(driver).click();
            int j=0;
            String firstName = readNewCustomer.sendCustomer(i,j);
            j++;
            String lastName = readNewCustomer.sendCustomer(i,j);
            j++;
            String postNum = readNewCustomer.sendCustomer(i,j);
            j++;
            bankOptions.clickFirstname(driver).sendKeys(firstName);
            Thread.sleep(1000);
            bankOptions.clickLastname(driver).sendKeys(lastName);
            Thread.sleep(1000);
            bankOptions.clickPostcode(driver).sendKeys(postNum);
            Thread.sleep(1000);
            bankOptions.clickAddCustomerBtn(driver).click();
            Thread.sleep(1000);
            Alert popup=driver.switchTo().alert();
            String popupText = popup.getText();
            if(popupText.contains("duplicate")){
                //ScreenShot.failScreenshot(driver,"duplicateUser");
                System.out.println("Customer Already Exist");
                testCase.log(Status.FAIL,"Customer not added");
                ScreenShot.alertFail("addCustomerFail");
                Thread.sleep(1000);
            }
            else{
                testCase.log(Status.PASS,"Customer Added successfully");
                ScreenShot.alertSuccess("addCustomerPass");
                bankOptions.addCustomerAlertMessage(driver);
                Thread.sleep(1000);
                bankOptions.clickOpenAccountOperation(driver).click();
                Thread.sleep(1000);
                bankOptions.selectCustomerName(driver,id);
                id++;
                Thread.sleep(1000);
                bankOptions.selectCurrency(driver,"Rupee");
                Thread.sleep(1000);
                bankOptions.selectProcessBtn(driver).click();
                Thread.sleep(1000);
                testCase.log(Status.INFO,"Account Opened");
                ScreenShot.alertSuccess("openAccountPass");
                bankOptions.openAccountAlertMessage(driver);
                Thread.sleep(1000);
//                bankOptions.clickCustomersOperation(driver).click();
//                Thread.sleep(1000);
                i++;
            }
        }
        driver.quit();
        extentReports.flush();
    }

    @Test
    public void depositTest() throws IOException, InterruptedException {
        WebDriver driver = initialSetup();
        testCase = extentReports.createTest("Verify Deposit Option");
        firstPage.clickCustomerLoginBtn(driver).click();
//        String firstName = readNewCustomer.sendCustomer(1,0);
//        String lastName = readNewCustomer.sendCustomer(1,0);
//        String fullName = firstName+" "+lastName;
        customerLogin.selectUsername(driver,"Ron Weasly");
        Thread.sleep(1000);
        customerLogin.clickLoginBtn(driver).click();
        Thread.sleep(1000);
        for(int i = 1;i<5 ;i++){
            String value = depositWithdrawlData.sendValue(i,0);
            String initialBalance=customerOptions.findBalance(driver).getText();
            System.out.println("Initial Balance "+initialBalance);
            customerOptions.selectDepositOperation(driver).click();
            Thread.sleep(1000);
            customerOptions.enterAmountToDeposit(driver).sendKeys(value);
            Thread.sleep(1000);
            customerOptions.selectDepositBtn(driver).click();
            Thread.sleep(1000);
            String finalBalance=customerOptions.findBalance(driver).getText();
            System.out.println("Final Balance "+finalBalance);
            System.out.println("Amount from excel "+value);
            int fb=Integer.parseInt(finalBalance)-Integer.parseInt(initialBalance);
            String balance=customerOptions.findBalance(driver).getText();
            String str=String.valueOf(fb-(Integer.parseInt(initialBalance)));
            if(str.equals(value.replace("\"",""))){
                System.out.println("Balance Verified successfully");
                testCase.log(Status.PASS,"Deposited Successfully");
                screenShot.passScreenshot(driver,"passBalanceDeposit");
            }else{
                System.out.println("Balance not updated");
                testCase.log(Status.FAIL,"Deposit Failed");
                screenShot.failScreenshot(driver,"failBalanceDeposit");
            }
            customerOptions.enterAmountToDeposit(driver).clear();
        }
        driver.quit();
        extentReports.flush();
    }


    @Test
    public void withdrawlTest() throws InterruptedException, IOException {
        WebDriver driver = initialSetup();
        testCase = extentReports.createTest("Verify Withdrawl Option");
        firstPage.clickCustomerLoginBtn(driver).click();
        customerLogin.selectUsername(driver,"Ron Weasly");
        Thread.sleep(1000);
        customerLogin.clickLoginBtn(driver).click();
        Thread.sleep(1000);
        String value = depositWithdrawlData.sendValue(4,0);
        customerOptions.selectDepositOperation(driver).click();
        Thread.sleep(1000);
        customerOptions.enterAmountToDeposit(driver).sendKeys(value);
        Thread.sleep(1000);
        customerOptions.selectDepositBtn(driver).click();
        Thread.sleep(1000);
        for(int i=1;i<6;i++){
            int initialBalance= Integer.parseInt(customerOptions.findBalance(driver).getText());
            String withValue = depositWithdrawlData.sendValue(i,1);
            customerOptions.selectWithdrawOperation(driver).click();
            Thread.sleep(1000);
            customerOptions.selectAmountToWithdraw(driver).sendKeys(withValue);
            Thread.sleep(1000);
            customerOptions.selectWithdrawAmountBtn(driver).click();
            Thread.sleep(1000);
            int finalBalance= Integer.parseInt(customerOptions.findBalance(driver).getText());
            if(String.valueOf(initialBalance-finalBalance).equals(withValue.replace("\"",""))){
                ScreenShot.passScreenshot(driver,"passWithdrawl");
                testCase.log(Status.PASS,"Withdrawl Successful");
                System.out.println("Balance Updated Successfully");
            }else {
                ScreenShot.failScreenshot(driver,"failWithDrawl");
                testCase.log(Status.FAIL,"Withdrawl Not Successful");
                System.out.println("Balance not updated");
            }
            customerOptions.selectAmountToWithdraw(driver).clear();
        }
        driver.quit();
        extentReports.flush();
    }


    @Test
    public void transactionTest() throws InterruptedException, IOException {
        WebDriver driver = initialSetup();
        testCase = extentReports.createTest("Verify Transactions Option");
        firstPage.clickCustomerLoginBtn(driver).click();
        customerLogin.selectUsername(driver,"Ron Weasly");
        Thread.sleep(1000);
        customerLogin.clickLoginBtn(driver).click();
        Thread.sleep(1000);
        String depositValue = depositWithdrawlData.sendValue(4,0);
        System.out.println(depositValue);
        customerOptions.selectDepositOperation(driver).click();
        Thread.sleep(1000);
        customerOptions.enterAmountToDeposit(driver).sendKeys(depositValue);
        Thread.sleep(1000);
        customerOptions.selectDepositBtn(driver).click();
        Thread.sleep(1000);
        customerOptions.selectTransactionOperation(driver).click();
        Thread.sleep(1000);
        customerTransactions.sortByDateAndTime(driver).click();
        Thread.sleep(1000);
        String latest ="\""+customerTransactions.latestAmount(driver)+"\"";
        System.out.println(latest);
        if(depositValue.equals(latest)){
            System.out.println("Deposit Transaction Verified");
            testCase.log(Status.PASS,"Deposit Transaction Successful");
            ScreenShot.passScreenshot(driver,"passDepositTransaction");

        }
        else{
            System.out.println("Deposit Transaction not Verified");
            testCase.log(Status.FAIL,"Deposit Transaction Not Successful");
            ScreenShot.failScreenshot(driver,"failDepositTransaction");
        }
        customerTransactions.clickBackBtn(driver).click();
        for(int i=4;i<6;i++){
            String withValue = depositWithdrawlData.sendValue(i,1);
            customerOptions.selectWithdrawOperation(driver).click();
            Thread.sleep(1000);
            customerOptions.selectAmountToWithdraw(driver).sendKeys(withValue);
            Thread.sleep(1000);
            customerOptions.selectWithdrawAmountBtn(driver).click();
            Thread.sleep(1000);
            Thread.sleep(1000);
            customerOptions.selectTransactionOperation(driver).click();
            Thread.sleep(1000);
            customerTransactions.sortByDateAndTime(driver).click();
            Thread.sleep(1000);
            String latestWithdrawl ="\""+customerTransactions.latestAmount(driver)+"\"";
            if(withValue.equals(latestWithdrawl)){
                System.out.println("Withdrawl Transaction Verified");
                testCase.log(Status.PASS,"Withdrawl Transaction Successful");
                ScreenShot.passScreenshot(driver,"passWithdrawlTransaction");
            }
            else{
                System.out.println("Withdrawl Transaction not Verified");
                testCase.log(Status.FAIL,"Withdrawl Transaction Not Successful");
                ScreenShot.failScreenshot(driver,"failWithdrawlTransaction");
            }
            customerTransactions.clickBackBtn(driver).click();
        }
        driver.quit();
        extentReports.flush();
    }


    public WebDriver initialSetup(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\jagats\\Downloads\\chromedriver.exe");
        extentReports=new ExtentReports();
        htmlReporter=new ExtentHtmlReporter("ExtentReport.html");
        extentReports.attachReporter(htmlReporter);
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        return driver;
    }

}