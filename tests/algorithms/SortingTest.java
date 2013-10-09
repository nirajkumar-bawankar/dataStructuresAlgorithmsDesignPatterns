package algorithms;

/**
 * Tests all logic within class Sorting.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 5, 2013
 */
public class SortingTest extends junit.framework.TestCase {
    private Student[] students;
    private final int numberOfStudents = 100;

    public void setUp() {
	this.students = new Student[this.numberOfStudents];
	int ID = this.numberOfStudents;
	for (int i = 0; i < this.students.length; i++) {
	    this.students[i] = new Student(ID + i);
	}

	// make the array of students unsorted
	for (int i = 0; i < this.students.length; i++) {
	    int randomNumber = (int) (Math.random() * this.numberOfStudents);
	    Sorting.swap(this.students, i, randomNumber);
	}
    }

    public void test_insertionSort() {
	// array of students is initially a random unsorted array
	Sorting.insertionSort(this.students);

	for (int i = 0; i < this.students.length; i++) {
	    assertEquals(this.numberOfStudents + i, this.students[i].getID());
	}
    }

    public void test_selectionSort() {
	// array of students is initially a random unsorted array
	Sorting.selectionSort(this.students);

	for (int i = 0; i < this.students.length; i++) {
	    assertEquals(this.numberOfStudents + i, this.students[i].getID());
	}
    }

    public void test_mergeSort() {
	// array of students is initially a random unsorted array
	Student[] temperaryArray = new Student[this.numberOfStudents];
	Sorting.mergeSort(this.students, temperaryArray, 0,
		this.students.length - 1);

	for (int i = 0; i < this.students.length; i++) {
	    assertEquals(this.numberOfStudents + i, this.students[i].getID());
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
