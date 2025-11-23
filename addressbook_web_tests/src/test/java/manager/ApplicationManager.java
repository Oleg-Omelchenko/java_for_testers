package manager;

import model.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ApplicationManager {
    protected WebDriver driver;
    private LoginHelper session;
    private GroupHelper groups;

    public void init() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook");
            session().login("admin", "secret");
        }
    }

    public LoginHelper session() {
        if (session == null) {
            session = new LoginHelper(this);
        }
        return session;
    }
    public GroupHelper groups() {
        if (groups == null) {
            groups = new GroupHelper(this);
        }
        return groups;
    }

    public boolean IsElementExist(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public void openUserPage() {
        if (!IsElementExist(By.name("submit"))) {
            driver.findElement(By.xpath("//a[normalize-space()='add new']")).click();
        }
    }

    public void createUser(UserData user) {
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).sendKeys(user.name());
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).sendKeys(user.lastname());
        driver.findElement(By.name("address")).click();
        driver.findElement(By.name("address")).sendKeys(user.address());
        driver.findElement(By.name("mobile")).click();
        driver.findElement(By.name("mobile")).sendKeys(user.mobile());
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).sendKeys(user.email());
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("home")).click();
    }

    public void delFirstUser() {
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.name("delete")).click();
    }

    public void mainPage() {
        if (!IsElementExist(By.name("Send e-Mail"))) {
            driver.findElement(By.linkText("home")).click();
        }
    }

    public void createUserIfNotExist() {
        if (!IsElementExist(By.name("selected[]"))) {
            driver.findElement(By.xpath("//a[normalize-space()='add new']")).click();
            createUser(new UserData("Oleg", "Omelchenko", "Lenina 20", "+79998887766", "test@test.com"));
        }
    }
}
