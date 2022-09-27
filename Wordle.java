/*
 * Wordle.java 
 * 
 * An console-based implementation of a popular word-guessing game
 * 
 * starter code: Computer Science 112 staff (cs112-staff@cs.bu.edu)
 *
 * completed by: 
 */

import java.util.*;

public class Wordle {
    // the name of a file containing a collection of English words, one word per line
    public static final String WORD_FILE = "words.txt";

    /*
     * printWelcome - prints the message that greets the user at the beginning of the game
     */  
    public static void printWelcome() {
        System.out.println();   
        System.out.println("Welcome to Wordle!");
        System.out.println("The mystery word is a 5-letter English word.");
        System.out.println("You have 6 chances to guess it.");
        System.out.println();
    }
    
    /*
     * initWordList - creates the WordList object that will be used to select
     * the mystery work. Takes the array of strings passed into main(),
     * since that array may contain a random seed specified by the user 
     * from the command line.
     */
    public static WordList initWordList(String[] args) {
        int seed = -1;
        if (args.length > 0) {
            seed = Integer.parseInt(args[0]);
        }

        return new WordList(WORD_FILE, seed);
    }

    /*
     * readGuess - reads a single guess from the user and returns it
     * inputs:
     *   guessNum - the number of the guess (1, 2, ..., 6) that is being read
     *   console - the Scanner object that will be used to get the user's inputs
     */
    public static String readGuess(int guessNum, Scanner console) {
        String guess;
        do {
            System.out.print("guess " + guessNum + ": ");
            guess = console.next();
        } while (! isValidGuess(guess));

        return guess.toLowerCase();
    }

    /**** ADD YOUR METHODS FOR TASK 1 HERE ****/
     
    public static boolean includes(String s, char c){
        for(int i = 0; i<s.length() ; i++){
            if(s.indexOf(c)==i){
                return true;
            }
        }
        return false;
        
    }

    public static boolean isAlpha(String s){
        for (int i = 0; i < s.length(); i++){
            if (Character.isAlphabetic(s.charAt(i)) == false){
            return false ;
            }
        }
        return true;
    }

    public static int numOccur(char c, String s){
        int count = 0;
        for (int i = 0; i< s.length(); i++){
            if (s.charAt(i)==c){
                count=count+1;
            }
        }
        return count;
    }

    public static int numInSamePosn(char c, String s1, String s2){
        int count = 0;
        for (int i = 0; i< s1.length(); i++){
            if (c == s1.charAt(i) && s1.charAt(i) == s2.charAt(i)){
                count = count +1;
            }
        
        }
        return count;
    }

        
        
        




    /*
     * TASK 2: Implement this method
     * 
     * isValidGuess -  takes an arbitrary string guess and returns true
     * if it is a valid guess for Wordle, and false otherwise
     */


    /**** ADD YOUR METHOD FOR TASKS 3 and 5 HERE. ****/
    public static boolean isValidGuess(String guess) {
        if (guess.length()!=5){
            System.out.println("Your guess must be 5 letters long.");
            return false;
        }
        else if (isAlpha(guess)==false){
            System.out.println("Your guess must only contain letters of the alphabet");
            return false;
        } 
        else{
            return true;
        }

    }

    public static boolean processGuess(String guess, String mystery){
        String current = " ";
        System.out.print("  ");
        if (guess.equals(mystery)){
            System.out.println(guess.charAt(0) + " " + guess.charAt(1) + " " + guess.charAt(2) + " " + guess.charAt(3) + " " + guess.charAt(4) + "");
            return true;
        }
        
        
        else{
            for (int i=0; i<5; i +=1){

                if (guess.charAt(i)==mystery.charAt(i)){
                    System.out.print(guess.charAt(i) +" ");
                    current += guess.charAt(i) + "";
                }
                else if (includes(mystery, guess.charAt(i)) && numOccur(guess.charAt(i), current)<numOccur(guess.charAt(i), mystery)){
                    if (numInSamePosn(guess.charAt(i), guess, mystery) < numOccur(guess.charAt(i), mystery)){
                        System.out.print("[" + guess.charAt(i) + "] ");
                        current += guess.charAt(i) + "";
                    }
                    
                 
                    else{
                        System.out.print("_ ");
                    }
                }
                else{
                System.out.print("_ ");
                }
            }
        }
        System.out.println(" ");  
        return false;
    }


    
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        
        printWelcome();

        // Create the WordList object for the collection of possible words.
        WordList words= initWordList(args);

        // Choose one of the words as the mystery word.
        String mystery = words.getRandomWord();
        
        /*** TASK 4: Implement the rest of the main method below. ***/
        boolean winn = false;
        for (int i = 0; i<6; i++){
            String guess = readGuess(i+1, console);
            winn = processGuess(guess, mystery);
            if(winn == true){
                System.out.println("Congrats! You guessed it!");
                
                break;
            }
            
                

            

        }
        if(winn==false){
            System.out.println("Sorry! Better luck next time!");
            System.out.println("The word was " + mystery + ".");
        }

        console.close();
    }
}