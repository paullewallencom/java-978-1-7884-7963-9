package section4_recipe8;

import java.util.concurrent.atomic.AtomicIntegerArray;

import section4_recipe8.Decrementer;
import section4_recipe8.Incrementer;


public class Main {
	public static void main(String[] args) {
		
		final int THREADS=100;
		AtomicIntegerArray vector=new AtomicIntegerArray(1000);
		
		Incrementer incrementer=new Incrementer(vector);
		
		Decrementer decrementer=new Decrementer(vector);
		
		Thread threadIncrementer[]=new Thread[THREADS];
		Thread threadDecrementer[]=new Thread[THREADS];
		
		for (int i=0; i<THREADS; i++) {
			threadIncrementer[i]=new Thread(incrementer);
			threadDecrementer[i]=new Thread(decrementer);
			
			threadIncrementer[i].start();
			threadDecrementer[i].start();
		}
		
		for (int i=0; i<THREADS; i++) {
			try {
				threadIncrementer[i].join();
				threadDecrementer[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		int errors=0;
		for (int i=0; i<vector.length(); i++) {
			if (vector.get(i)!=0) {
				System.out.println("Vector["+i+"] : "+vector.get(i));
				errors++;
			}
		}
		
		if (errors==0) {
			System.out.printf("No errors found\n");
		}
		
		System.out.println("Main: End of the example");

		
	}

}
