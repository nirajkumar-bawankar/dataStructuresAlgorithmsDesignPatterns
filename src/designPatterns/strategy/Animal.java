package designPatterns.strategy;

/**
 * @version Sept 30, 2013
 */
public class Animal {

    private String name;
    private String species;

    public Flys flyingAbility; // composition with the correct ability

    public Animal(String name, String species) {
	this.name = name;
	this.species = species;
    }

    public String getName() {
	return this.name;
    }

    public String getSpecies() {
	return this.species;
    }

    public String tryToFly() {
	return this.flyingAbility.fly();
    }

    public void setFlyingAbility(Flys newFlyingAbility) {
	this.flyingAbility = newFlyingAbility;
    }
}
