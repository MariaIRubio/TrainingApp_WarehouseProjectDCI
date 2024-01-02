package trainingApp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Payment {

	private String paymentMethod;
	private String membershipPlan;
	private boolean isPaid;
	private String statusPayment;
	private String statusAccount;
	private double amount;

	private Scanner scanner = new Scanner(System.in);

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public String getMembershipPlan() {
		return membershipPlan;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public String getStatusPayment() {
		return statusPayment;
	}

	public String getStatusAccount() {
		return statusAccount;
	}

	public String setStatusAccount(String statusAccount) {
		return this.statusAccount = statusAccount;
	}

	public double getAmount() {
		return amount;
	}

	public Scanner getScanner() {
		return scanner;
	}

	@Override
	public String toString() {
		return """

				Payment Info:
				Payment Method: %s
				MembershipPlan: %s
				Status account: %s

				""".formatted(paymentMethod, membershipPlan, statusAccount);
	}

	// CREATE A BILL
	public void generateBill() {
		this.amount = 35;
		double result = 0;
		this.statusPayment = "Paid";
		this.statusAccount = "Active";
		isPaid = true;

		LocalDate today = LocalDate.now();
		LocalDate paymentDate = today.withDayOfMonth(5);

		String membershipPlan = getMembershipPlan();

		switch (membershipPlan.toLowerCase()) {
		case "monthly":
			if (today.getDayOfMonth() > 5 && !isPaid) {
				isPaid = true;
				paymentDate = paymentDate.plusMonths(1);
				this.statusPayment = "Paid";
				this.statusAccount = "Active";
			} else if (today.getDayOfMonth() > 5 && isPaid) {
				isPaid = false;
				this.statusAccount = "Not Active";
				this.statusPayment = "Not Paid";
				System.out.println("Payment is required");
			} else if (today.getDayOfMonth() <= 5 && isPaid) {
				isPaid = false;
				this.statusAccount = "Not Active";
				this.statusPayment = "Not Paid";
				System.out.println(
						"Payment is required. Please make the Payment before 5th to prevent cancellations in your account.");
			}
			break;
		case "semiannual":
			if (today.getDayOfMonth() > 5 && !isPaid) {
				isPaid = true;
				paymentDate = paymentDate.plusMonths(6);
				this.statusPayment = "Paid";
				this.statusAccount = "Active";
			} else if (today.getDayOfMonth() > 5 && isPaid) {
				isPaid = false;
				this.statusAccount = "Not Active";
				this.statusPayment = "Not Paid";
				System.out.println("Payment is required");
			} else if (today.getDayOfMonth() <= 5 && isPaid) {
				isPaid = false;
				this.statusAccount = "Not Active";
				this.statusPayment = "Not Paid";
				System.out.println(
						"Payment is required. Please make the Payment before 5th to prevent cancellations in your account.");
			}
			break;
		default:
			System.out.println("Invalid membership plan. Please choose between 'monthly' or 'semiannual'.");
			break;
		}

		statusPayment = isPaid ? "Paid" : "Not Paid";
		statusAccount = isPaid ? "Active" : "Not Active";

		switch (membershipPlan.toLowerCase()) {
		case "monthly":
			result = amount;
			break;
		case "semiannual":
			result = (amount * 6) * 0.9;
			break;
		default:
			System.out.println("There was an error with your Billing. Please contact support.");
			break;
		}

		// System.out.println("Result before printing the invoice: " + result);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String expiredDate = paymentDate.isBefore(today) ? "- Expired date!!!" : "";

		System.out.println("""

				Billing information:
				Membership Plan: %s
				Status Payment: %s
				Due date for next Payment: %s    %s
				Amount: %.2f €
				Status Membership: %s
				""".formatted(membershipPlan, statusPayment, paymentDate.format(formatter), expiredDate, result,
				statusAccount));
	}

	// SET PAYMENT METHOD (CARD/PAYPAL/BANK TRANSFER)
	public void setPaymentMethod() {

		System.out.println("""

				Choose a payment method:
				Card
				PayPal
				Bank Transfer
				""");

		String paymentMethodInput = scanner.nextLine().toLowerCase();

		switch (paymentMethodInput) {
		// CARD
		case "card" -> {

			String cardName = "";
			String cardNumber = "";
			LocalDate cardExpiration = null;
			String cardCVC = "";

			boolean validNumber = false;
			boolean validDate = false;
			boolean validCVC = false;

			System.out.print("Name: ");
			cardName = scanner.nextLine();
			while (!validNumber) {
				System.out.print("Card Number: ");
				cardNumber = scanner.nextLine();
				if (cardNumber.matches("\\d{16}")) {
					validNumber = true;
				} else {
					System.out.println("Invalid card number. Please enter a 16-digit card number.");
				}
			}

			while (!validDate) {
				System.out.print("Expiration date (dd-mm-yyy): ");
				String cardExpirationDate = scanner.nextLine();
				try {
					cardExpiration = LocalDate.parse(cardExpirationDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
					validDate = true;
				} catch (DateTimeParseException e) {
					System.out.println("Invalid date format. Please enter date in format dd-mm-yyy");
					cardExpirationDate = scanner.nextLine();
				}
			}
			while (!validCVC) {
				System.out.print("CVC: ");
				cardCVC = scanner.nextLine();
				if (cardCVC.matches("\\d{3}")) {
					validCVC = true;
				} else {
					System.out.println("Invalid CVC. Please enter a 3-digit CVC.");
				}
			}
			Card card = new Card(cardName, cardNumber, cardExpiration, cardCVC);
			this.paymentMethod = "Card";
			System.out.println("\nPayment method set succesfully to card.");

		}

		// PAYPAL
		case "paypal" -> {
			boolean validEmail = false;
			String paypalName = "";
			String paypalEmail = "";

			System.out.print("Name: ");
			paypalName = scanner.nextLine();
			while (!validEmail) {
				System.out.print("PayPal email: ");
				paypalEmail = scanner.nextLine();
				if (paypalEmail.matches("^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
					validEmail = true;
				} else {
					System.out.println("Invalid email format. Please enter a valid email address.");
				}
			}
			PayPal paypal = new PayPal(paypalName, paypalEmail);
			this.paymentMethod = "PayPal";
			System.out.println("\nPayment method set succesfully to PayPal.");
		}

		// BANK TRANSFER
		case "bank transfer" -> {
			boolean validIBAN = false;
			String accountIban = "";

			System.out.print("Name: ");
			String accountName = scanner.nextLine();
			while (!validIBAN) {
				System.out.print("IBAN : ");
				accountIban = scanner.nextLine();
				if (accountIban.matches("^[A-Z]{2}\\d{18}$")) {
					validIBAN = true;
				} else {
					System.out.println("Invalid IBAN format. Please enter a valid IBAN.");
				}
			}

			System.out.print("BIC: ");
			String accountBic = scanner.nextLine();

			BankTransfer bankTransfer = new BankTransfer(accountName, accountIban, accountBic);
			this.paymentMethod = "Bank Transfer";
			System.out.println("\nPayment method set succesfully to Bank Transfer.");
		}
		default -> throw new IllegalArgumentException("Wrong payment method. Please try again.");

		}
		;

	}

	// CHANGE PAYMENT METHOD
	public void changePaymentMethod() {
		System.out.println("\nChange Payment Method: ");
		String currentPaymentMethod = getPaymentMethod();
		System.out.println("Current Payment Method: " + currentPaymentMethod);
		setPaymentMethod();

	}

	// DISPLAY MEMBERSHIP
	public void displayMembership() {
		System.out.println("\nDisplay Membership: ");
		System.out.println("Your Membership Plan: " + getMembershipPlan());
	}

	// CHOOSE A MEMBERSHIP
	public void setMembership() {
		boolean validMembership = false;
		while (!validMembership) {
			System.out.println("""

					Choose a Membership Plan:
					Monthly: 35€/month
					Semiannual: 189€/6 months (10%. Before 210€)
					""");

			String membershipInput = scanner.nextLine().toLowerCase();

			switch (membershipInput) {
			// MONTHLY
			case "monthly" -> {
				this.membershipPlan = "Monthly";
				validMembership = true;
			}
			// SEMIANNUAL (6 MONTHS)
			case "semiannual" -> {
				this.membershipPlan = "Semiannual";
				validMembership = true;
			}
			default -> System.out.println("Wrong membership plan. Please try again.");
			}
			;
		}
		System.out.println("Your membership: " + getMembershipPlan());
	}

	// MANAGE MEMBERSHIP (CHANGE STATUS - ACTIVE - PAUSE - CANCEL)
	public void manageStatusMembership() {

		System.out.println("""

				Manage Membership Status:

				Membership: %s
				Status: %s

				""".formatted(getMembershipPlan(), getStatusAccount()));

		switch (getStatusAccount().toLowerCase()) {
		case "active" -> {
			System.out.print("""
					Whould you like to pause or cancel your membership? (pause/cancel) :
					""");
			String answer = scanner.nextLine().toLowerCase();
			switch (answer) {
			case "pause" -> {
				this.statusAccount = "Paused";
				System.out.println("Account paused. \nWe hope to see you back");
			}
			case "cancel" -> {
				this.statusAccount = "Canceled";
				System.out.println("Account canceled. \nWe hope to see you back!");
			}
			default ->
				System.out.println("It was not possible to pause or cancel your membership. Please contact support.");
			}
		}
		case "paused" -> {
			System.out.print("""

					Your account is paused.
					To activate it back you have to talk with support and check your payment.
					Whould you like to cancel your membership? yes/no :
					""");
			String answer = scanner.nextLine().toLowerCase();
			switch (answer) {
			case "yes" -> {
				this.statusAccount = "Canceled";
				System.out.println("Account canceled. \nWe hope to see you back!");
			}
			case "no" -> this.statusAccount = "Paused";
			default -> System.out.println("It was not possible to cancel your membership. Please contact support.");
			}
		}
		case "canceled" -> {
			System.out.print("""

					Your account is canceled.
					To activate it back you have to talk with support and check your payment.

					""");

		}
		default -> System.out.println("It was not possible to update your membership. Please contact support.");
		}
		;

	}

}
