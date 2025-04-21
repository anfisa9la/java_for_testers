package tests;

import org.junit.jupiter.api.Test;


public class GroupDeleteTests extends TestBase {


    @Test
    public void testDeleteGroup() {
        app.groups().deleteGroup();
    }
}
