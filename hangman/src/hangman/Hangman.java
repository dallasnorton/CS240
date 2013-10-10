package hangman;

import java.io.File;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hangman {

    public static void main(String[] args) {
        String filePath;
        int wordLength;
        int guesses;
        StringBuilder word = new StringBuilder();
        Set<String> set = new TreeSet();
                
        filePath = args[0];
        wordLength = Integer.parseInt(args[1]);
        guesses = Integer.parseInt(args[2]);
        
        if(wordLength < 2 || guesses < 1 || filePath == null){
            return;
        }
        
        for(int i = 0; i < wordLength; i++){
            word.append("-");
        }
        
        MyEvilHangmanGame hangGame = new MyEvilHangmanGame();
        hangGame.startGame(new File(filePath), wordLength);
        Scanner in = new Scanner(System.in);
        while(guesses > 0){
            String guessStr = "";
            if(guesses == 1){
                guessStr = "You have " + guesses + " guess left.";
            }
            else{
                guessStr = "You have " + guesses + " guesses left.";
            }
            System.out.println(guessStr);
            System.out.println("Used letters: " + hangGame.getUsedLetters().toString());
            System.out.println("Word: " + word.toString());
            System.out.println("Enter guess: ");
            String charGuess = in.next().toLowerCase();
            char chr;
            if(charGuess.length() > 1 ){
                System.out.println("Please only enter in one character.  Please try again.");
                continue;
            }
            else{
                chr = charGuess.charAt(0);
            }
            try {
                set = hangGame.makeGuess(chr);
                
                boolean flag = false;
                
                for(int i = 0; i < set.iterator().next().length(); i++){
                    if(set.iterator().next().charAt(i) == chr){
                        flag = true;
                        word.replace(i, i+1, Character.toString(chr));
                    }
                }
                if(!flag){
                    guesses--;                    
                }

                
                if(set.size() == 1 && set.iterator().next().toString().equals(word.toString())){
                    System.out.println("You Win!");
                    return;
                }
            } 
            catch (EvilHangmanGame.GuessAlreadyMadeException ex) {
                System.out.print("That guess has already been made please try again.");
            }   
        }
        System.out.println("You lose!");
        System.out.println("The word was " + set.iterator().next().toString());
        in.close();
        return;
    }
}
