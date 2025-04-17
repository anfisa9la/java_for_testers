import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {


    @Test
    public void testCreateGroup() {
        openGroupsPage();
        createGroup(new GroupData("1111", "2222", "3333"));
    }

    @Test
    public void testCreateGroupWithEmptyFields() {

        openGroupsPage();
        createGroup(new GroupData());
    }

    @Test
    public void testCreateGroupWithNameOnly() {

        openGroupsPage();
        createGroup(new GroupData().withName("my group"));
    }
}
