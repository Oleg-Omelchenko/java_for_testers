package tests;

import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class UserDeleteTest extends TestBase {

    @Test
    public void delUser() {
        app.users().mainPage();
        app.users().createUserIfNotExist();
        var oldUsers = app.jdbc().getUserListFromDB();
        var rnd = new Random();
        var index = rnd.nextInt(oldUsers.size());
        app.users().deleteUser(oldUsers.get(index));
        var newUsers = app.jdbc().getUserListFromDB();
        Comparator<UserData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newUsers.sort(compareById);
        var expectedList = new ArrayList<>(oldUsers);
        expectedList.remove(index);
        expectedList.sort(compareById);
        Assertions.assertEquals(newUsers, expectedList);




    }

}
