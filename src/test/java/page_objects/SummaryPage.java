package page_objects;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Date;

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
    public void checkEmail() throws IOException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'"+userEmail+"')]")));
        takeScreenshot(driver);

        Assert.assertEquals("Check if displayed email is the same as email of registered user",userEmail,
                getElement("xpath","//*[contains(text(),'"+userEmail+"')]").getText());
    }
}
