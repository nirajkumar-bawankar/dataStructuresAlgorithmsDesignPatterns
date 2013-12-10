// Keeps track of size of tree, used to know when to handle
// root node cases
static int size;
// Used to know when to increment size, true when node is added to true
// false when node was not added, as it already exists in the tree. 
static bool modified;

// Creates an empty binary tree
template<class T> binTree<T>::binTree() {
    size = 0;
    modified = false;
}

// Cleans up memory before the tree's destruction
template<class T> binTree<T>::~binTree() {
   destruct(root);
}

// Inserts the value into the appropriate place in the tree.
// this function will use helper function insert(T val, node* n) to insert a value.
template<class T> void binTree<T>::insert(T val) {
    insert(val, root);
}

// Helper function for insert that takes a node argument.
// You should find the node n which the new node will be added to that.
// Then add new node to node n
template<class T> void binTree<T>::insert(T val, node* n) {
    if (size == 0) {
        // Tree is empty, create a new root node with the given val
        // set the empty boolean to false.
        root = new node(val, NULL, NULL);
        modified = true;
    } else if (n == NULL) {
        // If node does not exist create one
        n = new node(val, NULL, NULL);
        modified = true;
    } else if (val < n->val) {
        // If the left branch is null, create a node
        // other wise recurssively call insert on 
        // the left branch to find the values correct spot
        if (n->left == NULL) {
            n->left = new node(val, NULL, NULL);
            modified = true;
        } else {
            insert(val, n->left);
        }
    } else if (val > n->val) {
        // If the right branch is null, create a node
        // otherwise reucrissvely call insert on the
        // right branch to find the value's correct spot
        if (n->right == NULL) {
            n->right = new node(val, NULL, NULL);
            modified = true;
        } else {
            insert(val, n->right);
        }
    }
    // If modified flag has been set, increment
    // the size of the binary tree and reset flag
    if (modified) {
        size++;
        modified = false;
    }
}

// Finds the v in the tree such that v == val. It's assumed that
// for the generic type being used that if both v < val and v > val
// are false, then v == val is true.  Once v is found, it is returned.
// If no matching value is found in the tree, not_found is thrown.
// You should throw not_found in this way: 
// throw not_found();
template<class T> T binTree<T>::find(T val) const {
	return find(val, root);
}

// Helper function for find that accepts a node argument.
// The interface function find(T val) uses this function to find the val
// You should find the node n which which contains value val.
// Then return value val if it was not found throw not_found by:
// throw not_found();
template<class T> T binTree<T>::find(T val, node* n) const {
	if (n == NULL) {
        // Value to be found is not in tree
        throw not_found();
    } else if (val < n->val) {
        // Value to be found is less than the current node
        return find(val, n->left);
    } else if (val > n->val) {
        // Value to be found is greater than the current node
        return find(val, n->right);
    } 
    // Value is found, return it
    return n->val;
}

// Helper function to find the maximum value of the current subtree
template<class T> T binTree<T>::maximum(node* n) {
    if (n->right == NULL) {
        return n->val;
    } else {
        return maximum(n->right);
    }
}

// Removes a value from the tree.  Works identically to find (and operates
// with the same assumptions), except that it removes the value from the
// tree in addition to returning it.
template<class T> T binTree<T>::remove(T val) {
	return remove(val, root, NULL);
}

// Helper function to remove that accepts a node argument.
template<class T> T binTree<T>::remove(T val, node* n, node* parent) {
	if (n == NULL) {
        // Value to be removed not found;
        throw not_found();
    } else if (val < n->val) {
        // Value is less than the current node
        return remove(val, n->left, n);
    } else if (val > n->val) {
        // Value is greater than the current node
        return remove(val, n->right, n);
    } else { 
        // Value to be removed found
        // Store the value for returing.
        T found_value = n->val;
        if (n->left != NULL && n->right != NULL) {
            // Case 1: left node NOT NULL and right node NOT NULL.
            // Find the maximum value of the left subtree to
            // replace the node being removed. Remove the old
            // node containing the maximum value.
            //
            // We are replacing the value of the root node so
            // we do not need to delete the node
            T maxValue = maximum(n->left);
            remove(maxValue, n->left, n);
            n->val = maxValue;
        } else { 
            if (n->left == NULL && n->right != NULL) {
                // Case 2: left node NULL and right node NOT NULL.
                // Replace the parent's branch to point to the 
                // current nodes right branch. If parent is null 
                // then we are at the root node, just replace
                // the root with the right branch of the root.
                if (parent != NULL) {
                    if (parent->left == n) {
                        parent->left = n->right;
                    } else if (parent->right == n) {
                        parent->right = n->right;
                    }
                } else {
                    root = n->right;
                }
            } else if (n->right == NULL && n->left != NULL) {
                // Case 3: left node NOT NULL and right node NULL.
                // Replace the parent's branch to point to the 
                // current nodes left branch. if the parent is null
                // then we are at the root node, just replace the
                // root with the left branch of the root.
                if (parent != NULL) {
                    if (parent->left == n) {
                        parent->left = n->left;
                    } else if (parent->right == n) {
                        parent->right = n->left;
                    }
                } else {
                    root = n->left;
                }
            } else if (n->left == NULL && n->right == NULL) {
                // Case 4: left and right node are NULL.
                // If parent is not null then set parents branch
                // that is pointing to this node to null. If
                // parent is null then we are at the root
                // so set the root to null.
                if (parent != NULL && parent->right == n) {
                    parent->right = NULL;
                } else if (parent != NULL && parent->left == n) {
                    parent->left = NULL;
                } else if (parent == NULL) {
                    root = NULL;
                }
            }
            // Delete the node that was removed to free memory
            delete n;
        }
        
        // Return the value found and decrement size
        size--;
        return found_value;
    }
}
// Helper function for the destructor that recursively deletes all
// nodes in the tree.
template<class T> void binTree<T>::destruct(node* n) {
   // If the node is null there is nothing to do.
   if (n == NULL) { 
        return; 
   }
   // Else keep traversing children to delete nodes
   else {
       destruct(n->left);
       destruct(n->right);
   }
   // Once we have traversed to the bottom of the tree.
   // Start deleting nodes while we return from recurssive
   // calls. 
   delete n;
   size--;
}
