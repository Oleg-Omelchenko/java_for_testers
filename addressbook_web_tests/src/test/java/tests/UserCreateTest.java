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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserCreateTest extends TestBase {

    public static List<UserData> userCreator() throws IOException {
        var result = new ArrayList<UserData>();
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("users.json"), new TypeReference<List<UserData>>() {});
        result.addAll(value);
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
