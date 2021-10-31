package com.skilldistillery.cards;

import java.util.Scanner;

public class BlackjackApp {

	private DealerHand dealerHand;
	private PlayerHand playerHand;
	private Deck deck;

	public static void main(String[] args) {
		BlackjackApp blackJackApp = new BlackjackApp();
		blackJackApp.run();

	}

	private void run() {
		Scanner input = new Scanner(System.in);
		menuChoice(input);
		playBlackJack(input);

		input.close();

	}

	private void printMenu() {
		System.out.println("***********************");
		System.out.println("*______Blackjack______*");
		System.out.println("*   1. Play Game      *");
		System.out.println("*   2. Log Out        *");
		System.out.println("***********************");
	}

	private void menuChoice(Scanner input) {
		boolean keepGoing = true;
		do {
			printMenu();
			int choice = input.nextInt();
			switch (choice) {
			case 1:
				playBlackJack(input);
				break;
			case 2:
				System.out.println("Thank you for playing!");
				keepGoing = false;
				break;
			default:
				System.out.println("Invalid Selection. Please choose 1 or 2.");
				break;
			}
		} while (keepGoing);
	}

	private void playBlackJack(Scanner input) {
		this.playerHand = new PlayerHand();
		this.dealerHand = new DealerHand();
		this.deck = new Deck();
		this.deck.shuffleDeck();
		dealFirstTwoCards();
		printHands("Hidden");

		boolean keepPlaying = true;

		while (keepPlaying) {
			boolean isPlayerStaying = false;
			if (!this.playerHand.isBust() && this.playerHand.getHandValue() != 21) {
				isPlayerStaying = playerHit(input);
				if (isPlayerStaying) {
					dealerHit();
					if (this.playerHand.getHandValue() > this.dealerHand.getHandValue() && !this.dealerHand.isBust()) {
						System.out.println("*************************");
						printHands("Unhidden");
						System.out.println("Player wins!");
					} else if (this.dealerHand.isBust()) {
						System.out.println("*************************");
						printHands("Unhidden");
						System.out.println("Player wins!");
					} else if (this.playerHand.getHandValue() == this.dealerHand.getHandValue()) {
						System.out.println("*************************");
						printHands("Unhidden");
						System.out.println("Push!");
					} else {
						System.out.println("*************************");
						printHands("Unhidden");
						System.out.println("Dealer wins!");
					}
					break;
				}
				if (!this.playerHand.isBust()) {
					printHands("Hidden");
				}
			}
			if (this.playerHand.isBust()) {
				System.out.println("*************************");
				printHands("Unhidden");
				System.out.println("Player bust! Dealer wins!");
				keepPlaying = false;
				break;
			} else if (this.playerHand.isBlackjack()) {
				System.out.println("*************************");
				if (this.dealerHand.isBlackjack()) {
					printHands("Unhidden");
					System.out.println("Push!");
					break;
				} else {
					printHands("Unhidden");
					System.out.println("Player wins! Blackjack!");
					break;
				}
			}
			if (isPlayerStaying) {
				dealerHit();
				if (this.dealerHand.isBust()) {
					System.out.println("*************************");
					printHands("Unhidden");
					System.out.println("Player wins! Dealer Bust!");
					break;
				}
			}
		}
	}

	private void dealFirstTwoCards() {
		System.out.println("Giving intial cards to dealer and player. ");
		for (int i = 0; i < 2; i++) {
			this.playerHand.addCard(deck.dealCard());
			this.dealerHand.addCard(deck.dealCard());
		}
	}

	private boolean playerHit(Scanner input) {
		System.out.println("Do you want to hit or stay? Enter 1 to hit, 0 to stay.");
		int choice = -1;
		while (choice != 0 && choice != 1) {
			choice = input.nextInt();
			if (choice == 1) {
				this.playerHand.addCard(deck.dealCard());
				System.out.println("Player has hit.");
				return false;
			} else if (choice == 0) {
				System.out.println("Player has stayed. Player's turn is over.");
				return true;
			} else {
				System.out.println("Invalid choice, please enter 0 or 1.");
			}
		}
		return false;

	}

	private void printHands(String str) {
		if (str.equals("Hidden")) {
			System.out.println("Dealer's Hand: ");
			this.dealerHand.printDealerHiddenHand();
		} else {
			System.out.println("Dealer's Hand: Value: " + this.dealerHand.getHandValue());
			this.dealerHand.printUnhiddenHand();
		}
		System.out.println("Player's Hand: Value: " + this.playerHand.getHandValue());
		this.playerHand.printUnhiddenHand();
	}

	private void dealerHit() {
		while (this.dealerHand.canHit()) {
			this.dealerHand.addCard(deck.dealCard());
		}
	}
}
