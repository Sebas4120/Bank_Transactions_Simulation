package assigment2;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account {

	//Instance variables only accessible inside Account class
	private int accountNumber=1234567;
	private double accountBalance=0.0;

	private String accountName="Customer01";


	public Account() {

	}

	public Account(String accountName, int accountNumber, double accountBalance) {
		setAccountNumber(accountNumber);
		setAccountName(accountName);
		setAccountBalance(accountBalance);
	}
	public String getAccountName() {
		return accountName;
	}
	public boolean setAccountName(String accountName) {
		//Use of Regular Expressions
//		String regex="^[a-zA-Z]*[']?[ ]?[-]*[a-zA-Z]*$";
		String regex="^[a-zA-Z]*[']?[ ]?[-]*[a-zA-Z]*[']?[ ]?[-]*[a-zA-Z]*$";

		//Compilation of the regex pattern
		Pattern regExpre= Pattern.compile(regex);
		//Create the matcher with the user account name
		Matcher matcher= regExpre.matcher(accountName);

		// Check if the user account name has minimum 4 characters
		if(matcher.find() && accountName.length() >=4 ){
			this.accountName=accountName;
			return true;
		}
		else{
			System.out.println("Invalid account name, please enter a valid name");
			return false;
		}
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public boolean setAccountNumber(int accountNumber) {
		//Using RegEx to set the pattern of the account number
		String regex="\\d{5,9}";
		String acountNumberString=Integer.toString(accountNumber);

		//Checking if the account number has between 5 and 9 digits
		if (acountNumberString.matches(regex)){
			this.accountNumber=accountNumber;
			return true;
		}
		else{
			System.out.println("Invalid input, the following default account number" +
					" will be assigned:");
			return false;
		}
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public boolean setAccountBalance(double value) {
		//Using validatingBalance method to return True or false
		if(validatingBalance(value)){
			this.accountBalance=value;
            return true;
		}
		else{
			System.out.println("Invalid balance, please provide a correct balance");
			return false;
		}

	}

	private boolean validatingBalance(double number){
		//Using BigDecimal to check if the balance has 2 decimals
		BigDecimal balance = BigDecimal.valueOf(number);
		int scale = balance.scale();

		//Checking if the value has 2 decimals
		return scale<=2;
	}
	@Override
	public boolean equals(Object obj) {
		//First checking if the objects are the same reference
		if(this == obj){
			return true;
		}

		//Checking if the object is null or not an instance of Account
		if (obj == null || getClass() != obj.getClass()){
			return false;
		}

		//Covert the object to an Account
		Account otherAccount= (Account) obj;

		//Comparing the value of all fields
		return this.accountNumber== otherAccount.accountNumber &&
				Double.compare(otherAccount.accountBalance,this.accountBalance)==0 &&
				Objects.equals(this.accountName,otherAccount.accountName);

	}

	@Override
	public String toString() {
		return "Account{" +
				"accountNumber=" + accountNumber +
				", accountBalance=" + accountBalance +
				", accountName='" + accountName + '\'' +
				'}';
	}


}
