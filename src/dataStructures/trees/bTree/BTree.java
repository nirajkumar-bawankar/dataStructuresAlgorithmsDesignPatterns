package dataStructures.trees.bTree;

/**
 * B trees are the standard file organization for applications requiring
 * insertion, deletion, and key range searches. They are used to implement most
 * modern file systems.
 *
 * A B tree has the following properties:
 * 1) B trees are always height balanced, with all leaf nodes at the same level.
 * 2) Update and search operations affect only a few disk blocks. The fewer
 * the # of disk blocks affected, the less disk I/O is required.
 * 3) B trees keep related records(with similar key values) on the same disk
 * block, which helps to minimize disk I/O on searches due to locality of
 * reference.
 * 4) B trees guarantee that every node in the tree will be full at least to
 * a certain minimum percentage. This improves space efficienty while
 * reducing the typical number of disk fetches necessary during a search or
 * update operation.
 *
 * A B tree of order m is defined to have the following shape properties:
 * NOTE: A B tree is a generalization of the 2-3 tree. A 2-3 tree is a B-tree
 * of order 3.
 * 1) The root is either a leaf or has at least 2 children.
 * 2) Each internal node, except for the root, has between m/2 and m children.
 * 3) All leaves are at the same level in the tree, so the tree is always
 * height balanced.
 *
 * To learn more about the B tree data structure please visit:
 * http://algoviz.org/OpenDSA/Books/CS3114PM/html/BTree.html
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Dec 12, 2013
 */
public class BTree {

}
