package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserInfoTests extends TestBase {

    @Test
    void testPhones() {
        var users = app.hbm().getUserList();
        var rnd = new Random().nextInt(users.size());
        var user = users.get(rnd);
        var phones = app.users().getPhones(user);
        var expected = Stream.of(user.home(), user.mobile(), user.work())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(phones, expected);
    }

    @Test
    void testEmails() {
        var users = app.hbm().getUserList();
        var rnd = new Random().nextInt(users.size());
        var user = users.get(rnd);
        var emails = app.users().getEmails(user);
        var expected = Stream.of(user.email(), user.email2(), user.email3())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(emails, expected);
    }



}
