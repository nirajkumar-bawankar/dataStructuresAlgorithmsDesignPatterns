#include "run.h"
#include "binTree.h"

class pair {
 public:
	pair(int k, int v) : key(k), val(v) { }

	int key;
	int val;

	bool operator<(pair p) {
		return key < p.key;
	}

	bool operator>(pair p) {
		return key > p.key;
	}

	bool operator==(pair p) {
		return key == p.key;
	}
};

int main(int argc, char** argv) {
	{
		binTree<pair> tree;

//         tree.insert(pair(1, -1));
//         tree.insert(pair(2, -2));
//         CHECK(tree.find(pair(1, -1)).val == -1);

		int arr[] = {4, 2, 1, 3, 6, 5, 7};

		for(int i = 0; i < 7; ++i) {
			tree.insert(pair(arr[i], -arr[i]));
		}

		for(int i = 1; i <= 7; ++i) {
			CHECK(tree.find(pair(i, 0)).val == -i);
		}

		tree.remove(pair(4, 0));
		for(int i = 1; i <= 7; ++i) {
			if(i != 4) CHECK(tree.remove(pair(i, 0)).val == -i);
		}

		try {
			tree.remove(pair(1, 1));
			CHECK(false);
		} catch(not_found) {
			CHECK(true);
		}
	}

	{
		binTree<pair> tree;
		
		for(int i = 1; i <= 10; ++i) {
			tree.insert(pair(i, i+1));
		}
		
		for(int i = 5; i < 15; ++i) {
			try {
				pair p = tree.remove(pair(i, 0));
				if(i > 10) {
					CHECK(false);
				} else {
					CHECK(p.val == i + 1);
				}
			} catch(not_found) {
				CHECK(i > 10);
			}
		}
        
	}

	return 0;
}
