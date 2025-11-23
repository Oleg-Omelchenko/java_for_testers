package tests;

import model.GrData;
import org.junit.jupiter.api.Test;

public class GroupCreateTest extends TestBase {

    @Test
    public void canCreateGroup() {
        app.groups().createGroup(new GrData("Name_2", "Header_2", "Footer_2"));
    }

    @Test
    public void createGroupEmptyName() {
        app.groups().createGroup(new GrData());
    }

    @Test
    public void createGroupWithNameOnly() {
        app.groups().createGroup(new GrData().withName("some name"));
    }
}

