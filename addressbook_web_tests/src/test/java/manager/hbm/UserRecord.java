package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="addressbook")
public class UserRecord {
    @Id
    public int id;
    public String firstname;
    public String middlename;
    public String lastname;
    public String address;
    public String home;
    public String work;
    public String mobile;
    public String email;
    public String email2;
    public String email3;
    public String photo;

    public UserRecord() {}
    public UserRecord(int id, String firstname, String middlename, String lastname, String address, String home, String work,
                            String mobile, String email, String email2, String email3, String photo) {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.address = address;
        this.home = home;
        this.work = work;
        this.mobile = mobile;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.photo = photo;
    }

}
