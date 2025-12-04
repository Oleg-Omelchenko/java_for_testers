package tests;

import common.CommonFunc;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserCreateTest extends TestBase {

    public static List<UserData> userCreator() {
        var result = new ArrayList<UserData>(List.of(
                new UserData("","Oleg","Omelchenko","Lenina 20","+79998887766","test@test.com","src/test/resources/images/avatar.png")));
        for (int i = 1; i < 5; i++) {
            result.add(new UserData("", CommonFunc.randomString(6), CommonFunc.randomString(8), randomAddress(), randomMobile(11), randomEmail(),"src/test/resources/images/avatar.png"));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("userCreator")
    public void canCreateMultipleUser(UserData user) {
        var oldUserList = app.users().getUserList();
        app.users().createUser(user);
        var newUserList = app.users().getUserList();
        Comparator<UserData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newUserList.sort(compareById);
        var expectedList = new ArrayList<>(oldUserList);
        expectedList.add(user.withId(newUserList.get(newUserList.size()-1).id()).withMobile("").withAddress("").withEmail("").withPhoto(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newUserList);
        }

    @Test
    void canCreateUser() {
        var adduser = new UserData()
                .withName(CommonFunc.randomString(6))
                .withLastname(CommonFunc.randomString(8))
                .withPhoto(randomFile("src/test/resources/images"));
        app.users().createUser(adduser);
    }
}
