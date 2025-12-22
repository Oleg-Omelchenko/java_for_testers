package tests;

import common.CommonFunc;
import model.GrData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;

public class GroupChangeTests extends TestBase {

    @Test
    void canModifyGroup() {
        app.groups().createGroupIfNotExist();
        var oldGroups = app.jdbc().getGroupListFromDB();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var testData= new GrData().withName(CommonFunc.randomString(8));
        app.groups().modifyGroup(oldGroups.get(index), testData);
        var newGroups = app.jdbc().getGroupListFromDB();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));
        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
}
}
