package com.sportradarv2;

import java.util.*;

public class SportRadarGameBoard {

    private final Map<String, List<ScoreUpdate>> gamesList = new HashMap<>();

    public void registerMatch(String homeTeam, String awayTeam) {
        String key = gameKey(homeTeam, awayTeam);

        ScoreUpdate defaultScore = new ScoreUpdate(0, 0);
        List<ScoreUpdate> scoreUpdateList = List.of(defaultScore);
        gamesList.put(key, new ArrayList<>(scoreUpdateList));
    }

    public boolean isGameInProgress(String homeTeam, String awayTeam) {
        return gamesList.containsKey(gameKey(homeTeam, awayTeam));
    }

    private static String gameKey(String homeTeam, String awayTeam) {
        return homeTeam + awayTeam;
    }
}
