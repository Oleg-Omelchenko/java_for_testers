package tests;

import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class UserCreateTest extends TestBase {

    public static List<UserData> userCreator() {
        var result = new ArrayList<UserData>(List.of(
                new UserData(),
                new UserData("Oleg","Omelchenko","Lenina 20","+79998887766","test@test.com")));
        for (int i = 0; i < 5; i++) {
            result.add(new UserData(randomString(6), randomString(8), randomAddress(), randomMobile(11), randomEmail()));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("userCreator")
    public void canCreateMultipleUser(UserData user) {
        int userCount = app.users().userCount();
        app.users().createUser(user);
        int newUserCount = app.users().userCount();
        Assertions.assertEquals(userCount + 1, newUserCount);
        }

}
