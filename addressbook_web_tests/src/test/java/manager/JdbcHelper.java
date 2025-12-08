package manager;

import model.GrData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase {

    public JdbcHelper(ApplicationManager manager) {

        super(manager);
    }

    public List<GrData> getGroupList() {
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

    public int countGroup() {
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
}
