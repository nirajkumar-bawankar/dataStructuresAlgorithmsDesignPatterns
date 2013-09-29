package DesignPatterns.Visitor;

/**
 * @version Sept 29, 2013
 */
public interface Visitable {
    public double accept(Visitor visitor);
}
