package manager;

import model.UserData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class UserHelper extends HelperBase{

    public UserHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createUser(UserData user) {
        openUserPage();
        click(By.name("firstname"));
        type(By.name("firstname"), user.name());
        click(By.name("lastname"));
        type(By.name("lastname"), user.lastname());
        click(By.name("address"));
        type(By.name("address"), user.address());
        click(By.name("mobile"));
        type(By.name("mobile"), user.mobile());
        click(By.name("email"));
        type(By.name("email"), user.email());
        click(By.name("submit"));
        click(By.linkText("home"));
    }

    public void delFirstUser() {
        click(By.name("selected[]"));
        click(By.name("delete"));
    }

    public void createUserIfNotExist() {
        if (!manager.IsElementExist(By.name("selected[]"))) {
            click(By.xpath("//a[normalize-space()='add new']"));
            createUser(new UserData("", "Oleg", "Omelchenko", "Lenina 20", "+79998887766", "test@test.com"));
        }
    }

    public void openUserPage() {
        if (!manager.IsElementExist(By.name("submit"))) {
            click(By.xpath("//a[normalize-space()='add new']"));
        }
    }

    public int userCount() {
        mainPage();
        return  manager.driver.findElements(By.name("selected[]")).size();
    }


    public void mainPage() {
        if (!manager.IsElementExist(By.name("Send e-Mail"))) {
            click(By.linkText("home"));
        }
    }

    public List<UserData> getUserList() {
        var users = new ArrayList<UserData>();
        var rows = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (var row : rows) {
            var content = row.findElement(By.name("selected[]"));
            var id = content.getAttribute("value");
            var rawname = row.findElement(By.cssSelector("td:nth-child(3)"));
            var name =rawname.getText();
            var rawlastname = row.findElement(By.cssSelector("td:nth-child(2)"));
            var lastname =rawlastname.getText();
            users.add(new UserData().withId(id).withName(name).withLastname(lastname));
        }
        return users;
    }

    private void selectUser(UserData user) {
        click(By.cssSelector(String.format("input[value='%s']", user.id())));
    }

    public void deleteUser(UserData user) {
        mainPage();
        selectUser(user);
        click(By.name("delete"));
        mainPage();
    }
}

