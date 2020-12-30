package section1_recipe7;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import section1_recipe7.Task;


public class Main {
	public static void main(String[] args) {
		
		ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newCachedThreadPool();
		
		Task task=new Task();
		
		System.out.printf("Main: Executing the task\n");
		Future<String> result=executor.submit(task);
		
		// Sleep during two seconds
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.printf("Main: Cancelling the task\n");
		result.cancel(true);
		
		// Verify that the task has been cancelled
		System.out.printf("Main: Cancelled: %s\n",result.isCancelled());
		System.out.printf("Main: Done: %s\n",result.isDone());
		
		executor.shutdown();
		System.out.printf("Main: The executor has finished\n");
		
	}

}
