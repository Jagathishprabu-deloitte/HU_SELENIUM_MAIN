package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FirstPage {

    By customerLoginBtn = By.xpath("//button[text()='Customer Login']");
    By bankManagerLoginBtn = By.xpath("//button[text()='Bank Manager Login']");

    public WebElement clickCustomerLoginBtn(WebDriver driver){
        return driver.findElement(customerLoginBtn);
    }

    public WebElement clickBankManagerLogin(WebDriver driver){
        return driver.findElement(bankManagerLoginBtn);
    }

}