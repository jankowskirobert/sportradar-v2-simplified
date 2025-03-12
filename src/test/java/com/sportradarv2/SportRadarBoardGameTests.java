package com.sportradarv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SportRadarBoardGameTests {

    @Test
    public void shouldCreateEmptyMatch() {
        // given
        String homeTeamName = "Mexico";
        String awayTeamName = "Canada";
        SportRadarGameBoard sportRadarGameBoard = new SportRadarGameBoard();

        // when
        sportRadarGameBoard.registerMatch(homeTeamName, awayTeamName);

        // then
        Assertions.assertTrue(sportRadarGameBoard.isGameInProgress(homeTeamName, awayTeamName));
    }

    @Test
    public void shouldFinishMatchInProgress() {
        // given
        String homeTeamName = "Mexico";
        String awayTeamName = "Canada";
        SportRadarGameBoard sportRadarGameBoard = new SportRadarGameBoard();
        sportRadarGameBoard.registerMatch(homeTeamName, awayTeamName);

        // when
        boolean hasBeenFinished = sportRadarGameBoard.finishMatch(homeTeamName, awayTeamName);

        // then
        Assertions.assertTrue(hasBeenFinished);
        Assertions.assertFalse(sportRadarGameBoard.isGameInProgress(homeTeamName, awayTeamName));
    }

}
