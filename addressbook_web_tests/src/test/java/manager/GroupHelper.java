package manager;

import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GroupHelper {
    private final ApplicationManager manager;

    public void createGroup(GroupData group) {
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }

    public void modifyGroup(GroupData group, GroupData modifiedGroup) {
        openGroupsPage();
        selectGroup(group);
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPage();
    }

    public void deleteGroup(GroupData group) {
        openGroupsPage();
        selectGroup(group);
        removeSelectedGroup();
        returnToGroupsPage();
    }

    public void deleteAllGroups() {
        openGroupsPage();
        selectAllGroups();
        removeSelectedGroup();
    }

    public GroupHelper(ApplicationManager manager) {
        this.manager = manager;
    }

    public void openGroupsPage() {
        if (!manager.IsElementPresent(By.name("new"))) {
            manager.driver.findElement(By.linkText("groups")).click();
        }
    }

    private void submitGroupCreation() {
        manager.driver.findElement(By.name("submit")).click();
    }

    private void initGroupCreation() {
        manager.driver.findElement(By.name("new")).click();
    }

    private void removeSelectedGroup() {
        manager.driver.findElement(By.name("delete")).click();
    }

    public boolean isGroupPresent() {
        openGroupsPage();
        return manager.IsElementPresent(By.name("selected[]"));
    }

    public int getCount() {
        openGroupsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    private void selectAllGroups() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    private void selectAllGroupsFunctional() {
        manager.driver
                .findElements(By.name("selected[]"))
                .forEach(WebElement::click);
    }
    
    private void returnToGroupsPage() {
        manager.driver.findElement(By.linkText("group page")).click();
    }

    private void submitGroupModification() {
        manager.driver.findElement(By.name("update")).click();
    }

    private void fillGroupForm(GroupData group) {
        manager.driver.findElement(By.name("group_name")).click();
        manager.driver.findElement(By.name("group_name")).clear();
        manager.driver.findElement(By.name("group_name")).sendKeys(group.name());
        manager.driver.findElement(By.name("group_header")).click();
        manager.driver.findElement(By.name("group_header")).clear();
        manager.driver.findElement(By.name("group_header")).sendKeys(group.header());
        manager.driver.findElement(By.name("group_footer")).click();
        manager.driver.findElement(By.name("group_footer")).clear();
        manager.driver.findElement(By.name("group_footer")).sendKeys(group.footer());
    }

    private void initGroupModification() {
        manager.driver.findElement(By.name("edit")).click();
    }

    private void selectGroup(GroupData group) {
        manager.driver.findElement(By.cssSelector(String.format("input[value='%s']", group.id()))).click();
    }

    public List<GroupData> getList() {
        openGroupsPage();
        var spans = manager.driver.findElements(By.cssSelector("span.group"));
        return spans.stream()
                .map(span -> {
                    var name = span.getText();
                    var checkbox = span.findElement(By.name("selected[]"));
                    var id = checkbox.getAttribute("value");
                    return new GroupData().withId(id).withName(name);
                })
                .collect(Collectors.toList());
    }
}
