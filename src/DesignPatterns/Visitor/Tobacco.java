package DesignPatterns.Visitor;

/**
 * @version Sept 29, 2013
 */
public class Tobacco implements Visitable {
    private double price;

    public Tobacco(double price) {
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
