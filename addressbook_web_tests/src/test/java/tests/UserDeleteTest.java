package tests;

import org.junit.jupiter.api.Test;

public class UserDeleteTest extends TestBase {

    @Test
    public void delUser() {
        app.mainPage();
        app.createUserIfNotExist();
        app.delFirstUser();

    }

}
