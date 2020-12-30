package section1_recipe6;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import section1_recipe6.Task;

public class Main {
	
	public static void main(String[] args) {
		
		// Create a ScheduledThreadPoolExecutor
		ScheduledExecutorService executor=Executors.newScheduledThreadPool(1);
		
		System.out.printf("Main: Starting at: %s\n",new Date());
		
		Task task=new Task("Task");
		
		ScheduledFuture<?> result=executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);
		
		for(int i=0; i<10; i++) {
			System.out.printf("Main: Delay: %d\n",result.getDelay(TimeUnit.MILLISECONDS));
			
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		executor.shutdown();
		
		// Verify that the periodic tasks no is in execution after the executor shutdown()
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.printf("Main: Finished at: %s\n",new Date());
		
	}

}
