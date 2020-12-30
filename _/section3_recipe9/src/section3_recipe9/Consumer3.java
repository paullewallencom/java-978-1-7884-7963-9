package section3_recipe9;

import java.util.concurrent.Flow;

import section3_recipe9.Item;

public class Consumer3 implements Flow.Subscriber<Item> {
	
	@Override
	public void onComplete() {
		System.out.printf("%s: Consumer 3: Completed\n", Thread.currentThread().getName());
	}

	@Override
	public void onError(Throwable exception) {
		System.out.printf("%s: Consumer 3: Error\n", Thread.currentThread().getName());
		exception.printStackTrace(System.err);
	}
	
	@Override
	public void onNext(Item item) {
		System.out.printf("%s: Consumer 3: Item received\n", Thread.currentThread().getName());
		System.out.printf("%s: Consumer 3: %s\n", Thread.currentThread().getName(),item.getTitle());
		System.out.printf("%s: Consumer 3: %s\n", Thread.currentThread().getName(), item.getContent());
	}
	
	@Override
	public void onSubscribe(Flow.Subscription subscription) {
		System.out.printf("%s: Consumer 3: Subscription received\n", Thread.currentThread().getName());
		System.out.printf("%s: Consumer 3: Requested three items\n", Thread.currentThread().getName());
		subscription.request(3);
	}


}
