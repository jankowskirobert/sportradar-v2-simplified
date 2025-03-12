package com.sportradarv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FullGameBoardScenarioTest {
    static final String MEXICO = "Mexico";
    static final String CANADA = "Canada";

    static final String SPAIN = "Spain";
    static final String BRAZIL = "Brazil";

    static final String GERMANY = "Germany";
    static final String FRANCE = "France";

    static final String URUGUAY = "Uruguay";
    static final String ITALY = "Italy";

    static final String ARGENTINA = "Argentina";
    static final String AUSTRALIA = "Australia";

    @Test
    public void shouldSimulateFullTestFlowForAllTeams() {
        // given
        SportRadarGameBoard sportRadarGameBoard = new SportRadarGameBoard();
        sportRadarGameBoard.registerMatch(MEXICO, CANADA);
        sportRadarGameBoard.registerMatch(SPAIN, BRAZIL);
        sportRadarGameBoard.registerMatch(GERMANY, FRANCE);
        sportRadarGameBoard.registerMatch(URUGUAY, ITALY);
        sportRadarGameBoard.registerMatch(ARGENTINA, AUSTRALIA);

        sportRadarGameBoard.updateScore(MEXICO, 0, CANADA, 5);
        sportRadarGameBoard.updateScore(SPAIN, 10, BRAZIL, 2);
        sportRadarGameBoard.updateScore(GERMANY, 2, FRANCE, 2);
        sportRadarGameBoard.updateScore(URUGUAY, 6, ITALY, 6);
        sportRadarGameBoard.updateScore(ARGENTINA, 3, AUSTRALIA, 1);

        // when
        List<Game> scores = sportRadarGameBoard.getSummary();

        // then
        Assertions.assertEquals(5, scores.size());
        Assertions.assertTrue(scores.get(0).teamsAre(URUGUAY, ITALY));
        Assertions.assertTrue(scores.get(1).teamsAre(SPAIN, BRAZIL));
        Assertions.assertTrue(scores.get(2).teamsAre(MEXICO, CANADA));
        Assertions.assertTrue(scores.get(3).teamsAre(ARGENTINA, AUSTRALIA));
        Assertions.assertTrue(scores.get(4).teamsAre(GERMANY, FRANCE));
    }

}
