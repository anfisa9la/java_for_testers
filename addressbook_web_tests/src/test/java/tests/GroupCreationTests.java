package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {


    @Test
    public void testCreateGroup() {
        TestBase.app.groups().createGroup(new GroupData("1111", "2222", "3333"));
    }

    @Test
    public void testCreateGroupWithEmptyFields() {
        app.groups().createGroup(new GroupData());
    }

    @Test
    public void testCreateGroupWithNameOnly() {
        app.groups().createGroup(new GroupData().withName("my group"));
    }
}
