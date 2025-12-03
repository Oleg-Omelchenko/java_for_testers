package tests;

import model.GrData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class GroupCreateTest extends TestBase {

    public static List<GrData> groupCreator() {
        var result = new ArrayList<GrData>();
        for (var name : List.of("group name","")) {
            for (var header : List.of("group header","")) {
                for (var footer : List.of("group footer","")){
                    result.add(new GrData().withName(name).withHeader(header).withFooter(footer));
                }
            }
        }
        for (int i =0; i<2; i++ ) {
            result.add(new GrData()
                    .withName(randomString(i*3))
                    .withHeader(randomString(i*3))
                    .withFooter(randomString(i*3)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("groupCreator")
    public void canCreateMultipleGroup(GrData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Comparator<GrData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newGroups.get(newGroups.size()-1).id()).withHeader("").withFooter(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newGroups);
    }


    public static List<GrData> negativeGroupCreator() {
        var result = new ArrayList<GrData>(List.of(new GrData("", "group name'", "group header", "group footer")));
        return result;
    }


    @ParameterizedTest
    @MethodSource("negativeGroupCreator")
    public void canNotCreateGroup(GrData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Assertions.assertEquals(oldGroups, newGroups);
    }
}

