package assigment2;



import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bank {

	//Instance variables should be accessible only inside de class
	private String bankName="DefaultBank01&";
	public static enum BranchLocations {
		Meyer1,Meyer2,Meyer3,Meyer4,Meyer5
	};
	BranchLocations branchLocations= BranchLocations.Meyer2;
	private List<Account> accounts;


	public Bank() {}
	public Bank(String bankName, String branchLocation) {
		setBankName(bankName);
		setBranchLocation(branchLocation);
		this.accounts=new ArrayList<>();
	}
	public Bank(String bankName, BranchLocations branchLocation) {
		setBankName(bankName);
		setBranchLocation(branchLocation);
		this.accounts=new ArrayList<>();
	}

	public String getBankName() {
		return bankName;
	}
	public boolean setBankName(String bankName) {
		String regex="[a-zA-Z]*[0-9]{1,3}[&]*[ ]?";

		//Compilation of the regex pattern
		Pattern regExpre= Pattern.compile(regex);

		//Create the matcher with the bank name
		Matcher matcher= regExpre.matcher(bankName);

		// Check if the user account name has minimum 8 characters
		if(matcher.find() && bankName.length() >=8 ){
			this.bankName=bankName;
			return true;
		}
		else{
			System.out.println("Invalid bank name, please enter a valid name");
			return false;
		}

	}
	public boolean setBranchLocation(String branchLocation) {
		//Using Switch to validate the branch location
		switch (branchLocation.toUpperCase()){
			case "MEYER1":
				this.branchLocations= BranchLocations.Meyer1;
				return true;
			case "MEYER2":
				this.branchLocations=BranchLocations.Meyer2;
				return true;
			case "MEYER3":
				this.branchLocations=BranchLocations.Meyer3;
				return true;
			case "MEYER4":
				this.branchLocations=BranchLocations.Meyer4;
				return true;
			case "MEYER5":
				this.branchLocations=BranchLocations.Meyer5;
				return true;
			default:
				System.out.println("Invalid branch location, the default branch location will be " +
						"consider");
				return false;
		}

	}
	public String getBranchLocation() {
		return branchLocations.toString();
	}


	public boolean setBranchLocation(BranchLocations branchLocation) {
		this.branchLocations=branchLocation;
		return true;
	}
	public Account getAccountByNumber(int accountNumber) {
		//Search for an account by the account number
		for (Account account:accounts){
			if(account.getAccountNumber()==accountNumber){
				return account;
			}
		}
		//if the account is not found, the return is an empty account
		return new Account();
	}
	
	public boolean addAccount(Account account) {
		//adding an account to the collection, the account number must be unique
		if (!accounts.contains(account)){
			accounts.add(account);
			return true;
		}
		else{
			System.out.println("The account number is invalid, account number must be unique. " +
					"Account number not added");
			return false;
		}

	}
	public boolean addAccount(String accountName, int accountNumber, double accountBalance) {
		//Creating a new Account instance with parameters
		Account newAccount= new Account(accountName,accountNumber,accountBalance);

		//Calling the first method to add the new account
		return addAccount(newAccount);
	}
	public Account viewAccount(int accountNumber) {
		//search an account by the account number
		for (Account account:accounts){
			if(account.getAccountNumber()==accountNumber){
				return	account;
			}
		}
		//If the account is not found, an empty object is returned
		return new Account();
	}
	public Account viewAccount(byte index) {
		//Checking if the index is inside the valid range
		if(index >=0 && index < accounts.size()){
			// If it is, return the account based on the index number
			return accounts.get(index);
		}
		else{
			System.out.println("Invalid index, account not encountered");
			return new Account();
		}

	}
	public boolean modifyAccount(int accountNumber, String accountName) {
	// Searching the account by account number
		Account accountToModify=getAccountByNumber(accountNumber);

		// Check if the account exists
		if(!accountToModify.equals(new Account())){
			//Try to modify the account name
			if(accountToModify.setAccountName(accountName)){
				System.out.println("Account modified successfully");
				return true;
			}
			else{
				System.out.println("Account not modify, double check the new name");
				return false;
			}
		}
		else{
			// The account was not found
			System.out.println("Account not found");
			return false;
		}
	}

	public boolean modifyAccount(int accountNumber, double accountBalance) {
		// Searching the account by account number
		Account accountToModify=getAccountByNumber(accountNumber);
		// Check if the account exists
		if(!accountToModify.equals(new Account())){
			//Try to modify the account name
			if(accountToModify.setAccountBalance(accountBalance)){
				System.out.println("Account modified successfully");
				return true;
			}
			else{
				System.out.println("Account not modify, double check the account balance");
				return false;
			}
		}
		else{
			// The account was not found
			System.out.println("Account not found");
			return false;
		}

	}
	public boolean modifyAccount(int accountNumber, String accountName, double accountBalance) {

		// Searching the account by account number
		Account accountToModify=getAccountByNumber(accountNumber);

		// Check if the account exists
		if(!accountToModify.equals(new Account())){
			//Try to modify the account name
			if(accountToModify.setAccountName(accountName)){
				//Try to modify the account balance
				if(accountToModify.setAccountBalance(accountBalance)){
					System.out.println("Account modified successfully");
					return true;
				}else{
					//Reverse the name modification if account balance modification failed
					accountToModify.setAccountName(accountToModify.getAccountName());
					System.out.println("Failed to modify the account. Invalid account balance");
					return false;
				}
			}
			else{
				System.out.println("Failed to modify the account. Invalid account name");
				return false;
			}
		}
		else{
			// The account was not found
			System.out.println("Account not found");
			return false;
		}
	}

	public boolean modifyAccount(byte index, String accountName) {
		//Check if the index is inside the range
		if (index >=0 && index < accounts.size()){
				//Get the account by the index
				Account accountToModify= accounts.get(index);

				if (accountToModify.setAccountName(accountName)) {
					System.out.println("Account modified successfully.");
					return true;
				}else {
					System.out.println("Failed to modify account. Invalid accoun name");
					return false;
				}
		}else{
			// in case the index is invalid
			System.out.println("Invalid Index");
			return false;
		}
	}
	public boolean modifyAccount(byte index, double accountBalance) {
		//Check if the index is inside the range
		if (index >=0 && index < accounts.size()){
			//Get the account by the index
			Account accountToModify= accounts.get(index);
			//Check if the new balance is valid
			if(accountToModify.setAccountBalance(accountBalance)){
				//Change the account balance value
				accountToModify.setAccountBalance(accountBalance);
				System.out.println("Account balance modified successfully.");
				return true;
			}
			else{
				System.out.println("Invalid account balance. Balance not modified.");
				return false;
			}
		}
		else{
			System.out.println("Invalid index. Account balance not modified.");
			return false;
		}

	}
	public boolean modifyAccount(byte index, String accountName, double accountBalance) {
		//Check if the index is inside the range
		if (index >=0 && index < accounts.size()){
			//Get the account based ond the index
			Account accountToModify=accounts.get(index);

			//check if the new name is valid
			boolean isNameValid=accountToModify.setAccountName(accountName);

			//Check if the new balance is valid
			boolean isBalanceValid=accountToModify.setAccountBalance(accountBalance);

			//If the name and the balance is valid, modify the account and return true
			if(isNameValid && isBalanceValid){
				System.out.println("Account name and balance modified successfully.");
				return true;
			}else{
				//In case one of them is invalid, print the message and return false
				System.out.println("Invalid account name or balance. Account not modified.");
				return false;
			}
		}
		else {
			System.out.println("Invalid index. Account not modified.");
			return false;
		}
	}
	public boolean deleteAccount(int accountNumber) {
		//Search the account by the number of account
		Account accountToDelete=getAccountByNumber(accountNumber);

		//Check if the account exists
		if(!accountToDelete.equals(new Account())){
			//Delete the account if this is encountered
			accounts.remove(accountToDelete);
			System.out.println("Account deleted sucessfully");
			return true;
		}
		else{
			//If the account is not found
			System.out.println("Account not found");
			return false;
		}
	}
	public boolean deleteAccount(byte index) {
		//Check if the index is inside the range
		if (index >=0 && index < accounts.size()){
			//Delete the account by the index
			accounts.remove(index);
			System.out.println("Account deleted sucessfully");
			return true;
		}
		else{
			//If the index is invalid
			System.out.println("Invalid index. Account not deleted");
			return false;
		}
	}
	
	
}
