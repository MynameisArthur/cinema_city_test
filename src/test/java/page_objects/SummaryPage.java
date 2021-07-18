package page_objects;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class SummaryPage {
    private ChromeDriver driver = null;
    String userEmail;
    public SummaryPage(String userEmail, ChromeDriver driver){
        this.driver = driver;
        this.userEmail = userEmail;
    }
    private WebElement emailParagraph(){
        return driver.findElement(By.xpath("//*[contains(text(),'"+userEmail+"')]"));
    }
    private void takeScreenshot(WebDriver webdriver) throws IOException {
        File srcFile = ((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);
        //get milliseconds from current time to concatenate to the screenshot name
        long currentTime = (new Date()).getTime();
        FileUtils.copyFile(srcFile,new File("src/screenshots/screenshot_"+ currentTime +".jpg"));
    }
    public void checkEmail() throws IOException {
        takeScreenshot(driver);
        Assert.assertEquals("Check if displayed email is the same as email of registered user",userEmail,
                emailParagraph().getText());
    }
}
