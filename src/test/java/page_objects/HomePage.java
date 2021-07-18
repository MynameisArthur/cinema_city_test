package page_objects;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class HomePage {
    private ChromeDriver driver = null;
    private String country;
    private String link_text;
    public HomePage(String country, ChromeDriver driver){
        this.driver = driver;
        this.country = country;
        setLinkText();
    }
    private WebElement cookiePopup(){
        return driver.findElement(By.id("consent_prompt_submit"));
    }
    private void setLinkText(){
        if(country == "PL"){
            link_text = "Rejestracja";
        }else{
            link_text = "Zaregistrovat se";
        }
    }
    private WebElement registerLink(){
        return driver.findElement(By.partialLinkText(link_text));
    }
    private void closeCookie(){
        cookiePopup().click();
    }
    public void goToRegisterPage() {
        if(country == "CZ"){
            closeCookie();
        }
        registerLink().click();
    }
}
