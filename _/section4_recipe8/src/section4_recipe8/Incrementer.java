package section4_recipe8;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class Incrementer implements Runnable {
	
	private final AtomicIntegerArray vector;
	
	public Incrementer(AtomicIntegerArray vector) {
		this.vector=vector;
	}
	
	@Override
	public void run() {
		for (int i=0; i<vector.length(); i++){
			vector.getAndIncrement(i);
		}
	}

}
