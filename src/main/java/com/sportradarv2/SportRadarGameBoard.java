package com.sportradarv2;

import java.util.List;

public class SportRadarGameBoard {
    public static SportRadarGameBoard emptyBoard() {
        return new SportRadarGameBoard();
    }

    public void registerMatch(Team homeTeam, Team awayTeam) {

    }

    public List<GameSummary> getSummary() {
        return List.of();
    }
}
