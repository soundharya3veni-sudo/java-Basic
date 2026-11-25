
class Solution {
    static final long MOD = 1000000007L;

    private long normalTime(long x, long a, long b) {
        if (x <= a) {
            return x;
        }

        long net = a - b;
        long cycles = (x - a + net - 1) / net;

        return x + 2L * b * cycles;
    }

    private long prefix(long x, long a, long b) {
        if (x <= 0) {
            return 0;
        }

        long sum = x * (x + 1) / 2;

        if (x > a) {
            long net = a - b;
            long m = x - a;

            long q = m / net;
            long rem = m % net;

            long cycleSum =
                net * q * (q + 1) / 2
                + rem * (q + 1);

            sum += 2L * b * cycleSum;
        }

        return sum;
    }

    public long minTime(int a, int b, int d, int l, int r) {
        long A = a, B = b, D = d;
        long L = l, R = r;

        long normal = normalTime(D, A, B);
        long cap = normal - 1;

        // Convert jump length j into position x = d - j
        long low = D - R;
        long high = D - L;

        // Find maximum x where normalTime(x) <= cap
        long left = 1, right = D;
        long cutoff = 0;

        while (left <= right) {
            long mid = left + (right - left) / 2;

            if (normalTime(mid, A, B) <= cap) {
                cutoff = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        long end = Math.min(high, cutoff);
        long sum = 0;

        if (low <= end) {
            sum += prefix(end, A, B)
                 - prefix(low - 1, A, B);
        }

        long start = Math.max(low, cutoff + 1);

        if (start <= high) {
            long count = high - start + 1;
            sum += count * cap;
        }

        // One second for the special jump
        sum += R - L + 1;

        return sum % MOD;
    }
}
