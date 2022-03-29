package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BankOptions {

    By bankManagerLogin = By.xpath("//button[text()='Bank Manager Login']");
    By addCustomerOperation = By.xpath("//button[@ng-class='btnClass1']");
    By openAccountOperation = By.xpath("//button[@ng-class='btnClass2']");
    By customersOperation = By.xpath("//button[@ng-class='btnClass3']");
    By firstname = By.xpath("//input[@placeholder='First Name']");
    By lastname = By.xpath("//input[@placeholder='Last Name']");
    By postcode = By.xpath("//input[@placeholder='Post Code']");
    By addCustomerBtn = By.xpath("//button[text()='Add Customer']");
    By userDropdown = By.xpath("//select[@id='userSelect']");
    By currencyDropdown = By.xpath("//select[@id='currency']");
    By processBtn = By.xpath("//button[text()='Process']");
    By searchUsername = By.xpath("");
    By deleteBtn = By.xpath("");

    public WebElement clickManagerLogin(WebDriver driver){
        return driver.findElement(bankManagerLogin);
    }

    public WebElement clickAddCustomerOperation(WebDriver driver){
        return driver.findElement(addCustomerOperation);
    }

    public WebElement clickOpenAccountOperation(WebDriver driver){
        return driver.findElement(openAccountOperation);
    }

    public WebElement clickCustomersOperation(WebDriver driver){
        return driver.findElement(customersOperation);
    }

    public WebElement clickFirstname(WebDriver driver){
        return driver.findElement(firstname);
    }

    public WebElement clickLastname(WebDriver driver){
        return driver.findElement(lastname);
    }

    public WebElement clickPostcode(WebDriver driver){
        return driver.findElement(postcode);
    }

    public WebElement clickAddCustomerBtn(WebDriver driver){
        return driver.findElement(addCustomerBtn);
    }

    public void addCustomerAlertMessage(WebDriver driver) throws InterruptedException {
        driver.switchTo().alert().accept();
    }

    public void selectCustomerName(WebDriver driver,int id){
        WebElement dropdownBox=driver.findElement(userDropdown);
        Select select=new Select(dropdownBox);
        select.selectByIndex(id);
    }

    public void selectCurrency(WebDriver driver,String currency){
        WebElement dropdownBox=driver.findElement(currencyDropdown);
        Select select=new Select(dropdownBox);
        select.selectByVisibleText(currency);
    }

    public WebElement selectProcessBtn(WebDriver driver){
        return driver.findElement(processBtn);
    }

    public void openAccountAlertMessage(WebDriver driver) throws InterruptedException {
        driver.switchTo().alert().accept();
    }

}