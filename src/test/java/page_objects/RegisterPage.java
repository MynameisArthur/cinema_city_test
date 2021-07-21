package page_objects;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

/*
 *  Page object for register page
 * */

public class RegisterPage extends CinemaCity {
    private String userEmail;
    private JavascriptExecutor javascriptExecutor;

    public RegisterPage(String userEmail, ChromeDriver driver){
        super(driver);
        this.userEmail = userEmail;
        javascriptExecutor = ((JavascriptExecutor) driver);
    }

    private void fillInputField(String locator, String text){
        getElement("id",locator).sendKeys(text);
    }

    private void populateInputFields(){
        fillInputField("email",userEmail);
        fillInputField("emailConfirmation",userEmail);
        fillInputField("password","Secret_password01");
        fillInputField("firstName","FirstName");
        fillInputField("lastName","LastName");
        //javascript code to scroll down page so checkbox and signup button can be clicked
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        getElement("id","termsAndConditionsConsent").click();
    }

    public void registerUser() {
        populateInputFields();
        getElement("xpath","//*[@data-automation-id='signup-form-submit-button']").click();
    }
}
