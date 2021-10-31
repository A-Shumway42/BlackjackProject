package com.skilldistillery.cards;

public class PlayerHand extends Hand implements HandLogic {

	public PlayerHand() {
		super();

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
}
