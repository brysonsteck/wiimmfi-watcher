package me.brysonsteck.wiimmfiwatcher.wiimmfi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class RoomData {
    String roomHeader;
    String playerLink;
    String friendCode;
    ArrayList<Player> players = new ArrayList<>();

    public RoomData (ArrayList<Player> players, String friendCode) {
        this.friendCode = friendCode;
        getPlayerLink();
        Document doc = null;

        if (this.playerLink == null) {
            System.out.println("The player link is null for some reason");
        } else {

            try {
                doc = Jsoup.connect("https://wiimmfi.de/" + this.playerLink)
                        .userAgent("Wiimmfi Watcher for Android (https://github.com/brysonsteck/wiimmfi-watcher) (UNDER DEVELOPMENT)")
                        .get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Element table = doc.select("table").get(0);
            Elements rows = table.select("tr");

            for (int i = 0; i < rows.size(); i++) {
                Element row = rows.get(i);
                Elements colPlayers = row.select("td");
                Elements colHeader = row.select("th");

                if (colHeader.size() > 0) {
                    if (!colHeader.get(0).text().equals("friend code")) {
                        roomHeader = colHeader.get(0).text();
                    }
                }
                if (colPlayers.size() > 0) {

                    Player currentPlayer = new Player();
                    for (int j = 0; j < colPlayers.size(); j++) {
                        System.out.println("Player Data: " + colPlayers.get(j).text());
                        switch (j) {
                            case 0:
                                currentPlayer.friendCode = colPlayers.get(0).text();
                                break;
                            case 1:
                                currentPlayer.role = colPlayers.get(1).text().split(" ")[1];
                                currentPlayer.rosterNumber = colPlayers.get(1).text().split(" ")[0].replaceAll("\\s", "");
                                break;
                            case 2:
                                currentPlayer.loginRegion = colPlayers.get(2).text();
                                break;
                            case 3:
                                currentPlayer.roomMatch = colPlayers.get(3).text();
                                break;
                            case 4:
                                currentPlayer.world = colPlayers.get(4).text();
                                break;
                            case 5:
                                currentPlayer.connFail = colPlayers.get(5).text();
                                break;
                            case 6:
                                currentPlayer.vr = colPlayers.get(6).text();
                                break;
                            case 7:
                                currentPlayer.br = colPlayers.get(7).text();
                                break;
                            case 8:
                                currentPlayer.miiName = colPlayers.get(8).text();
                                break;
                        }
                    }
                    if (currentPlayer.friendCode.equals(friendCode)) {
                        currentPlayer.watching = true;
                    }
                    players.add(currentPlayer);
                }

            }
        }
    }

    public void getPlayerLink() {
        try {
            Document doc = Jsoup.connect("https://wiimmfi.de/stats/mkw")
                    .userAgent("Wiimmfi Watcher for Android (https://github.com/brysonsteck/wiimmfi-watcher) (Developer testing)")
                    .get();
            Element table = doc.select("table").get(0);
            Elements rows = table.select("tr");

            for (int i = 0; i < rows.size(); i++) {
                Element row = rows.get(i);
                Elements colPlayers = row.select("td");


                if (colPlayers.size() > 0) {

                    String data = colPlayers.get(0).select("a").toString();
                    if (data.contains(friendCode)) {
                        System.out.println("Found friend code");
                        playerLink = data.split("\"")[3];
                        System.out.println("Player link: " + playerLink);
                        break;
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Player> getPlayers() { return players; }

    public String getRoomHeader() { return this.roomHeader; }

    public RoomData refresh() {
        players.clear();
        roomHeader = "";
        return new RoomData(players, friendCode);
    }
}

