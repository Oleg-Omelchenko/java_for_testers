package tests;

import model.GrData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GroupChangeTests extends TestBase {

    @Test
    void canModifyGroup() {
        app.groups().createGroupIfNotExist();
        var oldGroups = app.jdbc().getGroupListFromDB();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var testData= new GrData().withName("Modified name");
        app.groups().modifyGroup(oldGroups.get(index), testData);
        var newGroups = app.jdbc().getGroupListFromDB();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));
        Comparator<GrData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);
}
}
