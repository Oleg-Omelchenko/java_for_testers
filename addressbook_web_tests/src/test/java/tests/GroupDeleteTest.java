package tests;

import model.GrData;
import org.junit.jupiter.api.Test;

public class GroupDeleteTest extends TestBase {

    @Test
    public void canDeleteGroup() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GrData("Group_name_1", "Group_header_1", "Group_footer_1"));
        }
        app.groups().deleteGroup();
    }

}
