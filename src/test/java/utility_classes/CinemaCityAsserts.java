package utility_classes;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
    public void assertCorrectEmail(){
        assertions++;
        Pattern emailRegex =  Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailRegex.matcher(userEmail);
        assertTrue("Assert if email: \""+userEmail+"\" is formed correctly ", matcher.find());
        successMessage("#"+assertions+". Email: \"" + userEmail + "\" formed correctly.");

    }
    public void assertRegisterLinkText(){
        assertions++;
        assertEquals("Assert if link text matches country instance", linkText,
                driver.findElement(By.partialLinkText(linkText)).getText());
        successMessage(
                "#"+assertions+". Expected Link text for the country instance " +
                        country + " is \""+linkText+"\" received \""+linkText+"\""
        );
    }
    public void assertRegisterButtonEnabled(){
        assertions++;
        assertTrue("Assert if register button is enabled ",driver.findElement(
                By.xpath("//*[@data-automation-id='signup-form-submit-button']")).isEnabled()
        );
        successMessage("#"+assertions+". Register button is enabled");
    }
    public void assertEmailDisplayed(){
        assertions++;
        assertEquals("Assert if paragraph with registered user email is displayed",
                true,
                driver.findElements(By.xpath("//*[contains(text(),'" + userEmail + "')]")).size() > 0
        );
        assertEquals(
                "Assert if paragraph with registered user email is the same as email: \"" + userEmail + "\" used in registration.",
                userEmail,
                driver.findElement(By.xpath("//*[contains(text(),'"+userEmail+"')]")).getText()
        );
        successMessage(
                "#"+assertions+". User registration complete. Expected email: \""+
                        userEmail+"\" received: \""+driver.findElement(By.xpath("//*[contains(text(),'"+userEmail+"')" +
                        "]")).getText()+"\""
        );
    }

}
