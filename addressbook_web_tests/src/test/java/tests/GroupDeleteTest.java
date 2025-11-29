package tests;

import model.GrData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupDeleteTest extends TestBase {

    @Test
    public void canDeleteGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GrData("Group_name_1", "Group_header_1", "Group_footer_1"));
        }
        int groupCount = app.groups().getCount();
        app.groups().deleteGroup();
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount - 1,newGroupCount);
    }
    @Test
    void canDeleteAllGroups() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GrData("Group_name_1", "Group_header_1", "Group_footer_1"));
        }
        app.groups().deleteAllGroups();
        Assertions.assertEquals(0, app.groups().getCount());
    }
}
