package section1_recipe4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import section1_recipe4.Result;
import section1_recipe4.Task;

public class Main {
	public static void main(String[] args){
		
		// Create an executor
		ExecutorService executor=(ExecutorService)Executors.newCachedThreadPool();
		
		// Create three tasks and stores them in a List
		List<Task> taskList=new ArrayList<>();
		for (int i=0; i<10; i++){
			Task task=new Task("Task-"+i);
			taskList.add(task);
		}
		
		List<Future<Result>>resultList=null;
		
		try {
			resultList=executor.invokeAll(taskList);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		executor.shutdown();
		
		// Writes the results to the console
		System.out.printf("Core: Printing the results\n");
		for (int i=0; i<resultList.size(); i++){
			Future<Result> future=resultList.get(i);
			try {
				Result result=future.get();
				System.out.printf("%s: %s\n",result.getName(),result.getValue());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			} 
		}

		
	}

}
