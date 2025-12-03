package model;

public record GrData(String id, String name, String header, String footer) {

    public GrData (){
        this("", "","","");
    }

    public GrData withName(String name) {
        return new GrData(this.id, name,this.header, this.footer);
    }

    public GrData withId(String id) {
        return new GrData(id, this.name,this.header, this.footer);
    }

    public GrData withHeader(String header) {
        return new GrData(this.id, this.name, header, this.footer);
    }

    public GrData withFooter(String footer) {
        return new GrData(this.id, this.name, this.header, footer);
    }
}