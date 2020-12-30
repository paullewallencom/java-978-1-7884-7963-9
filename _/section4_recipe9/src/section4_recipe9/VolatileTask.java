package section4_recipe9;

import java.util.Date;

import section4_recipe9.VolatileFlag;

public class VolatileTask implements Runnable {
	
	private VolatileFlag flag;
	
	public VolatileTask(VolatileFlag flag) {
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
