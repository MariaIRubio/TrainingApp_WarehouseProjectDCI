package trainingApp;

public class BankTransfer {
	private String accountName;
	private String accountIban;
	private String accountBic;
	
	public BankTransfer(String accountName, String accountIban, String accountBic) {
		this.accountName = accountName;
		this.accountIban = accountIban;
		this.accountBic = accountBic;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getAccountIban() {
		return accountIban;
	}

	public String getAccountBic() {
		return accountBic;
	}

	@Override
	public String toString() {
		return """
				
				Bank Account info:
				Account Name: %s
				IBAN: %s
				BIC: %s
				
				""".formatted(accountName, accountIban, accountBic);
	}
	
	
	
	
}
