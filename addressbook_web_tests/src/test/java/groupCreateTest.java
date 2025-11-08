//import org.junit.jupiter.api.AfterEach;

import model.GrData;
import org.junit.jupiter.api.Test;

public class groupCreateTest extends TestBase {

    @Test
    public void canCreateGroup() {
        openGroupsPage();
        createGroup(new GrData("Name_2", "Header_2", "Footer_2"));
    }

    @Test
    public void createGroupEmptyName() {
        openGroupsPage();
        createGroup(new GrData());
    }

    @Test
    public void createGroupWithNameOnly() {
        openGroupsPage();
        var emptyGroup = new GrData();
        var groupWithName = emptyGroup.withName("some name");
        createGroup(groupWithName);
    }
}

