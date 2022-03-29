package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomerOptions {

    By transactionOperation = By.xpath("//button[@ng-class='btnClass1']");
    By depositOperation = By.xpath("//button[@ng-class='btnClass2']");
    By withdrawOperation = By.xpath("//button[@ng-class='btnClass3']");
    By amountToDeposit = By.xpath("//input[@placeholder='amount']");
    By depositAmountBtn = By.xpath("//button[@class='btn btn-default']");
    By amountToWithdraw = By.xpath("//input[@placeholder='amount']");
    By withdrawAmountBtn = By.xpath("//button[text()='Withdraw']");
    By balance = By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]");
    By logout = By.xpath("//button[@class='btn logout']");
    By home = By.xpath("/html/body/div/div/div[1]/button[1]");

    public WebElement selectTransactionOperation(WebDriver driver){
        return driver.findElement(transactionOperation);
    }

    public WebElement selectDepositOperation(WebDriver driver){
        return driver.findElement(depositOperation);
    }

    public WebElement selectWithdrawOperation(WebDriver driver){
        return driver.findElement(withdrawOperation);
    }

    public WebElement enterAmountToDeposit(WebDriver driver){
        return driver.findElement(amountToDeposit);
    }

    public WebElement selectDepositBtn(WebDriver driver){
        return driver.findElement(depositAmountBtn);
    }

    public WebElement selectAmountToWithdraw(WebDriver driver){
        return driver.findElement(amountToWithdraw);
    }

    public WebElement selectWithdrawAmountBtn(WebDriver driver){
        return driver.findElement(withdrawAmountBtn);
    }

    public WebElement findBalance(WebDriver driver){
        return driver.findElement(balance);
    }

    public WebElement clickLogout(WebDriver driver){
        return driver.findElement(logout);
    }

    public WebElement clickHome(WebDriver driver){
        return driver.findElement(home);
    }

}

