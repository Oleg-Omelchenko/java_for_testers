import model.GrData;
import org.junit.jupiter.api.Test;

public class groupDeleteTest extends TestBase{

    @Test
    public void canDeleteGroup() {
        openGroupsPage();
        if (!isGroupPresent()) {
            createGroup(new GrData("Group_name_1", "Group_header_1", "Group_footer_1"));
        }
        deleteGroup();
    }

}
