package trainingApp;

public class PayPal {

	private String paypalName;
	private String paypalEmail;
	
	public PayPal(String paypalName, String paypalEmail) {
		this.paypalName = paypalName;
		this.paypalEmail = paypalEmail;
	}

	public String getPaypalName() {
		return paypalName;
	}

	public String getPaypalEmail() {
		return paypalEmail;
	}

	@Override
	public String toString() {
		return """
				
				PayPal info:
				Name: %s
				PaypalEmail: %s
				""".formatted(paypalName, paypalEmail);
	}
	
	
}
