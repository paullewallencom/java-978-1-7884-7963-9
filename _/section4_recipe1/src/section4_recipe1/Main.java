package section4_recipe1;

import java.util.concurrent.ConcurrentLinkedDeque;

import section4_recipe1.AddTask;
import section4_recipe1.PollTask;

public class Main {
	public static void main(String[] args) {
		
		ConcurrentLinkedDeque<String> list=new ConcurrentLinkedDeque<>();
		
		Thread threads[]=new Thread[100];
		
		// Create 100 AddTask objects and execute them as threads
		for (int i = 0; i < threads.length; i++) {
			AddTask task = new AddTask(list);
			threads[i] = new Thread(task);
			threads[i].start();
		}
		System.out.printf("Main: %d AddTask threads have been launched\n", threads.length);
		
		// Wait for the finalization of the threads
		for (int i = 0; i < threads.length; i++) {
		try {
			threads[i].join();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		}
		
		System.out.printf("Main: Size of the List: %d\n", list.size());
		
		// Create 100 PollTask objects and execute them as threads
		for (int i = 0; i < threads.length; i++) {
			PollTask task = new PollTask(list);
			threads[i] = new Thread(task);
			threads[i].start();
		}
		System.out.printf("Main: %d PollTask threads have been launched\n", threads.length);
		
		// Wait for the finalization of the threads
		for (int i = 0; i < threads.length; i++) {
		try {
			threads[i].join();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		}
		
		System.out.printf("Main: Size of the List: %d\n", list.size());		

	}

}
