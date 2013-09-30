package DesignPatterns.Strategy;

/**
 * @version Sept 30, 2013
 */
public class StrategyDesignPatternTest {
    public static void main(String[] args) {
	Animal dog = new Dog("Clifford", "Fictional");
	Animal bird = new Bird("Twitter", "Blue Jay");

	System.out.println("Clifford the dog says: " + "\"" + dog.tryToFly()
		+ "\"");
	System.out.println("Twitter the bird says: " + "\"" + bird.tryToFly()
		+ "\"");
    }
}
