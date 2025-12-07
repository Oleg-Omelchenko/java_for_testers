package tests;

import common.CommonFunc;
import model.GrData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class GroupCreateTest extends TestBase {

    public static List<GrData> groupCreator() throws IOException {
        var result = new ArrayList<GrData>();

/*Читаем файл построчно, сохраняем в переменную*/
/*
        var json = "";
        try (var reader = new FileReader("groups.json");
        var breader = new BufferedReader(reader)) {
            var line = breader.readLine();
            while (line != null) {
                json = json + line;
                line = breader.readLine();
            }
        }
*/
        //var json = Files.readString(Paths.get("groups.json")); // читаем файл в переменную целиком
        ObjectMapper mapper = new ObjectMapper();
        //var value = mapper.readValue(json, new TypeReference<List<GrData>>() {});
        var value = mapper.readValue(new File("groups.json"), new TypeReference<List<GrData>>() {});
        result.addAll(value);
        return result;
    }

    public static List<GrData> singleRandomGroup() {
        return List.of(new GrData()
                .withName(CommonFunc.randomString(6))
                .withHeader(CommonFunc.randomString(9))
                .withFooter(CommonFunc.randomString(12)));
    }



    @ParameterizedTest
    @MethodSource("singleRandomGroup")
    public void canCreateGroup(GrData group) {
        var oldGroups = app.jdbc().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.jdbc().getGroupList();
        Comparator<GrData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var maxId = newGroups.get(newGroups.size()-1).id();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newGroups);
    }

    @Test
    void CompareUiAndDatabase() {
        var newGroups = app.jdbc().getGroupList();
        var changeNewGroups = new ArrayList<GrData>();
        Comparator<GrData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        for (var line : newGroups) {
            //changeNewGroups.add(line.withHeader("").withFooter(""));
            changeNewGroups.add(new GrData().withId(line.id()).withName(line.name()));
        }
        changeNewGroups.sort(compareById);
        var newUiGroups = app.groups().getList();
        newUiGroups.sort(compareById);
        Assertions.assertEquals(changeNewGroups, newUiGroups);
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

