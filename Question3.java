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

	public Car(String name, int wheels, int doors, int seats, int maxSpeed) {
		this.name = name;
		this.wheels = wheels;
		this.doors = doors;
		this.seats = seats;
		this.maxSpeed = maxSpeed;

	}

	public void info() {
		System.out.println("This is all characteristics of " + name + ":");
		System.out.println("wheels = " + wheels + "\n" + "doors = " + doors + "\n" + "seats = " + seats + "\n"
				+ "maxSpeed = " + maxSpeed);
		System.out.println("--------------------------------------");
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {

			System.out.println(maxSpeed);
		}
	}

}

class Toyota extends Car {
	public Toyota(int wheels, int doors, int seats) {
		super("Toyota", wheels, doors, seats, 100);

	}

}

class BMW extends Car {
	public BMW(int wheels, int doors, int seats) {
		super("BMW", wheels, doors, seats, 200);

	}
}

class Factory {

	private Car toyota = new Toyota(4, 4, 5);
	private Car bmw = new BMW(4, 4, 5);

	public void infoCar() {
		toyota.info();
		bmw.info();
	}

	public void runCar() throws InterruptedException {
		Thread t1 = new Thread(toyota);
		Thread t2 = new Thread(bmw);
		t1.start();
		t1.join();
		t2.start();
	}
}
