package model;

public record UserData(String id, String name, String middlename, String lastname, String address, String home, String work,
                       String mobile, String email, String email2, String email3, String photo) {

    public UserData(){

        this("","","","","","","","","","","","");
    }

    public UserData withId(String id) {
        return new UserData(id, this.name, this.middlename, this.lastname, this.address, this.home, this.work, this.mobile, this.email, this.email2, this.email3, this.photo);
    }

    public UserData withName(String name) {
        return new UserData(this.id, name, this.middlename, this.lastname, this.address, this.home, this.work, this.mobile, this.email, this.email2, this.email3, this.photo);
    }

    public UserData withMiddlename(String middlename) {
        return new UserData(this.id, this.name, middlename, this.lastname, this.address, this.home, this.work, this.mobile, this.email, this.email2, this.email3, this.photo);
    }

    public UserData withLastname(String lastname) {
        return new UserData(this.id, this.name, this.middlename, lastname, this.address, this.home, this.work, this.mobile, this.email, this.email2, this.email3, this.photo);
    }

    public UserData withAddress(String address) {
        return new UserData(this.id, this.name, this.middlename, this.lastname, address, this.home, this.work, this.mobile, this.email, this.email2, this.email3, this.photo);
    }

    public UserData withHome(String home) {
        return new UserData(this.id, this.name, this.middlename, this.lastname, address, home, this.work, this.mobile, this.email, this.email2, this.email3, this.photo);
    }

    public UserData withWork(String work) {
        return new UserData(this.id, this.name, this.middlename, this.lastname, address, this.home, work, this.mobile, this.email, this.email2, this.email3, this.photo);
    }

    public UserData withMobile(String mobile) {
        return new UserData(this.id, this.name, this.middlename, this.lastname, address, this.home, this.work, mobile, this.email, this.email2, this.email3, this.photo);
    }

    public UserData withEmail(String email) {
        return new UserData(this.id, this.name, this.middlename, this.lastname, this.address, this.home, this.work, this.mobile, email, this.email2, this.email3, this.photo);
    }

    public UserData withEmail2(String email2) {
        return new UserData(this.id, this.name, this.middlename, this.lastname, this.address, this.home, this.work, this.mobile, email, email2, this.email3, this.photo);
    }

    public UserData withEmail3(String email3) {
        return new UserData(this.id, this.name, this.middlename, this.lastname, this.address, this.home, this.work, this.mobile, email, this.email2, email3, this.photo);
    }

    public UserData withPhoto(String photo) {
        return new UserData(this.id, this.name, this.middlename, this.lastname, this.address, this.home, this.work, this.mobile, this.email, this.email2, this.email3, photo);
    }












}