/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.Scanner;

/**
 *
 * @author gubotdev
 */
public class Dealer {
    
    private Hand dealerHand = new Hand();
    private Player[] myPlayers;
    private Deck myDeck = new Deck();
    private Scanner scan = new Scanner(System.in);
    
    public Dealer(int numOfPlayers){
        
        initMyPlayers(numOfPlayers);
        
        
    }
    
    public void playGame(){
        dealOutOpeningHand();
        takePlayerTurns();
        playOutDealerHand();
        declareWinners();
    }
    
    public void dealOutOpeningHand(){
        for(int i = 0; i < 2; i++){
            for(Player currPlayer : myPlayers){
                //for each Player in myPlayers it puts into currPlayer
                currPlayer.getMyHand().addCard(myDeck.dealCard());
            }
            dealerHand.addCard(myDeck.dealCard());
        }
    }
    
    public void takePlayerTurns(){
        for(Player currPlayer : myPlayers){
            while(currPlayer.getMyHand().getNumOfCards() < 5 &&
                    currPlayer.getMyHand().getScore() < 21){
                System.out.println(currPlayer.getName() + "'s Hand");
                currPlayer.getMyHand().printHand();
                System.out.println("Wanna Hit? (y/n)");
                char opt = scan.next().charAt(0);
                if(opt=='y'){
                    currPlayer.getMyHand().addCard(myDeck.dealCard());
                }else{
                    break;
                }
            }
            currPlayer.getMyHand().printHand();
        }
    }
    
    public void playOutDealerHand(){
        while(dealerHand.getScore() < 17){
            dealerHand.addCard(myDeck.dealCard());
        }
        dealerHand.printHand();
    }
    
    public void declareWinners(){
        System.out.println("Dealer's Hand: ");
        dealerHand.printHand();
        System.out.println("Dealer score: " + dealerHand.getScore());
        for(int i = 0; i < myPlayers.length; i++){
            Player currPlayer = myPlayers[i];
            System.out.println(currPlayer.getMyHand().getScore());
            System.out.println(currPlayer.getName() + "'s hand: ");
            if(dealerHand.getScore() > 21 || currPlayer.getMyHand().getScore()
                    > 21){
                
                if(currPlayer.getMyHand().getScore()>21){
                    System.out.println(currPlayer.getName() + " you busted ");
                }else{
                     System.out.println(currPlayer.getName() + " dealer busted, "
                    + "you win!");
                }
            }else if(dealerHand.getScore() == 21 || dealerHand.getNumOfCards()
            > 4){
                System.out.println("The dealer is hot tonight...you lose!!!");
            }else if(currPlayer.getMyHand().getNumOfCards() > 4){
                System.out.println(currPlayer.getName() + " Five cards under"
                        + "... good job (I guess?), you win");
            }else if(currPlayer.getMyHand().getScore() > dealerHand.getScore()){
                System.out.println("You win!");
            }else{
                System.out.println(" Quit while you still can, buddy.");
            }
        }
        
//        for(Player currPlayer : myPlayers){
//            if(currPlayer.getMyHand().getScore() > 21){
//                System.out.println(currPlayer + " loses");
//            }else if(currPlayer.getMyHand().getNumOfCards() == 5 && 
//                    (dealerHand.getScore() < 21 || 
//                    dealerHand.getNumOfCards() < 5)){
//                System.out.println(currPlayer + " wins");
//            }else if(dealerHand.getScore() >= 
//                    currPlayer.getMyHand().getScore()){
//                System.out.println(currPlayer + " loses");
//            }else if(currPlayer.getMyHand().getScore() > dealerHand.getScore()){
//                System.out.println(currPlayer + " wins");
//            }
//        }
                
    }

    private void initMyPlayers(int num) {
        myPlayers = new Player[num];
        for(int i = 0; i < myPlayers.length; i++){
            System.out.println("Player " + (i+1) + " what's your name: ");
            String name = scan.next();
            if(name.equals("")){
                myPlayers[i] = new Player(i +1);
            }else{
                myPlayers[i] = new Player(name);
            }
        }
    }
    
}
