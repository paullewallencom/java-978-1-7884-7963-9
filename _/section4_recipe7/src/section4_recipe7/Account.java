package section4_recipe7;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class Account {
	
	private final AtomicLong balance;
	private final LongAdder operations;
	private final DoubleAccumulator commission;
	
	public Account() {
		balance = new AtomicLong();
		operations = new LongAdder();
		commission = new DoubleAccumulator((x,y)-> x+y*0.2, 0);
	}
	
	public long getBalance() {
		 return balance.get();
	 }
	 
	public long getOperations() {
		return operations.longValue();
	}

	public double getCommission() {
		return commission.get();
	}
	
	public void setBalance(long balance) {
		this.balance.set(balance);
		operations.reset();
		commission.reset();
	}
	
	public void addAmount(long amount) {
		this.balance.getAndAdd(amount);
		this.operations.increment();
		this.commission.accumulate(amount);
	}
	
	public void subtractAmount(long amount) {
		this.balance.getAndAdd(-amount);
		this.operations.increment();
		this.commission.accumulate(amount);
	}

}
