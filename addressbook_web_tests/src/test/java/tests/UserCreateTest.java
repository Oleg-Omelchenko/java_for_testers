package tests;

import model.UserData;
import org.junit.jupiter.api.Test;

public class UserCreateTest extends TestBase {

    @Test
    public void userCreate() {
        app.openUserPage();
        app.createUser(new UserData("Oleg","Omelchenko","Lenina 20","+79998887766","test@test.com"));
    }

    @Test
    public void userCreateEmptyUser() {
        app.openUserPage();
        app.createUser(new UserData());
    }





}
