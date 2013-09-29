package DesignPatterns.Visitor;

/**
 * @version Sept 29, 2013
 */
public interface Visitor {
    public double visit(Liquor liquorItem);
    public double visit(Tobacco tobaccoItem);
    public double visit(Necessity necessityItem);
}
