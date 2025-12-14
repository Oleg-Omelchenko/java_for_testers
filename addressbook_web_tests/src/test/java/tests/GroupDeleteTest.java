package tests;

import model.GrData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class GroupDeleteTest extends TestBase {

    @Test
    public void canDeleteGroup() {
        //app.groups().createGroupIfNotExist();
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GrData("", "Group_name_1", "Group_header_1", "Group_footer_1"));
        }
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().deleteGroup(oldGroups.get(index));
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);
    }
    @Test
    void canDeleteAllGroups() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GrData("", "Group_name_1", "Group_header_1", "Group_footer_1"));
        }
        app.groups().deleteAllGroups();
        Assertions.assertEquals(0, app.hbm().getGroupCount());
    }
}
