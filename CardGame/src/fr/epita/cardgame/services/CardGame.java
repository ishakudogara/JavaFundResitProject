package fr.epita.cardgame.services;

import fr.epita.cardgame.datamodel.ActivePlayer;
import fr.epita.cardgame.datamodel.Card;
import fr.epita.cardgame.datamodel.Deck;
import fr.epita.cardgame.datamodel.Game;
import fr.epita.cardgame.datamodel.Player;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * @author Dogara Ishaku
 *
 */
public class CardGame {
    public static void showCards(){
        Deck deck = new Deck();
        List<Card> cards = deck.getCards();
        cards.forEach((card) -> {
            System.out.println(card.getValueStr()+" "+card.getColor());
        });
    }
    public static void createPlayer(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Name:");
        String name = scanner.next();
        Player player = new Player();
        player.setName(name);
        if(!checkIfUserExists(player)){
            String insertQuery = "Insert into PLAYERS(Username,Win,Lost) Values(?,?,?)";
            DBConnection conn = new DBConnection();
            try (Connection connection = conn.getConnection();
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);) {
                insertStatement.setString(1, player.getName());
                insertStatement.setInt(2, 0);
                insertStatement.setInt(3, 0);
                insertStatement.execute();
                System.out.println("Player added successfully");
            } catch (SQLException e) {
            	System.out.println(e);
            }
        }else{
            System.out.println("Name already exist. Try another name");
        }
    }
    public static boolean checkIfUserExists(Player player){
        String selectQuery = "Select * from PLAYERS where USERNAME=?";
        DBConnection conn = new DBConnection();
        try (Connection connection = conn.getConnection();
            PreparedStatement preparedStmt = connection.prepareStatement(selectQuery,Statement.RETURN_GENERATED_KEYS);) {
            preparedStmt.setString(1,player.getName());
            try(ResultSet result = preparedStmt.executeQuery();){
                return result.next();
            }catch(SQLException e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    public static int playersCount(){
        String selectQuery = "Select COUNT(*) as COUNT from PLAYERS";
        DBConnection conn = new DBConnection();
        try (Connection connection = conn.getConnection();
            PreparedStatement preparedStmt = connection.prepareStatement(selectQuery);) {
            try(ResultSet result = preparedStmt.executeQuery();){
                if(result.next()) {
                    return result.getInt("COUNT");
                }
            }catch(SQLException e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
    public static void updateWinner(Player player){
        String selectQuery = "Update PLAYERS set WIN=WIN+1 where USERNAME=?";
        DBConnection conn = new DBConnection();
        try (Connection connection = conn.getConnection();
            PreparedStatement preparedStmt = connection.prepareStatement(selectQuery,Statement.RETURN_GENERATED_KEYS);) {
            preparedStmt.setString(1,player.getName());
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static void updateLooser(Player player){
        String selectQuery = "Update PLAYERS set LOST=LOST+1 where USERNAME=?";
        DBConnection conn = new DBConnection();
        try (Connection connection = conn.getConnection();
            PreparedStatement preparedStmt = connection.prepareStatement(selectQuery,Statement.RETURN_GENERATED_KEYS);) {
            preparedStmt.setString(1,player.getName());
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static void showStatistics(){
        String selectQuery = "Select * from PLAYERS";
        DBConnection conn = new DBConnection();
        System.out.println("Players Stats");
        System.out.printf("%10s %30s %20s", "Username", "Win", "Lost");
        System.out.println();
        try (Connection connection = conn.getConnection();
            PreparedStatement preparedStmt = connection.prepareStatement(selectQuery,Statement.RETURN_GENERATED_KEYS);) {
            try(ResultSet result = preparedStmt.executeQuery();){
                while(result.next()){
                    System.out.format("%10s %30s %20s", result.getString("USERNAME"), result.getString("WIN"),result.getString("LOST"));
                    System.out.println();
                     //System.out.println(result.getString("USERNAME")+result.getString("WIN")+result.getString("LOST"));
                }
            }catch(SQLException e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static void createGame(){
        Game game = new Game();
        Deck deck = new Deck();
        game.setCards(deck.getCards());
        List<ActivePlayer> players = new ArrayList<>();
        if(playersCount()<4){
            System.out.println("Please add a maximum of 4 players and then create a game");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        List<String> names = new ArrayList<>();
        for(int i=0;i<4;){
            ActivePlayer activePlayer = new ActivePlayer();
            Player player = new Player();
            System.out.println("Enter name of the player "+(i+1));
            String name = scanner.nextLine();
            player.setName(name);
            if(names.contains(name)){
                System.out.println("Player is already added to the game");
            }else if(!checkIfUserExists(player)){
                System.out.println("Player "+name+" does not exist. Please add player who already exist");
            }else{
                names.add(name);
                i++;
                activePlayer.setPlayer(player);
                activePlayer.setCards(new ArrayList<>());
                players.add(activePlayer);
            }
        }
        game.setPlayer(players);
        game.start();
    }
}
