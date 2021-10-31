package com.skilldistillery.cards;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {
	private List<Card> hand = new ArrayList<>();

	public Hand() {
	}

	public void addCard(Card card) {
		this.hand.add(card);
	}

	public int getHandValue() {
		int value = 0;
		if (this.hand.size() > 0) {
			for (Card card : this.hand) {
				if (card != null) {
					value += card.getValue();
				}
			}
		}
		return value;
	}

	public List<Card> getHand() {

		return this.hand;
	}

	public int getHandSize() {
		return this.hand.size();

	}
	public void printUnhiddenHand() {
		for (Card card : this.hand) {
			System.out.print(card.toString() + "\t");
		}
		System.out.println();
	}

	public abstract void hit(Card card);

	public abstract void stay();
}
