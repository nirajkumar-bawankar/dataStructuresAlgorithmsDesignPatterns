package algorithms;

/**
 * Tests all logic within class Sorting.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 5, 2013
 */
public class SortingTest extends junit.framework.TestCase {
    private Student[] students;

    public void setUp() {
	this.students = new Student[100];
	int ID = 100;
	for (int i = 0; i < this.students.length; i++) {
	    this.students[i] = new Student(ID + i);
	}

	// make the array of students unsorted
	for (int i = 0; i < this.students.length; i++) {
	    int randomNumberFrom0To9 = (int) (Math.random() * 10);
	    Sorting.swap(this.students, i, randomNumberFrom0To9);
	}
    }

    public void test_insertionSort() {
	// array is initially a random unsorted array
	Sorting.insertionSort(this.students);

	for (int i = 0; i < this.students.length; i++) {
	    assertEquals(100 + i, this.students[i].getID());
	}
    }

    public void test_selectionSort() {
	// array is initially a random unsorted array
	Sorting.selectionSort(this.students);

	for (int i = 0; i < this.students.length; i++) {
	    assertEquals(100 + i, this.students[i].getID());
	}
    }
}

class Student implements Comparable {
    private int ID;

    public Student(int ID) {
	this.ID = ID;
    }

    public int getID() {
	return this.ID;
    }

    @Override
    public int compareTo(Object otherStudent) {
	int otherStudentID = ((Student) otherStudent).getID();
	if (this.ID > otherStudentID) {
	    return 1;
	} else if (this.ID == otherStudentID) {
	    return 0;
	} else {
	    return -1;
	}
    }
}
