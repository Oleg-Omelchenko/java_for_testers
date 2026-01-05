package ru.stqa.mantis.manager;

import okhttp3.*;
import org.openqa.selenium.By;

import java.io.IOException;
import java.net.CookieManager;
import java.time.Duration;
import java.util.regex.Pattern;


public class HttpSessionHelper  extends HelperBase {


    OkHttpClient client;

    public HttpSessionHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
    }

    public void login(String username, String password) {
        RequestBody formBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .url(String.format("%s/login.php", manager.property("web.baseUrl")))
                .post(formBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isloggedIn() {
        Request request = new Request.Builder()
                .url(manager.property("web.baseUrl"))
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            String body = response.body().string();
            return body.contains("<span class=\"user-info\">");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUserInBrowser(String username, String email) {
        manager.driver().findElement(By.xpath("(//a[normalize-space()='Signup for a new account'])[1]")).click();
        click(By.name("username"));
        type(By.name("username"), username);
        click(By.name("email"));
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Signup']"));
        click(By.xpath("//a[normalize-space()='Proceed']"));
        }

    public void useLink(String url) {
        manager.driver().get(url);
        click(By.xpath("//input[@id='realname']"));
        type(By.xpath("//input[@id='realname']"), "Oleg_test");
        click(By.xpath("//input[@id='password']"));
        type(By.xpath("//input[@id='password']"), "Oleg_test");
        click(By.xpath("//input[@id='password-confirm']"));
        type(By.xpath("//input[@id='password-confirm']"), "Oleg_test");
        click(By.xpath("//button[@type='submit']"));
    }

}

