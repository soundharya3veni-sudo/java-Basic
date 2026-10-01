
import java.util.*;

class Solution {
    public long minTime(int[] duration, int[][] dependencies) {
        int n = duration.length;

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        int[] indegree = new int[n];

        // Build graph
        for (int[] edge : dependencies) {
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);
            indegree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        long[] dp = new long[n];

        // Add modules having no dependencies
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
                dp[i] = duration[i];
            }
        }

        int count = 0;
        long answer = 0;

        // Topological sorting
        while (!queue.isEmpty()) {
            int u = queue.poll();
            count++;

            answer = Math.max(answer, dp[u]);

            for (int v : adj.get(u)) {

                dp[v] = Math.max(
                    dp[v],
                    dp[u] + duration[v]
                );

                indegree[v]--;

                if (indegree[v] == 0) {
                    queue.add(v);
                }
            }
        }

        // Cycle detection
        if (count != n) {
            return -1;
        }

        return answer;
    }
}
