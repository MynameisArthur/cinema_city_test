import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.HomePage;
import page_objects.RegisterPage;
import page_objects.SummaryPage;
import utility_classes.CinemaCityAsserts;
import utility_classes.PageLoader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

public class CinemaCityTest {
    WebDriver driver;
    String country;
    // path to test data document
    Path path;
    // email string retrieved from test data document used in tests
    String userEmail;
    // text in link to register page
    String linkText;
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
        PageLoader page = new PageLoader(country, path, (ChromeDriver) driver);
        page.loadPage();
        //Load user email
//        userEmail = page.loadEmail();
        userEmail = "honorata@gmail.com";
        //Starting page tests
        HomePage homePage = new HomePage(country,(ChromeDriver) driver);
        homePage.goToRegisterPage();
        linkText = country.equals("PL") ? "Rejestracja" : "Zaregistrovat se";
        // instantiate CinemaCityAsserts utility class
        CinemaCityAsserts ccAsserts = new CinemaCityAsserts(userEmail, linkText, country,
                (ChromeDriver) driver);
        ccAsserts.assertRegisterLinkText();

        //Register page tests
        RegisterPage registerPage = new RegisterPage(userEmail,(ChromeDriver) driver);
        registerPage.registerUser();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-automation-id='signup-form-submit-button']")));
        ccAsserts.assertRegisterButtonEnabled();

        //Summary page tests
        SummaryPage summaryPage = new SummaryPage(userEmail,(ChromeDriver) driver);
        summaryPage.checkEmail();
        ccAsserts.assertEmailDisplayed();
    }

}