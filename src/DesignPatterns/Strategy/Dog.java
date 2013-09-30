package DesignPatterns.Strategy;

/**
 * @version Sept 30, 2013
 */
public class Dog extends Animal {
    public void digHole() {
	System.out.println("Just finished digging a hole for my bone. I am "
		+ "going to bark at you for no reason now!");
    }

    public Dog(String name, String species) {
	super(name, species);

	this.flyingAbility = new CantFly();
    }
}
