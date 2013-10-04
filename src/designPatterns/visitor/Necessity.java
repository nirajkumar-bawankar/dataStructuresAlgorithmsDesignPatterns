package designPatterns.visitor;

/**
 * @version Sept 29, 2013
 */
public class Necessity implements Visitable {
    private double price;

    public Necessity(double price) {
	this.price = price;
    }
    public double getPrice() {
	return this.price;
    }

    @Override
    public double accept(Visitor visitor) {
	return visitor.visit(this);
    }
}
