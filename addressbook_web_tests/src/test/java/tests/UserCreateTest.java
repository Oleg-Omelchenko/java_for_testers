package tests;

import model.UserData;
import org.junit.jupiter.api.Test;

public class UserCreateTest extends TestBase {

    @Test
    public void userCreate() {
        app.users().openUserPage();
        app.users().createUser(new UserData("Oleg","Omelchenko","Lenina 20","+79998887766","test@test.com"));
    }

    @Test
    public void userCreateEmptyUser() {
        app.users().openUserPage();
        app.users().createUser(new UserData());
    }





}
