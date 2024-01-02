package trainingApp;

import java.time.LocalDate;

public class Card {
	private String cardName;
	private String cardNumber;
	private LocalDate cardExpiration;
	private String cardCVC;

	public Card(String cardName, String cardNumber, LocalDate cardExpiration, String cardCVC) {
		this.cardName = cardName;
		this.cardNumber = cardNumber;
		this.cardExpiration = cardExpiration;
		this.cardCVC = cardCVC;

	}

	public String getCardName() {
		return cardName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public LocalDate getCardExpiration() {
		return cardExpiration;
	}

	public String getCardCVC() {
		return cardCVC;
	}

	@Override
	public String toString() {
		return """

				Card info:
				Name: %s
				Number: %s
				Expiration Date: %s
				CVC: %s
				""";
	}

}
