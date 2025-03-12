package com.sportradarv2;

import java.util.*;

public class SportRadarGameBoard {

    private final Map<String, Game> gamesList = new HashMap<>();

    public boolean registerMatch(String homeTeamName, String awayTeamName) {
        if (notBlank(homeTeamName) && notBlank(awayTeamName)) {
            String key = gameKey(homeTeamName, awayTeamName);
            Game existing = gamesList.putIfAbsent(key, Game.between(homeTeamName, awayTeamName));
            return existing == null;
        }
        return false;
    }

    public boolean isGameInProgress(String homeTeam, String awayTeam) {
        return gamesList.containsKey(gameKey(homeTeam, awayTeam));
    }

    private static String gameKey(String homeTeam, String awayTeam) {
        return homeTeam + awayTeam;
    }

    public boolean finishMatch(String homeTeamName, String awayTeamName) {
        if (notBlank(homeTeamName) && notBlank(awayTeamName)) {
            return gamesList.remove(gameKey(homeTeamName, awayTeamName)) != null;
        }
        return false;
    }

    public List<Game> getSummary() {
        return gamesList.values().stream()
                .sorted(Comparator
                        .comparingInt((Game game) -> game.getHomeTeamScore() + game.getAwayTeamScore()).reversed()
                        .thenComparing(Game::getStartedAt).reversed()
                        .thenComparingLong(Game::getNanoStartedAt).reversed())
                .toList();
    }

    private boolean notBlank(String teamName) {
        return Objects.nonNull(teamName) && !teamName.isBlank();
    }

    public boolean updateScore(String homeTeam, int homeTeamScore, String awayTeam, int awayTeamScore) {
        if (homeTeamScore >= 0 && awayTeamScore >= 0) {
            Game game = gamesList.get(gameKey(homeTeam, awayTeam));
            if (game != null) {
                game.updateScore(homeTeamScore, awayTeamScore);
                return true;
            }
        }
        return false;
    }
}
