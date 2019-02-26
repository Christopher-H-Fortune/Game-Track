package com.fullsail.christopherfortune.gametrack.MatchListClass;

public class MatchesListClass {

    private String mapName;
    private String date;
    private int assists;
    private int kills;
    private int score;

    public MatchesListClass(){

    }

   public MatchesListClass(String mapName, String date, int assists, int kills, int score){
        this.mapName = mapName;
        this.date = date;
        this.assists = assists;
        this.kills = kills;
        this.score = score;
   }

    public String getMapName() {
        return mapName;
    }

    public String getDate() {
        return date;
    }

    public int getAssists() {
        return assists;
    }

    public int getKills() {
        return kills;
    }

    public int getScore() {
        return score;
    }
}
