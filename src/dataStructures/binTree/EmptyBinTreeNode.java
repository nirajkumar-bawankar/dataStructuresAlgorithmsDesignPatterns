package dataStructures.binTree;

/**
 * If your program always will need an instance of a class or the cost of
 * creating an instance is not too large then the programmer should use this
 * eager initialization.
 *
 * The class is the singleton design pattern of empty bin tree node.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 9, 2013
 */
public class EmptyBinTreeNode extends BinTreeNode {
    private static final EmptyBinTreeNode emptyBinTreeNode = new EmptyBinTreeNode();

    private EmptyBinTreeNode() {
	// initialize nothing
    }

    public static EmptyBinTreeNode getInstance() {
	return emptyBinTreeNode;
    }
}
