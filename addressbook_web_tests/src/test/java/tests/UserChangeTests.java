package tests;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class UserChangeTests extends TestBase {
    @Test
    void canModifyUser() {
        app.users().createUserIfNotExist();
        if (app.jdbc().countUsersFromDB() == 0) {
            app.users().createUser(new UserData("", "Oleg", "Omelchenko", "Lenina 20", "+79998887766", "test@test.com",""));
        }
        var oldUsers = app.jdbc().getUserListFromDB();
        var rnd = new Random();
        var index = rnd.nextInt(oldUsers.size());
        var testData= new UserData().withName("James").withLastname("Bond");
        app.users().changeUser(oldUsers.get(index), testData);
        var newUsers = app.jdbc().getUserListFromDB();
        var expectedList = new ArrayList<>(oldUsers);
        expectedList.set(index, testData.withId(oldUsers.get(index).id()).withName("James").withLastname("Bond"));
        Comparator<UserData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newUsers.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newUsers, expectedList);
    }

}
