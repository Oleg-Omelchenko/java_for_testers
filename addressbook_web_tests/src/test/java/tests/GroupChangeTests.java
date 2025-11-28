package tests;

import model.GrData;
import org.junit.jupiter.api.Test;

public class GroupChangeTests extends TestBase {

    @Test
    void canModifyGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GrData("Group_name_1", "Group_header_1", "Group_footer_1"));
        }
        app.groups().modifyGroup(new GrData().withName("Modified name"));
}
}
