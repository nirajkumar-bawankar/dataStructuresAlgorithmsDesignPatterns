package DesignPatterns.Strategy;

/**
 * @version Sept 30, 2013
 */
public interface Flys {
    String fly();
}

class CanFly implements Flys {
    public String fly() {
	return "Flying very fast in the wind. It feels amazing!";
    }
}

class CantFly implements Flys {
    public String fly() {
	return "Are you crazy?! I am staying on the ground";
    }
}
