package org.uob.a1;

public class Score {
    private int numRoomsVisited;
    private int puzzlesSolved;
    private int startingScore;
    private double score;
    private final int PUZZLE_VALUE = 10;

    public Score(int startingScore) {
        this.numRoomsVisited = 0;
        this.startingScore = startingScore;
        this.puzzlesSolved = 0;
        this.score = 0.0;
    }

    public void visitRoom() {
        numRoomsVisited++;
    }

    public void solvePuzzle() {
        puzzlesSolved++;
    }

    public double getScore() {
        score = (startingScore - numRoomsVisited) + (puzzlesSolved * PUZZLE_VALUE);
        return score;
    }
        
}