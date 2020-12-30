package section4_recipe8;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class Decrementer implements Runnable {
	
	private AtomicIntegerArray vector;
	
	public Decrementer(AtomicIntegerArray vector) {
		this.vector=vector;
	}
	
	@Override
	public void run() {
		for (int i=0; i<vector.length(); i++) {
			vector.getAndDecrement(i);
		}	
	}

}
