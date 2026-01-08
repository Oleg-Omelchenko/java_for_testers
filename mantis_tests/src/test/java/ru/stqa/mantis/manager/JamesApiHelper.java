package ru.stqa.mantis.manager;


import jakarta.mail.Flags;
import jakarta.mail.Folder;
import jakarta.mail.MessagingException;
import okhttp3.*;

import java.io.IOException;
import java.net.CookieManager;
import java.util.Arrays;

public class JamesApiHelper extends HelperBase {

    public static final MediaType JSON = MediaType.get("application/json");

    OkHttpClient client;

    public JamesApiHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();

    }

    public void drainApi(String username, String email) {
        Request request = new Request.Builder()
                .url(String.format("%s/users/%s/mailboxes/%s/messages", manager.property("james.apiServer") ,username, email))
                .delete()
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addBox(String email, String password) throws IOException {
        RequestBody body = RequestBody.create(
                String.format("{\"password\":\"%s\"}", password), JSON);
        Request request = new Request.Builder()
                .url(String.format("%s/users/%s", manager.property("james.apiServer") ,email))
                .put(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
