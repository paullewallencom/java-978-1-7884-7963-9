package section4_recipe4;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

import section4_recipe4.Event;
import section4_recipe4.Task;

public class Main {
	public static void main(String[] args) throws Exception{
		
		DelayQueue<Event> queue=new DelayQueue<>();
		
		Thread threads[]=new Thread[5];
		
		for (int i = 0; i < threads.length; i++) {
			Task task = new Task(i + 1, queue);
			threads[i] = new Thread(task);
		}

		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}

		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
		
		do {
			int counter = 0;
			Event event;
			do {
				event = queue.poll();
				if (event != null)
					counter++;
			} while (event != null);
			System.out.printf("At %s you have read %d events\n", new Date(), counter);
			TimeUnit.MILLISECONDS.sleep(500);
		} while (queue.size() > 0);
		
	}

}
