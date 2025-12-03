package model;

public record UserData(String id, String name, String lastname, String address, String mobile, String email) {

    public UserData(){

        this("","","","","","");
    }

    public UserData withId(String id) {
        return new UserData(id, this.name, lastname, this.address, this.mobile, this.email);
    }

    public UserData withName(String name) {
        return new UserData(this.id, name,this.lastname, this.address, this.mobile, this.email);
    }

    public UserData withLastname(String lastname) {
        return new UserData(this.id,this.name, lastname, this.address, this.mobile, this.email);
    }

    public UserData withAddress(String address) {
        return new UserData(this.id,this.name, this.lastname, address, this.mobile, this.email);
    }

    public UserData withMobile(String mobile) {
        return new UserData(this.id,this.name, this.lastname, this.address, mobile, this.email);
    }

    public UserData withEmail(String email) {
        return new UserData(this.id,this.name, this.lastname, this.address, this.mobile, email);
    }

}