package manager;

import model.ContactData;
import model.GroupData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase{
    public JdbcHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<GroupData> getGroupList() {
        var groups = new ArrayList<GroupData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list");
     )
        {
            while (result.next()) {
                groups.add(new GroupData()
                        .withId(result.getString("group_id"))
                        .withName(result.getString("group_name"))
                        .withHeader(result.getString("group_header"))
                        .withFooter(result.getString("group_footer")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    public void checkConsistency() {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook",
                "root",
                "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT * FROM address_in_groups ag left join addressbook ad on ag.id=ad.id WHERE ad.id is null;"))
        {  if (result.next()) {
            throw new IllegalStateException("DB is corrupted");
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteContactFromGroup(ContactData contact, GroupData group) {

        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             )
        {
            statement.execute(String.format("DELETE FROM address_in_groups WHERE group_id = '%s' AND id = '%s'", group.id(), contact.id()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkLink(GroupData group, ContactData contact) {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery(String.format(
                     "SELECT * FROM address_in_groups WHERE id = '%s' AND group_id = '%s'", contact.id(), group.id()))) {
            return result.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
