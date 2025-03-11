package com.sportradarv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SportRadarBoardGameTests {

    @Test
    public void shouldCreateEmptyMatch() {
        Team homeTeam = Team.withName("Mexico");
        Team awayTeam = Team.withName("Canada");
        SportRadarGameBoard sportRadarGameBoard = SportRadarGameBoard.emptyBoard();
        sportRadarGameBoard.registerMatch(homeTeam, awayTeam);

        List<GameSummary> gameSummary = sportRadarGameBoard.getSummary();

        Assertions.assertEquals(1, gameSummary.size());
    }

}
