package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunc;

import java.io.IOException;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegistrationUser() throws IOException  {
        var username = CommonFunc.randomString(6);
        var email = String.format("%s@localhost", username);
        app.jamesCli().addBox(email, "password");
        app.http().createUserInBrowser(username, email); // заполняем форму и отправляем (браузер)
        var url = app.mail().getUrl(username);// извелекаем ссылку из письма
        app.http().useLink(url);// проходим по ссылке из письма и завершаем регистрацию (браузер)
        app.http().login(username, "Oleg_test");
        Assertions.assertTrue(app.http().isloggedIn());// проверяем, что созданный юзер может залогиниться (httpSessionHelper)
    }
}
