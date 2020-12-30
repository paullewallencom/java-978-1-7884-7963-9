package section2_recipe1;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import section2_recipe1.Product;
import section2_recipe1.ProductListGenerator;
import section2_recipe1.Task;

public class Main {
	public static void main(String[] args) {
		
		// Create a list of products
		ProductListGenerator generator=new ProductListGenerator();
		List<Product> products=generator.generate(10000);
		
		Task task=new Task(products, 0, products.size(), 0.20);
		
		ForkJoinPool pool=new ForkJoinPool();
		
		pool.execute(task);
		
		// Write information about the pool
		do {
			System.out.printf("******************************************\n");
			System.out.printf("Main: Parallelism: %d\n",pool.getParallelism());
			System.out.printf("Main: Active Threads: %d\n",pool.getActiveThreadCount());
			System.out.printf("Main: Task Count: %d\n",pool.getQueuedTaskCount());
			System.out.printf("Main: Steal Count: %d\n",pool.getStealCount());
			System.out.printf("******************************************\n");
			try {
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!task.isDone());
		
		pool.shutdown();
		
		// Check if the task has completed normally
		if (task.isCompletedNormally()){
			System.out.printf("Main: The process has completed normally.\n");
		}
		
		// Expected result: 12. Write products which price is not 12
		for (int i=0; i<products.size(); i++){
			Product product=products.get(i);
			if (product.getPrice()!=12) {
				System.out.printf("Product %s: %f\n",product.getName(),product.getPrice());
			}
		}
		
		System.out.printf("Main: End of the Program.\n");		
	}

}
