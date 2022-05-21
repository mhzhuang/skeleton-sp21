package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> a = new AListNoResizing<>();
        BuggyAList<Integer> b = new BuggyAList<>();
        a.addLast(4);
        a.addLast(5);
        a.addLast(6);

        b.addLast(4);
        b.addLast(5);
        b.addLast(6);

        assertEquals(a.size(), b.size());

        assertEquals(a.removeLast(), b.removeLast());
        assertEquals(a.removeLast(), b.removeLast());
        assertEquals(a.removeLast(), b.removeLast());
    }

    @Test
    public void randomizedTest() {

        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> bug = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                bug.addLast(randVal);
                //System.out.println("addLast(" + randVal + ")");
                assertEquals(L.size(), bug.size());

            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                //System.out.println("Cor size: " + size);
                //System.out.println("Bug size: " + bug.size());
                assertEquals(L.size(), bug.size());
            } else if (operationNumber == 2) {
                // getLast
                if (L.size() == 0) {
                    continue;
                }
                //System.out.println("Cor getLast: " + L.getLast());
                //System.out.println("Bug getLast: " + L.getLast());
                assertEquals(L.getLast(), bug.getLast());
            } else if (operationNumber == 3) {
                // removeLast
                if (L.size() == 0) {
                    continue;
                }
                assertEquals(L.removeLast(), bug.removeLast());
            }
        }
    }
}
