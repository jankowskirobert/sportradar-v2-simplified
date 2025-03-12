package com.sportradarv2;

import java.util.*;

public class SportRadarGameBoard {

    private final Map<String, List<ScoreUpdate>> gamesList = new HashMap<>();

    public boolean registerMatch(String homeTeam, String awayTeam) {
        String key = gameKey(homeTeam, awayTeam);

        ScoreUpdate defaultScore = new ScoreUpdate(0, 0);
        List<ScoreUpdate> scoreUpdateList = List.of(defaultScore);
        List<ScoreUpdate> existing = gamesList.putIfAbsent(key, new ArrayList<>(scoreUpdateList));
        return existing == null;
    }

    public boolean isGameInProgress(String homeTeam, String awayTeam) {
        return gamesList.containsKey(gameKey(homeTeam, awayTeam));
    }

    private static String gameKey(String homeTeam, String awayTeam) {
        return homeTeam + awayTeam;
    }

    public boolean finishMatch(String homeTeamName, String awayTeamName) {
        return gamesList.remove(gameKey(homeTeamName, awayTeamName)) != null;
    }
}
