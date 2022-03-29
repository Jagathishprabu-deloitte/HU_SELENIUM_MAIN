package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomerTransactions {

    By backBtn = By.xpath("//button[text()='Back']");
    By dateTime = By.xpath("/html/body/div/div/div[2]/div/div[2]/table/thead/tr/td[1]/a");
    By logout = By.xpath("//button[@class='btn logout']");
    By latest = By.xpath("//*[@id='anchor0']/td[2]");

    public WebElement clickBackBtn(WebDriver driver){
        return driver.findElement(backBtn);
    }

    public WebElement sortByDateAndTime(WebDriver driver){
        return driver.findElement(dateTime);
    }

    public String latestAmount(WebDriver driver){
        return driver.findElement(latest).getText();
    }

    public WebElement clickLogout(WebDriver driver){
        return driver.findElement(logout);
    }

}