package manager;

import model.UserData;
import org.openqa.selenium.By;

public class UserHelper extends HelperBase{

    public UserHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openUserPage() {
        if (!manager.IsElementExist(By.name("submit"))) {
            manager.driver.findElement(By.xpath("//a[normalize-space()='add new']")).click();
        }
    }

    public void createUser(UserData user) {
        manager.driver.findElement(By.name("firstname")).click();
        manager.driver.findElement(By.name("firstname")).sendKeys(user.name());
        manager.driver.findElement(By.name("lastname")).click();
        manager.driver.findElement(By.name("lastname")).sendKeys(user.lastname());
        manager.driver.findElement(By.name("address")).click();
        manager.driver.findElement(By.name("address")).sendKeys(user.address());
        manager.driver.findElement(By.name("mobile")).click();
        manager.driver.findElement(By.name("mobile")).sendKeys(user.mobile());
        manager.driver.findElement(By.name("email")).click();
        manager.driver.findElement(By.name("email")).sendKeys(user.email());
        manager.driver.findElement(By.name("submit")).click();
        manager.driver.findElement(By.linkText("home")).click();
    }

    public void mainPage() {
        if (!manager.IsElementExist(By.name("Send e-Mail"))) {
            manager.driver.findElement(By.linkText("home")).click();
        }
    }

    public void delFirstUser() {
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.name("delete")).click();
    }

    public void createUserIfNotExist() {
        if (!manager.IsElementExist(By.name("selected[]"))) {
            manager.driver.findElement(By.xpath("//a[normalize-space()='add new']")).click();
            createUser(new UserData("Oleg", "Omelchenko", "Lenina 20", "+79998887766", "test@test.com"));
        }
    }
}
