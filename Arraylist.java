
import java.util.*;

class Solution {
    public ArrayList<ArrayList<Integer>> formCoils(int n) {

        int m = 8 * n * n;
        int total = 16 * n * n;

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        ArrayList<Integer> first = new ArrayList<>();
        ArrayList<Integer> second = new ArrayList<>();

        int[] coil = new int[m];

        // Generate the basic coil
        coil[0] = 8 * n * n + 2 * n;

        int curr = coil[0];
        int flag = 1;
        int step = 2;
        int index = 1;

        while (index < m) {

            // Move vertically
            for (int i = 0; i < step && index < m; i++) {
                curr = curr - 4 * n * flag;
                coil[index++] = curr;
            }

            // Move horizontally
            for (int i = 0; i < step && index < m; i++) {
                curr = curr + flag;
                coil[index++] = curr;
            }

            flag = -flag;
            step += 2;
        }

        // Construct both required coils
        for (int i = m - 1; i >= 0; i--) {

            int value = coil[i];

            second.add(value);
            first.add(total + 1 - value);
        }

        res.add(first);
        res.add(second);

        return res;
    }
}
