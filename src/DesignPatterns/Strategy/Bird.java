package DesignPatterns.Strategy;

/**
 * @version Sept 30, 2013
 */
public class Bird extends Animal {
    public void digHole() {
	System.out.println("Just finished digging a hole for my bone. I am "
		+ "going to bark at you for no reason now!");
    }

    public Bird(String name, String species) {
	super(name, species);

	this.flyingAbility = new CanFly(); // <== ONLY DIFFERENCE BETWEEN DOG
					   // besides class name
    }
}
