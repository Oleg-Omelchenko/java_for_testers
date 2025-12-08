package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import common.CommonFunc;
import model.GrData;
import model.UserData;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*--type groups --output groups.json --format json --count 5*/

public class Generator {
    @Parameter(names={"--type", "-t"})
    String type;
    @Parameter(names={"--output", "-o"})
    String output;
    @Parameter(names={"--format", "-f"})
    String format;
    @Parameter(names={"--count", "-c"})
    int count;

    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder().addObject(generator).build().parse(args);
        generator.run();

    }

    private void run() throws IOException {
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
        var result = new ArrayList<UserData>();
        for (int i =0; i<count; i++ ) {
            result.add(new UserData()
                    .withName(CommonFunc.randomString(6))
                    .withLastname(CommonFunc.randomString(8)));
        }
        return result;
    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = JsonMapper.builder().enable(SerializationFeature.INDENT_OUTPUT).build();
            var json = mapper.writeValueAsString(data);
            try (var writer = new FileWriter(output)) {
                writer.write(json);
            }
        } else if ("yaml".equals(format)) {
            var mapper = new YAMLMapper();
            //mapper.writeValue(new File(output), data);
            var yaml = mapper.writeValueAsString(data);
            try (var writer = new FileWriter(output)) {
                writer.write(yaml);
            }
        } else if ("xml".equals(format)) {
            var mapper = new XmlMapper();
            mapper.writeValue(new File(output), data);
        } else {
        throw new IllegalArgumentException("Неизвестный формат данных " + format);
        }
    }
}
