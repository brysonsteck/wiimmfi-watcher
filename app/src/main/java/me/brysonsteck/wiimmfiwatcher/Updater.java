package me.brysonsteck.wiimmfiwatcher;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Updater {

    public boolean outdated = false;
    public String newestRelease;
    public String githubRelease;
    public String playStore = "https://play.google.com/store/apps/details?id=me.brysonsteck.wiimmfiwatcher";

    public Updater() {
        try {
            String json = urlReader();
            JsonElement root = new JsonParser().parse(json);
            JsonObject rootObj = root.getAsJsonObject();
            newestRelease = rootObj.get("current-release").getAsString();
            githubRelease = rootObj.get("github-release").getAsString();
        } catch (IOException e) {
            System.out.println("An error has occurred while attempting to check for updates.");
            e.printStackTrace();
        }
    }

    public String urlReader() throws IOException {
        URL website = new URL("https://brysonsteck.net/watcher.json");
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);

        in.close();

        return response.toString();

    }

    public void compareRelease(String deviceRelease) {
        if (!deviceRelease.equals(newestRelease)) {
            outdated = true;
        }
    }

    public boolean isOutdated() {
        return outdated;
    }

    public String getNewestRelease() {
        return newestRelease;
    }

    public String getGithubRelease() {
        return githubRelease;
    }
}
