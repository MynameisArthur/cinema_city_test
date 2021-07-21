import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page_objects.HomePage;
import utility_classes.PageLoader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static utility_classes.MessageDisplay.message;

public class CinemaCityTest {
    WebDriver driver;
    String country;
    // path to test data document
    Path path;
    // email string retrieved from test data document used in tests
    String linkText;
    // assertions counter used in success message displays
    int assertions = 0;
    @Before
    public void setUpClass() throws URISyntaxException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // load country instance from the system properties
        country = System.getProperty("country.instance").toUpperCase(Locale.ROOT);
        // create path to testing data document
        path = Paths.get(CinemaCityTest.class.getResource("email_list.txt").toURI());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void mainTest() throws IOException, InterruptedException {
        //Load page
        PageLoader page = new PageLoader(country, path,(ChromeDriver) driver);
        page.loadPage();
        //Load user email
        String userEmail = page.loadEmail();
        //Starting page tests
        HomePage homePage = new HomePage(country,(ChromeDriver) driver);
        homePage.goToRegisterPage();
        linkText = country.equals("PL") ? "Rejestracja" : "Zaregistrovat se";
        assertRegisterLink();

        //Register page tests
//        RegisterPage registerPage = new RegisterPage(userEmail,(ChromeDriver) driver);
//        registerPage.registerUser();
//        //Summary page tests
//        SummaryPage summaryPage = new SummaryPage(userEmail,(ChromeDriver) driver);
//        summaryPage.checkEmail();
    }
    private void assertRegisterLink(){
        assertions++;
        assertEquals("Compare link texts", linkText,driver.findElement(By.partialLinkText(linkText)).getText());
        message("#"+assertions+". Expected Link text for the country instance " + country + " is \""+linkText+
                "\", " +
                "received \""+linkText+"\"");
    }
}