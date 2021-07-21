package utility_classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utility_classes.MessageDisplay.failureMessage;
import static utility_classes.MessageDisplay.successMessage;


/*
* Utility class for assertions
* */


public class CinemaCityAsserts {
    // assertions counter used in success message displays
    int assertions = 0;
    String linkText;
    ChromeDriver driver;
    String country;
    String userEmail;
    public CinemaCityAsserts(String userEmail, String linkText, String country, ChromeDriver driver){
        this.linkText = linkText;
        this.driver = driver;
        this.country = country;
        this.userEmail = userEmail;
    }
    public void assertRegisterLinkText(){
        assertions++;
        assertEquals("Compare link texts", linkText,driver.findElement(By.partialLinkText(linkText)).getText());
        successMessage(
                "#"+assertions+". Expected Link text for the country instance " +
                        country + " is \""+linkText+"\" received \""+linkText+"\""
        );
    }
    public void assertRegisterButtonEnabled(){
        assertions++;
        assertTrue("Register button is enabled ",driver.findElement(
                By.xpath("//*[@data-automation-id='signup-form-submit-button']")).isEnabled()
        );
        successMessage("#"+assertions+". Register button is enabled");
    }
    public void assertEmailDisplayed(){
        assertions++;
        if(driver.findElements(By.xpath("//*[contains(text(),'"+userEmail+"')]")).size() > 0){
            WebElement emailParagraph = driver.findElement(By.xpath("//*[contains(text(),'"+userEmail+"')]"));
            assertEquals(
                    "Check if displayed email: \"" + userEmail + "\" is the same as the one used in registration",
                    userEmail,
//                    driver.findElement(By.xpath("//*[contains(text(),'"+userEmail+"')]")).getText()
                    emailParagraph.getText()
            );
            successMessage(
                    "#"+assertions+". User registration complete. Expected email: \""+
                            userEmail+"\" received: \""+emailParagraph.getText()+"\""
            );
        }else{
            failureMessage("#"+assertions+". User registration failed! Registered user with email: \""+userEmail+"\" " +
                    " already exists or other error occurred ");
        }
    }

}
