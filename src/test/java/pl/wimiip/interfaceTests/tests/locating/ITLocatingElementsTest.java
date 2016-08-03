package pl.wimiip.interfaceTests.tests.locating;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.wimiip.TestApp;
import pl.wimiip.interfaceTests.config.ITConfigurationForChromeBrowser;
import pl.wimiip.interfaceTests.tests.CommonMethods;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by nishi on 2016-06-19.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApp.class)
@WebAppConfiguration
public class ITLocatingElementsTest extends ITConfigurationForChromeBrowser {

    private CommonMethods commonMethods;

    @Rule
    public TestName name = new TestName();

    @Before
    public void setUp() {
        System.out.println("Starting " + name.getMethodName());
        commonMethods = new CommonMethods();
        commonMethods.logInAndMoveToSeleniumPage();
    }

    @Test
    public void inputFieldsOfLoginForm_LocatingElementById_NothingResultsOnlyAsserts() {
        assertTrue(wait.until(ExpectedConditions.urlContains("locating")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("1.2"))).click();
        fillInForm("Maciek", "test");
        clearForm();
    }

    @Test
    public void previousAndNextButtons_LocatingElementByName_NothingResultsOnlyAsserts() {
        assertTrue(wait.until(ExpectedConditions.urlContains("locating")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("1.3"))).click();
        WebElement previousButton = driver.findElement(By.name("previous"));
        WebElement nextButton = driver.findElement(By.name("next"));
        assertNotNull(previousButton);
        assertNotNull(nextButton);
        wait.until(ExpectedConditions.invisibilityOfElementWithText(By.name("text"), "Previous"));
        wait.until(ExpectedConditions.invisibilityOfElementWithText(By.name("text"), "Next"));

        previousButton.click();
        assertTrue(driver.findElement(By.name("text")).isDisplayed());

        String text = driver.findElement(By.name("text")).getText();
        assertEquals("Previous", text);

        nextButton.click();
        text = driver.findElement(By.name("text")).getText();
        assertEquals("Next", text);
    }

    @Test
    public void htmlHeadingElements_LocatingElementByName_NothingResultsOnlyAsserts() {
        assertTrue(wait.until(ExpectedConditions.urlContains("locating")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("1.4"))).click();
        WebElement h1 = driver.findElement(By.className("h1"));
        assertEquals("Bootstrap heading (36px)", h1.getText());
        assertEquals("h1", h1.getAttribute("class"));
        assertEquals("h1", h1.getTagName());
        assertTrue(h1.isDisplayed());
        String h4 = driver.findElement(By.className("h4")).getText();
        assertNotSame(h1, h4);
        assertNotEquals("Bootstrap heading (18px)", h1.getText());
        assertEquals("Bootstrap heading (18px)", h4);
    }

    @After
    public void tearDown() {
        System.out.println("Cleaning after " + name.getMethodName());
    }


    /**
     * Method which fills in a form
     * @param login
     * @param password
     */
    private void fillInForm(String login, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login"))).sendKeys(login);
        driver.findElement(By.id("password")).sendKeys(password);
        assertNotNull(driver.findElement(By.id("clickMeButton")));
        driver.findElement(By.id("clickMeButton")).click();
    }

    /**
     * Method clearing fields form. It does not accept any arguments.
     */
    private void clearForm() {
        if(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login"))).isDisplayed()
            && wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login"))).getText() != null) {
            driver.findElement(By.id("login")).clear();
            assertEquals("", driver.findElement(By.id("login")).getText());
        }
        if(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).isDisplayed()
                && wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).getText() != null)
            driver.findElement(By.id("password")).clear();
            assertEquals("", driver.findElement(By.id("login")).getText());
        }
}