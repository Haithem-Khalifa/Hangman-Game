
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
//import javax.sound.sampled.SourceDataLine;

public class Hangman{
    public static void main(String[] args){


    String filePath = "word sample.txt";
    ArrayList<String> words = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
        String line;
        while((line = reader.readLine()) != null){
            words.add(line.trim());
        }
        
    } catch (FileNotFoundException e) {
        System.out.println("Could not find file");
    }
    catch(IOException e){
        System.out.println("Something went wrong");
    }

    Random random = new Random();

    String word = words.get(random.nextInt(words.size())) ;


    Scanner scanner =new Scanner(System.in);

    ArrayList<Character> wordState = new ArrayList<>();
    int wrongGuess =0;

    for(int i=0;i<word.length();i++){
        wordState.add('_');
    }

    System.out.println("***************************");
    System.out.println("  Welcome to Hangman Game");
    System.out.println("***************************");

    while (wrongGuess < 6) {
    System.out.print(getHangmanArt(wrongGuess));
    System.out.print("Word: ");

    for(char c : wordState){
        System.out.print(c +" ");
    }
    System.out.println();

    System.out.print("Guess a letter: ");
    char guess = scanner.next().toLowerCase().charAt(0);

    if(word.indexOf(guess) >= 0){
        System.out.println("Correct guess!");

        for(int i=0;i<word.length();i++){
            if(word.charAt(i) == guess){
                wordState.set(i,guess); 
            }
        }
        if(!wordState.contains('_')){
            System.out.print(getHangmanArt(wrongGuess));
            System.out.println("YOU WIN!");
            System.out.println("The word: "+ word);

            break;
        }
    }
    else{
        System.out.println("Wrong guess!");
        wrongGuess++;
    } 
    
    }


    if(wrongGuess >= 6){
    System.out.print(getHangmanArt(wrongGuess));
    System.out.println("GAME OVER!");
    System.out.println("The word is: "+word);
    }


    scanner.close();

    } 
    static String getHangmanArt(int wrongGuess){

    return switch(wrongGuess){
        case 0 -> """



                  """ ;
        case 1 -> """
                   o


                  """ ;
        case 2 -> """
                   o
                   |

                  """ ;

        case 3 -> """
                   o
                  /|

                  """ ;
        case 4 -> """
                   o
                  /|\\

                  """ ;
        case 5 -> """
                   o
                  /|\\
                  /
                  """ ;
        case 6 -> """
                   o
                  /|\\
                  / \\
                  """ ;
        default -> "";
                    
        
    };

    }
}