package manager;

import model.UserData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        attach(By.name("photo"), user.photo());
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
            createUser(new UserData("", "Oleg", "Omelchenko", "Lenina 20", "+79998887766", "test@test.com",""));
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
            var id = row.findElement(By.name("selected[]")).getAttribute("value");
            var name = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
            var lastname = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
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

    public void changeUser(UserData user, UserData changedUser) {
        mainPage();
        startEditUser(user);
        type(By.name("firstname"), changedUser.name());
        type(By.name("lastname"), changedUser.lastname());
        //attach(By.name("photo"), changedUser.photo());
        click(By.name("update"));
        mainPage();
    }

    private void startEditUser(UserData user) {
        manager.driver.findElement(By.xpath(String.format("//a[@href='edit.php?id=%s']", user.id()))).click();
    }
}

