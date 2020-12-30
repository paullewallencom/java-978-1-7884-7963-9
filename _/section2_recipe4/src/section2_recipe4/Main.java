package section2_recipe4;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import section2_recipe4.Task;

public class Main {
	public static void main(String[] args){
		
		int array[]=new int[100];
		
		Task task=new Task(array,0,100);
		
		ForkJoinPool pool=new ForkJoinPool();
		
		pool.execute(task);
		
		pool.shutdown();
		
		// Wait for the finalization of the task
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (task.isCompletedAbnormally()) {
			System.out.printf("Main: An exception has ocurred\n");
			System.out.printf("Main: %s\n",task.getException());
		}
		
		System.out.printf("Main: Result: %d",task.join());
		
	}

}
