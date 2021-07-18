package page_objects;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


public class RegisterPage {
    private ChromeDriver driver = null;
    private String userEmail;
    private JavascriptExecutor javascriptExecutor;
    private WebDriver webdriver;
    public RegisterPage(String userEmail, ChromeDriver driver){
        this.driver = driver;
        this.userEmail = userEmail;
        javascriptExecutor = ((JavascriptExecutor) driver);

    }

    private WebElement getElement(String method, String locator){
        if(method == "xpath"){
            return driver.findElement(By.xpath(locator));
        }
        return driver.findElement(By.id(locator));
    }
    private void populateInputFields(){
        getElement("id","email").sendKeys(userEmail);
        getElement("id","emailConfirmation").sendKeys(userEmail);
        getElement("id","password").sendKeys("Secret_password01");
        getElement("id","firstName").sendKeys("FirstName");
        getElement("id","lastName").sendKeys("LastName");
        //javascript code to scroll down page so checkbox can be clicked
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        getElement("id","termsAndConditionsConsent").click();
    }

    public void registerUser() {
        populateInputFields();
//        getElement("xpath","//*[@data-automation-id='signup-form-submit-button']").click();
    }
}
