package tests;
import common.CommonFunc;
import model.GrData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class UserChangeTests extends TestBase {
    @Test
    void canModifyUser() {
        app.users().createUserIfNotExist();
        if (app.jdbc().countUsersFromDB() == 0) {
            app.users().createUser(new UserData("", "Oleg","", "Omelchenko", "Lenina 20","","", "+79998887766", "test@test.com", "","",""));
        }
        var oldUsers = app.jdbc().getUserListFromDB();
        var rnd = new Random();
        var index = rnd.nextInt(oldUsers.size());
        var testData = new UserData().withName("James").withLastname("Bond");
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
            app.users().createUser(new UserData("", "Oleg","", "Omelchenko", "Lenina 20","","", "+79998887766", "test@test.com", "","",""));
        }
        if (app.jdbc().countLinks() == app.jdbc().countGroupsFromDB() * app.jdbc().countUsersFromDB()) {
            app.groups().createGroup(new GrData("", CommonFunc.randomString(6), CommonFunc.randomString(8), CommonFunc.randomString(10)));
            app.users().mainPage();
        }
        var userAndGroupForAdd = app.jdbc().checkUsers();
        var oldList = app.jdbc().getAvailableGroupsForUser((UserData) userAndGroupForAdd.get(0));
        app.users().moveUserToGroup((UserData) userAndGroupForAdd.get(0), (String) userAndGroupForAdd.get(1));
        var newList = app.jdbc().getAvailableGroupsForUser((UserData) userAndGroupForAdd.get(0));
        var expectedList = new ArrayList<>(oldList);
        expectedList.remove(userAndGroupForAdd.get(1));
        Comparator<String> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1), Integer.parseInt(o2));
        };
        newList.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newList, expectedList);
            }

    @Test
    void removeUserFromGroup() {
        if (app.jdbc().countGroupsFromDB() == 0) {
            app.groups().createGroup(new GrData("", "Group_name_1", "Group_header_1", "Group_footer_1"));
        }
        if (app.jdbc().countUsersFromDB() == 0) {
            app.users().createUser(new UserData("", "Oleg","", "Omelchenko", "Lenina 20","","", "+79998887766", "test@test.com", "","",""));
        }
        List<UserData> userList = app.jdbc().getUserListFromDB();
        if (app.jdbc().countLinks() == 0) {
            var availableGroup = app.jdbc().getAvailableGroupsForUser(userList.getFirst());
                app.users().moveUserToGroup(userList.getFirst(),availableGroup.getFirst());
        }
        var links = app.jdbc().getGroupUserLinks();
        var rndLink = new Random();
        var indexLink = rndLink.nextInt(links.size());
        app.users().removeUserFromGroup(links.get(indexLink).get(1), links.get(indexLink).get(0));
        var newLinks = app.jdbc().getGroupUserLinks();
        var expectedLinks = new ArrayList<>(links);
        expectedLinks.remove(indexLink);
        Comparator<List<String>> pairComparator = (pair1, pair2) -> {
            int groupCompare = pair1.get(0).compareTo(pair2.get(0));
            if (groupCompare != 0) {
                return groupCompare;
            }
            return pair1.get(1).compareTo(pair2.get(1));
        };
        newLinks.sort(pairComparator);
        expectedLinks.sort(pairComparator);
        Assertions.assertEquals(newLinks, expectedLinks);
    }
}

