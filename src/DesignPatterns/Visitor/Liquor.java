package DesignPatterns.Visitor;

/**
 * @version Sept 29, 2013
 */
public class Liquor implements Visitable {
    private double price;

    public Liquor(double price) {
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
