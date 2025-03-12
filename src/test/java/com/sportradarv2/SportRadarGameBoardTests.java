package com.sportradarv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SportRadarGameBoardTests {
    static final String MEXICO = "Mexico";
    static final String CANADA = "Canada";
    static final String SPAIN = "Spain";
    static final String BRAZIL = "Brazil";

    @Test
    public void shouldCreateEmptyMatch() {
        // given
        SportRadarGameBoard sportRadarGameBoard = new SportRadarGameBoard();

        // when
        sportRadarGameBoard.registerMatch(MEXICO, CANADA);

        // then
        Assertions.assertTrue(sportRadarGameBoard.isGameInProgress(MEXICO, CANADA));
    }

    @Test
    public void shouldNotCreateEmptyMatch_NullAsATeamHome() {
        // given
        SportRadarGameBoard sportRadarGameBoard = new SportRadarGameBoard();

        // when
        boolean matchRegistered = sportRadarGameBoard.registerMatch(null, CANADA);

        // then
        Assertions.assertFalse(matchRegistered);
    }

    @Test
    public void shouldNotCreateEmptyMatch_NullAsATeamAway() {
        // given
        SportRadarGameBoard sportRadarGameBoard = new SportRadarGameBoard();

        // when
        boolean matchRegistered = sportRadarGameBoard.registerMatch(MEXICO, null);

        // then
        Assertions.assertFalse(matchRegistered);
    }

    @Test
    public void shouldNotCreateEmptyMatch_NullAsATeams() {
        // given
        SportRadarGameBoard sportRadarGameBoard = new SportRadarGameBoard();

        // when
        boolean matchRegistered = sportRadarGameBoard.registerMatch(null, null);

        // then
        Assertions.assertFalse(matchRegistered);
    }

    @Test
    public void shouldFinishMatchInProgress() {
        // given
        SportRadarGameBoard sportRadarGameBoard = new SportRadarGameBoard();
        sportRadarGameBoard.registerMatch(MEXICO, CANADA);

        // when
        boolean hasBeenFinished = sportRadarGameBoard.finishMatch(MEXICO, CANADA);

        // then
        Assertions.assertTrue(hasBeenFinished);
        Assertions.assertFalse(sportRadarGameBoard.isGameInProgress(MEXICO, CANADA));
    }

    @Test
    public void shouldFinishMatchInProgress_matchHasNotBeenRegistered() {
        // given
        SportRadarGameBoard sportRadarGameBoard = new SportRadarGameBoard();

        // when
        boolean hasBeenFinished = sportRadarGameBoard.finishMatch(MEXICO, CANADA);

        // then
        Assertions.assertFalse(hasBeenFinished);
    }

    @Test
    public void shouldNotFinishMatchInProgress_nullHomeTeamName() {
        // given
        SportRadarGameBoard sportRadarGameBoard = new SportRadarGameBoard();
        sportRadarGameBoard.registerMatch(MEXICO, CANADA);

        // when
        boolean hasBeenFinished = sportRadarGameBoard.finishMatch(null, CANADA);

        // then
        Assertions.assertFalse(hasBeenFinished);
        Assertions.assertTrue(sportRadarGameBoard.isGameInProgress(MEXICO, CANADA));
    }

    @Test
    public void shouldGetMatchSummaryOrderByTotalScore() {
        // given
        SportRadarGameBoard sportRadarGameBoard = new SportRadarGameBoard();
        sportRadarGameBoard.registerMatch(MEXICO, CANADA);

        // when
        List<Game> scores = sportRadarGameBoard.getSummary();

        // then
        Assertions.assertTrue(sportRadarGameBoard.isGameInProgress(MEXICO, CANADA));
        Assertions.assertEquals(1, scores.size());
        Assertions.assertTrue(scores.get(0).teamsAre(MEXICO, CANADA));
    }

    @Test
    public void shouldGetMatchSummaryOrderByRecentlyStarted_scoresAreTheSame() {
        // given
        SportRadarGameBoard sportRadarGameBoard = new SportRadarGameBoard();
        sportRadarGameBoard.registerMatch(MEXICO, CANADA);
        sportRadarGameBoard.registerMatch(SPAIN, BRAZIL);

        // when
        List<Game> scores = sportRadarGameBoard.getSummary();

        // then
        Assertions.assertEquals(2, scores.size());
        Assertions.assertTrue(scores.get(0).teamsAre(SPAIN, BRAZIL));
        Assertions.assertTrue(scores.get(1).teamsAre(MEXICO, CANADA));
    }

    @Test
    public void shouldUpdateScore() {
        // given
        SportRadarGameBoard sportRadarGameBoard = new SportRadarGameBoard();
        sportRadarGameBoard.registerMatch(MEXICO, CANADA);
        sportRadarGameBoard.updateScore(MEXICO, 1, CANADA, 2);

        // when
        List<Game> scores = sportRadarGameBoard.getSummary();

        // then
        Assertions.assertEquals(1, scores.size());
        Assertions.assertTrue(scores.get(0).teamsAre(MEXICO, CANADA));
        Assertions.assertTrue(scores.get(0).scoresAre(1, 2));
    }

    @Test
    public void shouldNotUpdateScore_negativeValueForHomeTeam() {
        // given
        SportRadarGameBoard sportRadarGameBoard = new SportRadarGameBoard();
        sportRadarGameBoard.registerMatch(MEXICO, CANADA);
        boolean scoreUpdated = sportRadarGameBoard.updateScore(MEXICO, -1, CANADA, 2);

        // when
        List<Game> scores = sportRadarGameBoard.getSummary();

        // then
        Assertions.assertFalse(scoreUpdated);
        Assertions.assertEquals(1, scores.size());
        Assertions.assertTrue(scores.get(0).teamsAre(MEXICO, CANADA));
        Assertions.assertTrue(scores.get(0).scoresAre(0, 0));
    }

    @Test
    public void shouldNotUpdateScore_negativeValueForAwayTeam() {
        // given
        SportRadarGameBoard sportRadarGameBoard = new SportRadarGameBoard();
        sportRadarGameBoard.registerMatch(MEXICO, CANADA);
        boolean scoreUpdated = sportRadarGameBoard.updateScore(MEXICO, 1, CANADA, -2);

        // when
        List<Game> scores = sportRadarGameBoard.getSummary();

        // then
        Assertions.assertFalse(scoreUpdated);
        Assertions.assertEquals(1, scores.size());
        Assertions.assertTrue(scores.get(0).teamsAre(MEXICO, CANADA));
        Assertions.assertTrue(scores.get(0).scoresAre(0, 0));
    }

    @Test
    public void shouldNotUpdateScore_negativeValueForBothTeams() {
        // given
        SportRadarGameBoard sportRadarGameBoard = new SportRadarGameBoard();
        sportRadarGameBoard.registerMatch(MEXICO, CANADA);
        boolean scoreUpdated = sportRadarGameBoard.updateScore(MEXICO, -1, CANADA, -2);

        // when
        List<Game> scores = sportRadarGameBoard.getSummary();

        // then
        Assertions.assertFalse(scoreUpdated);
        Assertions.assertEquals(1, scores.size());
        Assertions.assertTrue(scores.get(0).teamsAre(MEXICO, CANADA));
        Assertions.assertTrue(scores.get(0).scoresAre(0, 0));
    }

}
