package fr.epita.cardgame.launcher;

import fr.epita.cardgame.services.CardGame;
import java.util.Random;
import java.util.Scanner;



/**
 * @author Dogara Ishaku
 *
 */
public class Launcher {
    public static void main(String[] args){
        boolean status=true;
        while(status){
            System.out.println("\nEnter your choice");
            System.out.println("1.Show Cards");
            System.out.println("2.Add Player");
            System.out.println("3.Start Game");
            System.out.println("4.Show Statistics");
            System.out.println("5.Quit");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch(choice){
                case 1:
                    CardGame.showCards();
                    break;
                case 2:
                    CardGame.createPlayer();
                    break;
                case 3:
                    CardGame.createGame();
                    break;
                case 4:
                    CardGame.showStatistics();
                    break;
                case 5:
                    status=false;
                    break;
            }
        }
    }
}
