package com.skilldistillery.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DealingTest {

	public static void main(String[] args) {
		DealingTest dealing = new DealingTest();
		dealing.deal();


	}
	
	public void deal() {
		// get a deck of cards 
		Deck deck = new Deck();
		
		// call shuffle method to shuffle deck
		deck.shuffleDeck();
		
		// ask the user "how many cards?"
		Scanner sc = new Scanner(System.in);
		System.out.println("How many cards do you want?");
		int howMany = sc.nextInt();
		sc.nextLine();
		
		List<Card> userHand = new ArrayList<>();
		int handTotal = 0;
		
		// deal input amount using deal method, one card/time
		for (int numCard = 0; (numCard < howMany) && (deck != null) && (numCard < 52) && (deck.checkDeckSize() > 0 ); numCard++) {
		Card dealtCard = deck.dealCard();
		
		// user puts dealt card in hand
		userHand.add(dealtCard);
		
		// the user adds up the cards, based on the rank
		handTotal += dealtCard.getValue();
		
		
		// have the user show their hand
		System.out.println("Player total of hand: " + handTotal);
		for (Card card : userHand) {
			System.out.println(card);
			
		}
		
		
		}
		
	}

}
