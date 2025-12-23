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
        var phones = app.users().getPhonesFromMain(user);
        var expected = Stream.of(user.home(), user.mobile(), user.work())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(phones, expected);
    }

    @Test
    void testEmailsFromSite() {
        var users = app.users().getUserList();
        var rnd = new Random().nextInt(users.size());
        var user = users.get(rnd);
        var emails = app.users().getEmailsFromMain(user);
        var expected = app.users().getEmailsFromModify(user);
        Assertions.assertEquals(emails, expected);
    }

    @Test
    void testPhonesFromSite() {
        var users = app.users().getUserList();
        var rnd = new Random().nextInt(users.size());
        var user = users.get(rnd);
        var phones = app.users().getPhonesFromMain(user);
        var expected = app.users().getPhonesFromModify(user);
        Assertions.assertEquals(phones, expected);
    }

    @Test
    void testAddressFromSite() {
        var users = app.users().getUserList();
        var rnd = new Random().nextInt(users.size());
        var user = users.get(rnd);
        var address = app.users().getAddressFromMain(user);
        var expected = app.users().getAddressFromModify(user);
        Assertions.assertEquals(address, expected);
    }


}
