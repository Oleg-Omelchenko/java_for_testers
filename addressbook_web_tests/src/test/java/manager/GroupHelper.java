package manager;

import model.GrData;
import model.UserData;
import org.openqa.selenium.By;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createGroup(GrData group) {
        openGroupsPage();
        startGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }

    public void deleteGroup(GrData group) {
        openGroupsPage();
        selectGroup(group);
        delSelectedGroups();
        returnToGroupsPage();
    }

    public void modifyGroup(GrData group, GrData modifiedGroup) {
        openGroupsPage();
        selectGroup(group);
        startGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPage();
    }

    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    private void startGroupCreation() {
        click(By.name("new"));
    }

    private void delSelectedGroups() {
        click(By.name("delete"));
    }

    private void returnToGroupsPage() {
        click(By.linkText("groups"));
    }

    private void submitGroupModification() {
        click(By.name("update"));
    }

    private void fillGroupForm(GrData group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());
    }

    private void startGroupModification() {
        click(By.name("edit"));
    }

    private void selectGroup(GrData group) {
        click(By.cssSelector(String.format("input[value='%s']", group.id())));
    }

    public void openGroupsPage() {
        if (!manager.IsElementExist(By.name("new"))) {
            returnToGroupsPage();
        }
    }

    public int getCount() {
        openGroupsPage();
        return  manager.driver.findElements(By.name("selected[]")).size();
    }

    public void deleteAllGroups() {
        openGroupsPage();
        selectAllGroups();
        delSelectedGroups();
    }

    private void selectAllGroups() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public void createGroupIfNotExist() {
        openGroupsPage();
        if (!manager.IsElementExist(By.cssSelector("span.group"))) {
            createGroup(new GrData("", "Group_name_1", "Group_header_1", "Group_footer_1"));
        }
    }


    public List<GrData> getList() {
        openGroupsPage();
        var groups = new ArrayList<GrData>();
        var spans = manager.driver.findElements(By.cssSelector("span.group"));
        for (var span : spans) {
            var name = span.getText();
            var checkbox = span.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            groups.add(new GrData().withId(id).withName(name));
        }
        return groups;
    }
}
