public class Main {
	public static void main(String[] args) {
		Factory factory = new Factory();
		factory.infoCar();
		try {
			factory.runCar();
		} catch (InterruptedException ex) {}
	}
}

abstract class Car implements Runnable {
	private int wheels;
	private int doors;
	private int seats;
	private int maxSpeed;
	private String name;
	protected int flag;
	private TrafficBlock block;

	public Car(String name, int wheels, int doors, int seats, int maxSpeed, TrafficBlock block) {
		this.name = name;
		this.wheels = wheels;
		this.doors = doors;
		this.seats = seats;
		this.maxSpeed = maxSpeed;
		this.block = block;
	}

	public void info() {
		System.out.println("This is all characteristics of " + name + ":");
		System.out.println("wheels = " + wheels + "\n" + "doors = " + doors + "\n" + "seats = " + seats + "\n"
				+ "maxSpeed = " + maxSpeed);
		System.out.println("--------------------------------------");
	}

	@Override
	public void run() {
		try {
			synchronized (block) {
				for (int i = 0; i < 10; i++) {
					while (block.flag == flag) {
						System.out.println(maxSpeed);
						block.wait();
					}
					block.flag = (block.flag + 1) % 2;
					block.notifyAll();
				}
			}
		} catch (Exception e) {
		}
	}

}

class Toyota extends Car {
	public Toyota(int wheels, int doors, int seats, TrafficBlock block) {
		super("Toyota", wheels, doors, seats, 100, block);
		flag = 0;
	}

}

class BMW extends Car {
	public BMW(int wheels, int doors, int seats, TrafficBlock block) {
		super("BMW", wheels, doors, seats, 200, block);
		flag = 1;
	}
}

class TrafficBlock {
	int flag = 0;
}

class Factory {
	private TrafficBlock block = new TrafficBlock();
	private Car toyota = new Toyota(4, 4, 5, block);
	private Car bmw = new BMW(4, 4, 5, block);

	public void infoCar() {
		toyota.info();
		bmw.info();
	}

	public void runCar() throws InterruptedException {
		Thread t1 = new Thread(toyota);
		Thread t2 = new Thread(bmw);
		t1.start();
		t2.start();
	}
}
