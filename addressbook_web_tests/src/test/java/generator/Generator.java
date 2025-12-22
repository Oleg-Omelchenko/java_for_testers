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
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*--type groups --output users.json --format json --count 5*/

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

    private Object generateData(Supplier<Object> dataSupplier) {
        return Stream.generate(dataSupplier).limit(count).collect(Collectors.toList());
    }

    private Object generateGroups() {
        return generateData(() -> new GrData()
                .withName(CommonFunc.randomString(6))
                .withHeader(CommonFunc.randomString(8))
                .withFooter(CommonFunc.randomString(8)));
    }

    private Object generateUsers() {
        return generateData(() -> new UserData()
                .withName(CommonFunc.randomString(6))
                .withMiddlename(CommonFunc.randomString(7))
                .withLastname(CommonFunc.randomString(8))
                .withAddress(CommonFunc.randomString(8) + " " + CommonFunc.randomString(3))
                .withHome(CommonFunc.randomPhone())
                .withWork(CommonFunc.randomPhone())
                .withMobile(CommonFunc.randomPhone())
                .withEmail(CommonFunc.randomString(7) + "@" + CommonFunc.randomString(5) + "." + CommonFunc.randomString(2))
                .withEmail2(CommonFunc.randomString(7) + "@" + CommonFunc.randomString(5) + "." + CommonFunc.randomString(2))
                .withEmail3(CommonFunc.randomString(7) + "@" + CommonFunc.randomString(5) + "." + CommonFunc.randomString(2)));
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
