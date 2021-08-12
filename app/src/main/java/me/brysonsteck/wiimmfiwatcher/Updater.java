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
    public boolean failed = false;
    public String newestRelease;
    public String githubRelease;
    public String playStore = "https://play.google.com/store/apps/details?id=me.brysonsteck.wiimmfiwatcher";

    public Updater() {
        try {
            String json = urlReader();
            JsonElement root = new JsonParser().parse(json);
            JsonObject rootObj = root.getAsJsonObject();
            JsonElement softwareElement = rootObj.getAsJsonObject("wiimmfi-watcher");
            JsonObject softwareObj = softwareElement.getAsJsonObject();
            newestRelease = softwareObj.get("current-release").getAsString();
            newestRelease = newestRelease.replace("\"", "");
            githubRelease = softwareObj.get("github-release").getAsString();
            githubRelease = githubRelease.replace("\"", "");
        } catch (IOException e) {
            System.out.println("An error has occurred while attempting to check for updates.");
            e.printStackTrace();
        }
    }

    public String urlReader() throws IOException {
        URL website = new URL("https://brysonsteck.net/updates.json");
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
        if (newestRelease == null) {
            failed = true;
        }
        else if (!deviceRelease.equals(newestRelease)) {
            outdated = true;
        }
    }

    public boolean isOutdated() {
        return outdated;
    }

    public boolean hasFailed() { return failed; }

    public String getNewestRelease() {
        return newestRelease;
    }

    public String getGithubRelease() {
        return githubRelease;
    }
}
