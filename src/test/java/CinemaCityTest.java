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
import utility_classes.DataReader;
import utility_classes.PageLoader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;



public class CinemaCityTest {
    WebDriver driver;
    String country;
    String userEmail;
    String linkText;
    PageLoader page;

    @Before
    public void setUpClass() throws URISyntaxException, IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // initialize DataReader needed as a dependency of PageLoader
        DataReader reader = new DataReader(
                getPath("properties")
        );
        // load country instance from the system properties
        country = reader.readProjectProps("country","CinemaCityProps.properties");
        // initialize PageLoader class
        page = new PageLoader(reader, country, (ChromeDriver) driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void mainTest() throws IOException, URISyntaxException {
        //Load user email
        userEmail = page.loadEmail();
        //Load page
        page.loadPage();
        //Starting page tests
        HomePage homePage = new HomePage(country,(ChromeDriver) driver);
        homePage.goToRegisterPage();
        linkText = country.equals("PL") ? "Rejestracja" : "Zaregistrovat se";
        // instantiate CinemaCityAsserts utility class
        CinemaCityAsserts ccAsserts = new CinemaCityAsserts(userEmail, linkText, country,
                (ChromeDriver) driver);
        //check if email form is correct
        ccAsserts.assertCorrectEmail();
        //check if link text matches country instance
        ccAsserts.assertRegisterLinkText();

        //Register page tests
        RegisterPage registerPage = new RegisterPage(userEmail,(ChromeDriver) driver);
        registerPage.registerUser();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-automation-id='signup-form-submit-button']")));
        //check if register button can be clicked
        ccAsserts.assertRegisterButtonEnabled();

        //Summary page tests
        SummaryPage summaryPage = new SummaryPage(userEmail,(ChromeDriver) driver);
        summaryPage.checkEmail();
        //check if paragraph with registered user email is displayed
        ccAsserts.assertEmailDisplayed();
    }
    public Path getPath(String uri) throws URISyntaxException {
        return Paths.get(CinemaCityTest.class.getResource(uri).toURI());
    }
}