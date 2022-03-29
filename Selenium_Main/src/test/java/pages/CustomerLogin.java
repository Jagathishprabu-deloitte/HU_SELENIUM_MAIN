package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CustomerLogin {

    By dropdown = By.xpath("//select[@id='userSelect']");
    By loginBtn = By.xpath("//button[text()='Login']");

    public void selectUsername(WebDriver driver,String username){
        WebElement dropdownBox=driver.findElement(dropdown);
        Select select=new Select(dropdownBox);
        select.selectByVisibleText(username);
    }

    public WebElement clickLoginBtn(WebDriver driver){
        return driver.findElement(loginBtn);
    }

}