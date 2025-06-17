package ru.stqa.mantis.manager;


import okhttp3.*;

import java.io.IOException;
import java.net.CookieManager;

public class JamesAPIHelper extends HelperBase {

    OkHttpClient client;
    public static final MediaType JSON = MediaType.get("application/jason");

   public JamesAPIHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
    }

    public void addUser(String email, String password) {
        RequestBody body = RequestBody.create(
                String.format("{\"password\":\"%s\"}", password), JSON);
        Request request = new Request.Builder()
                .url(String.format("%s/users/%s", manager.property("jamesAPIBaseUrl"), email))
                .put(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
