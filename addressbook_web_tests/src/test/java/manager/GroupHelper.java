package manager;

import model.GrData;
import org.openqa.selenium.By;

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

    public void deleteGroup() {
        openGroupsPage();
        selectGroup();
        delSelectedGroup();
        returnToGroupsPage();
    }

    public void modifyGroup(GrData modifiedGroup) {
        openGroupsPage();
        selectGroup();
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

    private void delSelectedGroup() {
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

    private void selectGroup() {
        click(By.name("selected[]"));
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
}
