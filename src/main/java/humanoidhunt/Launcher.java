package humanoidhunt;

import humanoidhunt.puzzle.tattoo.TattooPuzzle;

import java.io.IOException;

public class Launcher {

    public static void main(String[] args) {
        System.out.println("Welcome to Humanoid Hunt!");

        try {
            System.out.println("Solution for first puzzle: " +
                    TattooPuzzle.loadInputsAndSolve());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
