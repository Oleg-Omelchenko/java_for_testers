package tests;
import model.GrData;
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

    @Test
    void addUserToGroup() {
        if (app.jdbc().countGroupsFromDB() == 0) {
            app.groups().createGroup(new GrData("", "Group_name_1", "Group_header_1", "Group_footer_1"));
        }
        if (app.jdbc().countUsersFromDB() == 0) {
            app.users().createUser(new UserData("", "Oleg", "Omelchenko", "Lenina 20", "+79998887766", "test@test.com",""));
        }
        var userList = app.jdbc().getUserListFromDB();
        //var groupList = app.jdbc().getGroupListFromDB();
        var rndUser = new Random();
        var indexUser = rndUser.nextInt(userList.size());
        var availableGroup = app.jdbc().getAvailableGroupsForUser(userList.get(indexUser).id());
        var expectedList = new ArrayList<>(availableGroup);
        if (availableGroup.size() != 0) {
            var rndGroup = new Random();
            var indexGroup = rndGroup.nextInt(availableGroup.size());
            app.users().moveUserToGroup(userList.get(indexUser), availableGroup.get(indexGroup));
            expectedList.remove(indexGroup);
            var newAvailableGroup = app.jdbc().getAvailableGroupsForUser(userList.get(indexUser).id());
            Comparator<String> compareById = (o1, o2) -> {
                return Integer.compare(Integer.parseInt(o1), Integer.parseInt(o2));
            };
            newAvailableGroup.sort(compareById);
            expectedList.sort(compareById);
            Assertions.assertEquals(newAvailableGroup, expectedList);
        } else {
            throw new IllegalArgumentException("Пользователь с id "+userList.get(indexUser).id()+" состоит во всех группах");
        }
    }

}
