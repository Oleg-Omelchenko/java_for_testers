package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunc;
import java.io.IOException;

public class TempJames extends TestBase{
    @Test
    void canCreateUser() throws IOException {
        app.jamesCli().addBox(String.format("%s@localhost", CommonFunc.randomString(6)), "password");
    }





}
