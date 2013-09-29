package evilhangman;

import java.io.File;
import java.util.*;

public class Evilhangman {

    public static void main(String[] args) {
        int remainingGuesses = -1;
        int wordLength = -1;
        String filePath = null;
        
        try {
            filePath = args[0];
            wordLength = Integer.parseInt(args[1]);
            remainingGuesses = Integer.parseInt(args[2]);

            if (wordLength < 2 || remainingGuesses < 1 || filePath == null){
                return;
            }
            
            MyEvilHangmanGame hangManGame = new MyEvilHangmanGame();
            hangManGame.startGame(new File(filePath), wordLength);
            
            Scanner in = new Scanner(System.in);
            while(remainingGuesses > 0){
                System.out.println("You have " + remainingGuesses + " guess" + (remainingGuesses == 1 ? "" : "es") + " left");
                System.out.println("Used letters: " + hangManGame.usedLetters.toString());
                System.out.println("Word: ");
                System.out.print("Enter guess: ");
                String charGuess = in.next().toLowerCase();
                char chr;
                
                if (charGuess.length() > 1) {
                    System.out.println("Please enter one character only.  Please try again.\n");
                    continue;
                }
                else {
                    chr = charGuess.charAt(0);
                }

                hangManGame.makeGuess(chr); 

            }
            
        } catch (Exception e) {
            System.out.println("USAGE: java EvilHangman wordLength or guesses incorrect values");
            return;
        }
    }
}
