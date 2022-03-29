package data;

import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShot {
    public static void passScreenshot(WebDriver driver,String name) throws IOException {
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String fileName=sdf.format(date);
        FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"/passedScreenshots/"+name+fileName+".png"));
    }
    public static void failScreenshot(WebDriver driver,String name) throws IOException {
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HHmmss");
        File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String fileName=sdf.format(date);
        FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"/failedScreenshots/"+name+fileName+".png"));
    }
    public static void alertSuccess(String name) throws AWTException, IOException {
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HHmmss");
        String fileName=sdf.format(date);
        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIO.write(image,"png",new File(System.getProperty("user.dir")+"/passedScreenshots/"+name+fileName+".png"));
    }
    public static void alertFail(String name) throws AWTException, IOException {
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HHmmss");
        String fileName=sdf.format(date);
        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIO.write(image,"png",new File(System.getProperty("user.dir")+"/failedScreenshots/"+name+fileName+".png"));
    }
}