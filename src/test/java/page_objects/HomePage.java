package page_objects;

import org.openqa.selenium.chrome.ChromeDriver;

public class HomePage extends CinemaCity {
    private String country;
    private String link_text;

    public HomePage(String country, ChromeDriver driver){
        super(driver);
        this.country = country;
        setLinkText();
    }

    private void setLinkText(){
        if(country.contains("PL")){
            link_text = "Rejestracja";
        }else{
            link_text = "Zaregistrovat se";
        }
    }

    private void closeCookie(){
        getElement("id", "consent_prompt_submit").click();
    }

    public void goToRegisterPage() {
        if(country.contains("CZ")){
            closeCookie();
        }
        getElement("partialLinkText", link_text).click();
    }
}
