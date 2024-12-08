package co.myproject.javarushquest.model;

public class PlayerInfo {
    private String name;
    private String ip;
    private int gamesPlayed;

    public PlayerInfo(String name, String ip) {
        this.name = name;
        this.ip = ip;
        this.gamesPlayed = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void incrementGamesPlayed() {
        this.gamesPlayed++;
    }
}
