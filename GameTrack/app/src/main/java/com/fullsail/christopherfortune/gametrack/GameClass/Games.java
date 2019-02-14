package com.fullsail.christopherfortune.gametrack.GameClass;

public class Games {

    String gameTitle;
    String gameDeveloper;
    String gamePublisher;
    String gameReleaseYear;
    String gameLauncher;

    public Games(){
    }

    public Games(String gameTitle, String gameDeveloper, String gamePublisher, String gameReleaseYear, String gameLauncher){
        this.gameTitle = gameTitle;
        this.gameDeveloper = gameDeveloper;
        this.gamePublisher = gamePublisher;
        this.gameReleaseYear = gameReleaseYear;
        this.gameLauncher = gameLauncher;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public String getGameDeveloper() {
        return gameDeveloper;
    }

    public String getGamePublisher() {
        return gamePublisher;
    }

    public String getGameReleaseYear() {
        return gameReleaseYear;
    }

    public String getGameLauncher() {
        return gameLauncher;
    }
}
