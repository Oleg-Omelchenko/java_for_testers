package tests;

import common.CommonFunc;
import model.GrData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class UserCreateTest extends TestBase {

    public static List<UserData> userCreator() throws IOException {
        var result = new ArrayList<UserData>();
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("users.json"), new TypeReference<List<UserData>>() {});
        result.addAll(value);
        return result;
    }


    public static Stream<UserData> randomUser() {
        Supplier<UserData> rndUser = () -> new UserData()
                .withName(CommonFunc.randomString(6))
                .withMiddlename(CommonFunc.randomString(7))
                .withLastname(CommonFunc.randomString(8))
                .withAddress(CommonFunc.randomString(8) + " " + CommonFunc.randomString(3))
                .withHome(CommonFunc.randomPhone())
                .withWork(CommonFunc.randomPhone())
                .withMobile(CommonFunc.randomPhone())
                .withEmail(CommonFunc.randomString(7) + "@" + CommonFunc.randomString(5) + "." + CommonFunc.randomString(2))
                .withEmail2(CommonFunc.randomString(7) + "@" + CommonFunc.randomString(5) + "." + CommonFunc.randomString(2))
                .withEmail3(CommonFunc.randomString(7) + "@" + CommonFunc.randomString(5) + "." + CommonFunc.randomString(2));
        return Stream.generate(rndUser).limit(5);
    }


    @ParameterizedTest
    @MethodSource("randomUser")
    public void canCreateMultipleUser(UserData user) {
        var oldUserList = app.hbm().getUserList();
        app.users().createUser(user);
        var newUserList = app.hbm().getUserList();
        Comparator<UserData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newUserList.sort(compareById);
        var expectedList = new ArrayList<>(oldUserList);
        expectedList.add(user.withId(newUserList.get(newUserList.size()-1).id()));
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
