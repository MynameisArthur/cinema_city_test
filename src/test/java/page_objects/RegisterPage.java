package page_objects;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RegisterPage extends CinemaCity {
    private String userEmail;
    private JavascriptExecutor javascriptExecutor;

    public RegisterPage(String userEmail, ChromeDriver driver){
        super(driver);
        this.userEmail = userEmail;
        javascriptExecutor = ((JavascriptExecutor) driver);
    }

    private void populateInputFields(){
        getElement("id","email").sendKeys(userEmail);
        getElement("id","emailConfirmation").sendKeys(userEmail);
        getElement("id","password").sendKeys("Secret_password01");
        getElement("id","firstName").sendKeys("FirstName");
        getElement("id","lastName").sendKeys("LastName");
        //javascript code to scroll down page so checkbox and signup button can be clicked
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        getElement("id","termsAndConditionsConsent").click();
    }

    public void registerUser() {
        populateInputFields();
        getElement("xpath","//*[@data-automation-id='signup-form-submit-button']").click();
    }
}
