package page_objects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/*
 *  Page object for summary page
 * */


public class SummaryPage extends CinemaCity {
    String userEmail;
    public SummaryPage(String userEmail, ChromeDriver driver){
        super(driver);
        this.userEmail = userEmail;
    }

    private void takeScreenshot(WebDriver webdriver) throws IOException {
        File srcFile = ((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);
        //get milliseconds from current time to concatenate to the screenshot name
        long currentTime = (new Date()).getTime();
        FileUtils.copyFile(srcFile,new File("src/screenshots/screenshot_"+userEmail+"_"+currentTime+".jpg"));
    }
    public void checkEmail() throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class,'bg-danger')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'"+userEmail+"')]"))
        ));
        takeScreenshot(driver);
    }
}
