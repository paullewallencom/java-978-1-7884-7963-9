package section4_recipe6;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

import section4_recipe6.Operation;

public class HashFiller implements Runnable {
	
	private ConcurrentHashMap<String, ConcurrentLinkedDeque<Operation>> userHash;

	public HashFiller(ConcurrentHashMap<String, ConcurrentLinkedDeque<Operation>> userHash) {
		this.userHash = userHash;
	}
	
	@Override
	public void run() {
		Random randomGenerator = new Random();
		for (int i = 0; i < 100; i++) {
			Operation operation = new Operation();
			String user = "USER" + randomGenerator.nextInt(100);
			operation.setUser(user);
			String action = "OP" + randomGenerator.nextInt(10);
			operation.setOperation(action);
			operation.setTime(new Date());

			addOperationToHash(userHash, operation);
		}
	}
	
	private void addOperationToHash(ConcurrentHashMap<String, ConcurrentLinkedDeque<Operation>> userHash,Operation operation) {

	ConcurrentLinkedDeque<Operation> opList = userHash.computeIfAbsent(operation.getUser(), user -> new ConcurrentLinkedDeque<>());

	opList.add(operation);

	}

}
