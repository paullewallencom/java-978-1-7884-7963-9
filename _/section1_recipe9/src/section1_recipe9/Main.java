package section1_recipe9;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import section1_recipe9.ReportGenerator;
import section1_recipe9.ReportProcessor;
import section1_recipe9.ReportRequest;

public class Main {
	public static void main(String[] args) {
		
		// Create the executor and thee CompletionService using that executor
		ExecutorService executor=Executors.newCachedThreadPool();
		
		CompletionService<String> service=new ExecutorCompletionService<>(executor);
		
		// Crete two ReportRequest objects and two Threads to execute them
		ReportRequest faceRequest=new ReportRequest("Face", service);
		ReportRequest onlineRequest=new ReportRequest("Online", service);
		Thread faceThread=new Thread(faceRequest);
		Thread onlineThread=new Thread(onlineRequest);
		
		// Create a ReportSender object and a Thread to execute  it
		ReportProcessor processor=new ReportProcessor(service);
		Thread senderThread=new Thread(processor);
		
		// Start the Threads
		System.out.printf("Main: Starting the Threads\n");
		faceThread.start();
		onlineThread.start();
		senderThread.start();
		
		// Wait for the end of the ReportGenerator tasks
		try {
			System.out.printf("Main: Waiting for the report generators.\n");
			faceThread.join();
			onlineThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Shutdown the executor
		System.out.printf("Main: Shuting down the executor.\n");
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		processor.stopProcessing();
		System.out.printf("Main: Ends");
		
	}

}
