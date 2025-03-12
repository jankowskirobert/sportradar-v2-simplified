package com.sportradarv2;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Game {

    private final String homeTeam;
    private final String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private final Instant startedAt;
    private final long nanoStartedAt;

    public static Game between(String homeTeam, String awayTeam) {
        return new Game(homeTeam, awayTeam, 0, 0, Instant.now(), System.nanoTime());
    }

    public boolean teamsAre(String homeTeam, String awayTeam) {
        return this.homeTeam.equals(homeTeam) && this.awayTeam.equals(awayTeam);
    }


    public void updateScore(int homeTeamScore, int awayTeamScore) {
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    public boolean scoresAre(int homeTeamScoreToCompare, int awayTeamScoreToCompare) {
        return homeTeamScore == homeTeamScoreToCompare && awayTeamScore == awayTeamScoreToCompare;
    }
}
