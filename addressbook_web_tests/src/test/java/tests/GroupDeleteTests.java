package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GroupDeleteTests extends TestBase {


    @Test
    public void testDeleteGroup() {

        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("", "name", "1", "2"));
        }

        var oldGroups = app.groups().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().deleteGroup(oldGroups.get(index));
        var newGroups = app.groups().getList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups.size(), oldGroups.size() - 1);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @Test
    public void testDeleteAllGroups() {

        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "name", "1", "2"));
        }


        app.groups().deleteAllGroups();
        Assertions.assertEquals(0, app.groups().getCount());
    }
}
