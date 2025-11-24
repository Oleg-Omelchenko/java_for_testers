package tests;

import org.junit.jupiter.api.Test;

public class UserDeleteTest extends TestBase {

    @Test
    public void delUser() {
        app.users().mainPage();
        app.users().createUserIfNotExist();
        app.users().delFirstUser();

    }

}
