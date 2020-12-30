package section4_recipe10;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

import section4_recipe10.Account;

public class Decrementer implements Runnable {
	
	private Account account;
	
	public Decrementer(Account account) {
		this.account=account;
	}
	
	@Override
	public void run() {
		VarHandle handler;
		try {
			handler = MethodHandles.lookup().in(Account.class).findVarHandle(Account.class, "amount", double.class);
			for (int i = 0; i < 10000; i++) {
				handler.getAndAdd(account, -100);
				account.unsafeAmount -= 100;
			}
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
