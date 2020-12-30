package section3_recipe3;

public class Counter {
	
	private String value;
	private int counter;
	
	public Counter() {
		counter=1;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public void increment() {
		counter++;
	}

}
