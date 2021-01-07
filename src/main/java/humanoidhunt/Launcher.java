package humanoidhunt;

import humanoidhunt.puzzle.android.AndroidPuzzle;
import humanoidhunt.puzzle.nanobots.NanobotsPuzzle;
import humanoidhunt.puzzle.tattoo.TattooPuzzle;

import java.io.IOException;

public class Launcher {

    public static void main(String[] args) {
        System.out.println("Welcome to Humanoid Hunt!");

        try {
            System.out.println("Solution for first puzzle: " +
                    TattooPuzzle.loadInputsAndSolve());

            System.out.println("Solution for second puzzle: " +
                    NanobotsPuzzle.loadInputAndSolve());

            System.out.println("Solution for third puzzle: " +
                    AndroidPuzzle.loadInputsAndSolve());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
