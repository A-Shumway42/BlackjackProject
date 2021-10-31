package com.skilldistillery.cards;

public class DealerHand extends Hand implements HandLogic {

	public DealerHand() {
		super();
	}

	public void dealerHit() {

	}

	public void dealerStay() {

	}

	@Override
	public void hit(Card card) {
		super.addCard(card);
	}

	@Override
	public void stay() {

	}

	@Override
	public boolean isBlackjack() {
		return this.getHandValue() == 21 ? true : false; 
	}

	@Override
	public boolean isBust() {
		return this.getHandValue() > 21 ? true : false;
	}
	
	public boolean canHit() {
		return this.getHandValue() < 17 ? true : false;
	}
	
	public void printDealerHiddenHand() {
		int cardNum = 1;
		for (Card card : this.getHand()) {
			if (cardNum++ == 1) {
				System.out.print("HIDDEN CARD\t");
			} else {
				System.out.print(card.toString() + "\t");
			}
		}
		System.out.println();
	}
}
