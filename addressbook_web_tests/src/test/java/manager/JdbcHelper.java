package manager;

import model.GrData;
import model.UserData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JdbcHelper extends HelperBase {

    public JdbcHelper(ApplicationManager manager) {

        super(manager);
    }

    public List<GrData> getGroupListFromDB() {
        var groups = new ArrayList<GrData>();
        try (var connect = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root","");
             var statement = connect.createStatement();
             var  result = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list"))
        {
            while (result.next()) {
                groups.add(new GrData().withId(result.getString("group_id"))
                        .withName(result.getString("group_name"))
                        .withHeader(result.getString("group_header"))
                        .withFooter(result.getString("group_footer")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    public int countGroupsFromDB() {
        int count;
        try (var connect = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = connect.createStatement();
             var result = statement.executeQuery("SELECT count(*) FROM group_list"))
        {
            if (result.next()) {
                count = result.getInt(1);
            } else {
                count = 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public List<UserData> getUserListFromDB() {
        var users = new ArrayList<UserData>();
        try (var connect = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root","");
             var statement = connect.createStatement();
             var  result = statement.executeQuery("SELECT id, firstname, lastname, address, mobile, email FROM addressbook"))
        {
            while (result.next()) {
                users.add(new UserData().withId(result.getString("id"))
                        .withName(result.getString("firstname"))
                        .withLastname(result.getString("lastname"))
                        .withAddress(result.getString("address"))
                        .withMobile(result.getString("mobile"))
                        .withEmail(result.getString("email")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public List<String> getAvailableGroupsForUser(UserData user) {
        List<String> userGroups = new ArrayList<>();
        List<String> availableGroups = getGroupListFromDB().stream().map(GrData::id).collect(Collectors.toList());
        try (var connect = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = connect.createStatement();
             var result = statement.executeQuery(String.format("SELECT group_id FROM address_in_groups WHERE id=%s", user.id()))) {
            while (result.next()) {
                userGroups.add(result.getString("group_id"));
            }
            availableGroups.removeAll(userGroups);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return availableGroups;
    }

    public int countUsersFromDB() {
        int count;
        try (var connect = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = connect.createStatement();
             var result = statement.executeQuery("SELECT count(*) FROM addressbook"))
        {
            if (result.next()) {
                count = result.getInt(1);
            } else {
                count = 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public int countLinks() {
        int count;
        try (var connect = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = connect.createStatement();
             var result = statement.executeQuery("SELECT count(*) FROM address_in_groups"))
        {
            if (result.next()) {
                count = result.getInt(1);
            } else {
                count = 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public List<List<String>> getGroupUserLinks() {
        List<List<String>> links = new ArrayList<>();
        try (var connect = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root","");
             var statement = connect.createStatement();
             var  result = statement.executeQuery("SELECT group_id, id FROM address_in_groups"))
        {
            while (result.next()) {
                List<String> row = new ArrayList<>();
                row.add(result.getString("group_id"));
                row.add(result.getString("id"));
                links.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return links;
    }

    public ArrayList<Object> checkUsers() {
        var userList = getUserListFromDB();
        var pair = new ArrayList<>();
        for (UserData user : userList) {
            var availableGroup = getAvailableGroupsForUser(user);
            if (!availableGroup.isEmpty()) {
                pair.add(user);
                pair.add(availableGroup.getFirst());
            }
        }
        return pair;
    }
}
