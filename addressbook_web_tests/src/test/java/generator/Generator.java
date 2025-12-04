package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import common.CommonFunc;
import model.GrData;

import java.util.ArrayList;

public class Generator {
    @Parameter(names={"--type", "-t"})
    String type;
    @Parameter(names={"--output", "-o"})
    String output;
    @Parameter(names={"--format", "-f"})
    String format;
    @Parameter(names={"--count", "-c"})
    int count;

    public static void main(String[] args) {
        var generator = new Generator();
        JCommander.newBuilder().addObject(generator).build().parse(args);
        generator.run();

    }

    private void run() {
        var data = generate();
        save(data);
    }



    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        } else if ("users".equals(type)) {
            return generateUsers();
        } else {
            throw new IllegalArgumentException("Неизвестный тип данных" + type);
        }
    }

    private Object generateGroups() {
        var result = new ArrayList<GrData>();
        for (int i =0; i<count; i++ ) {
            result.add(new GrData()
                    .withName(CommonFunc.randomString(i*3))
                    .withHeader(CommonFunc.randomString(i*3))
                    .withFooter(CommonFunc.randomString(i*3)));
        }
        return result;
    }

    private Object generateUsers() {
        return null;
    }

    private void save(Object data) {
    }
}
