package dataStructures.visitor;

import designPatterns.visitor.Liquor;
import designPatterns.visitor.Necessity;
import designPatterns.visitor.TaxHolidayVisitor;
import designPatterns.visitor.TaxVisitor;
import designPatterns.visitor.Tobacco;

/**
 * @version Sept 29, 2013
 */
public class VisitorTest {
    public static void main(String[] args) {
	TaxVisitor taxVisitor = new TaxVisitor();
	TaxHolidayVisitor taxHolidayVisitor = new TaxHolidayVisitor();

	Necessity milk = new Necessity(3.47);
	Liquor vodka = new Liquor(11.99);
	Tobacco cigars = new Tobacco(19.99);

	System.out.println("TAX PRICES");

	System.out.println(milk.accept(taxVisitor) + "\n");
	System.out.println(vodka.accept(taxVisitor) + "\n");
	System.out.println(cigars.accept(taxVisitor) + "\n");

	System.out.println("\nTAX HOLIDAY PRICES");

	System.out.println(milk.accept(taxHolidayVisitor) + "\n");
	System.out.println(vodka.accept(taxHolidayVisitor) + "\n");
	System.out.println(cigars.accept(taxHolidayVisitor) + "\n");
    }
}
