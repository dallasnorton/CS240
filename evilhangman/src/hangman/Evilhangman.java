package hangman;

import java.io.File;
import java.util.*;

public class Evilhangman {

    public static void main(String[] args) {
        int remainingGuesses = -1;
        int wordLength = -1;
        String filePath = null;
        Set<String> tempSet = new TreeSet<String>();
        String word = "";
        Evilhangman hangMan = new Evilhangman();
        
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
                System.out.println("Used letters: " + hangManGame.getUsedLetters().toString());
                System.out.println("Word: " + word.toString());
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

                try{
                    tempSet = hangManGame.makeGuess(chr);
                    remainingGuesses--;
                    hangMan.buildNewWord(tempSet.iterator().next(), word, chr);
                    if(tempSet.size() == 1){
                        System.out.println("You win!");
                        return;
                    }
                }
                catch (EvilHangmanGame.GuessAlreadyMadeException e) {
                    System.out.println("You already used that letter. Please try again.\n");
                }
            }
            System.out.println("You lose!");
            System.out.println("The word was: " + tempSet.iterator().next().toString());
            in.close();
            
        } 
        catch (Exception e) {
            System.out.println("USAGE: java EvilHangman wordLength or guesses incorrect values");
            e.printStackTrace();
            return;
        }
    }
    
    void buildNewWord(String str, String word, char guess) {
        StringBuilder wordDash = new StringBuilder(word);
        
        for(int i = 0; i < str.length(); i ++){
            if(str.charAt(i) == guess){
                wordDash.replace(i, i, str);
            }
        }
        
    }
}
