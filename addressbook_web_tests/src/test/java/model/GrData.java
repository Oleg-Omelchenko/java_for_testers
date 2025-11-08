package model;

public record GrData(String name, String header, String footer) {

    public GrData (){
        this("","","");
    }

    public GrData withName(String name) {
        return new GrData(name,this.header, this.footer);
    }

    public GrData withHeader(String header) {
        return new GrData(this.name, header, this.footer);
    }

    public GrData withFooter(String footer) {
        return new GrData(this.name, this.header, footer);
    }
}