package com.fullsail.christopherfortune.gametrack.MatchClass;

public class Matches {

    private String matchDate;
    private boolean gameWon;
    private String mapName;
    private String matchLength;
    private int kills;
    private int deaths;
    private String mainWeapon;
    private String secondaryWeapon;
    private String grenades;
    private int matchScore;
    private int matchAssists;
    private String matchNotes;

    public Matches(){

    }

    public Matches(String matchDate,
                   boolean gameWon,
                   String mapName,
                   String matchLength,
                   int kills,
                   int deaths,
                   String mainWeapon,
                   String secondaryWeapon,
                   String grenades,
                   int matchScore,
                   int matchAssists,
                   String matchNotes){

        this.matchDate = matchDate;
        this.gameWon = gameWon;
        this.mapName = mapName;
        this.matchLength = matchLength;
        this.kills = kills;
        this.deaths = deaths;
        this.mainWeapon = mainWeapon;
        this.secondaryWeapon = secondaryWeapon;
        this.grenades = grenades;
        this.matchScore = matchScore;
        this.matchAssists = matchAssists;
        this.matchNotes = matchNotes;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public String getMapName() {
        return mapName;
    }

    public String getMatchLength() {
        return matchLength;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public String getMainWeapon() {
        return mainWeapon;
    }

    public String getSecondaryWeapon() {
        return secondaryWeapon;
    }

    public String getGrenades() {
        return grenades;
    }

    public int getMatchScore() {
        return matchScore;
    }

    public int getMatchAssists() {
        return matchAssists;
    }

    public String getMatchNotes() {
        return matchNotes;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public void setMatchLength(String matchLength) {
        this.matchLength = matchLength;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setMainWeapon(String mainWeapon) {
        this.mainWeapon = mainWeapon;
    }

    public void setSecondaryWeapon(String secondaryWeapon) {
        this.secondaryWeapon = secondaryWeapon;
    }

    public void setGrenades(String grenades) {
        this.grenades = grenades;
    }

    public void setMatchScore(int matchScore) {
        this.matchScore = matchScore;
    }

    public void setMatchAssists(int matchAssists) {
        this.matchAssists = matchAssists;
    }

    public void setMatchNotes(String matchNotes) {
        this.matchNotes = matchNotes;
    }
}
