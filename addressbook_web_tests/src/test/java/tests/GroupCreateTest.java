package tests;

import model.GrData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;


public class GroupCreateTest extends TestBase {

    public static List<GrData> groupCreator() {
        var result = new ArrayList<GrData>();
        for (var name : List.of("group name","")) {
            for (var header : List.of("group header","")) {
                for (var footer : List.of("group footer","")){
                    result.add(new GrData(name,header,footer));
                }
            }
        }
        for (int i =0; i<5; i++ ) {
            result.add(new GrData(randomString(i*3),randomString(i*3),randomString(i*3)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("groupCreator")
    public void canCreateMultipleGroup(GrData group) {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(group);
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1,newGroupCount);
    }
    public static List<GrData> negativeGroupCreator() {
        var result = new ArrayList<GrData>(List.of(new GrData("group name'", "group header", "group footer")));
        return result;
    }


    @ParameterizedTest
    @MethodSource("negativeGroupCreator")
    public void canNotCreateGroup(GrData group) {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(group);
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount,newGroupCount);
    }
}

