package section4_recipe7;

import section4_recipe7.Account;
import section4_recipe7.Bank;
import section4_recipe7.Company;

public class Main {
	public static void main(String[] args) {
		
		// Creates a new account
		Account	account=new Account();
		// an initialize its balance to 1000
		account.setBalance(1000);
		
		// Creates a new Company and a Thread to run its task
		Company	company=new Company(account);
		Thread companyThread=new Thread(company);
		// Creates a new Bank and a Thread to run its task
		Bank bank=new Bank(account);
		Thread bankThread=new Thread(bank);
		
		// Prints the initial balance
		System.out.printf("Account : Initial Balance: %d\n",account.getBalance());
		
		// Starts the Threads
		companyThread.start();
		bankThread.start();
		
		try {
			// Wait for the finalization of the Threads
			companyThread.join();
			bankThread.join();
			// Print the final balance
			System.out.printf("Account : Final Balance: %d\n",account.getBalance());
			System.out.printf("Account : Number of Operations: %d\n", account.getOperations());
			System.out.printf("Account : Accumulated commisions: %f\n", account.getCommission());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
