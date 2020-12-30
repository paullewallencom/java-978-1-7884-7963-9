package section4_recipe9;

import java.util.Date;

import section4_recipe9.Flag;

public class Task implements Runnable {
	
	private Flag flag;
	
	public Task(Flag flag) {
		this.flag=flag;
	}
	
	@Override
	public void run() {
		int i = 0;
		while (flag.flag) {
			i++;
		}
		System.out.printf("Task: %d - %s\n", i, new Date());
	}

}
