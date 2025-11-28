package tests;

import model.GrData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupCreateTest extends TestBase {

    @Test
    public void canCreateGroup() {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(new GrData("Name_2", "Header_2", "Footer_2"));
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1,newGroupCount);
    }

    @Test
    public void createGroupEmptyName() {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(new GrData());
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1,newGroupCount);
    }

    @Test
    public void createGroupWithNameOnly() {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(new GrData().withName("some name"));
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1,newGroupCount);
    }
}

