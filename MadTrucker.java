import java.util.*;

/**
 * @author
 * @author
 * @author
 *
 * Solves the Mad Trucker problem by recursively constructing an ordering
 * of the fuel cans such that the truck never stops at a forbidden location.
 *
 * The algorithm maintains the remaining fuel cans and the set of forbidden
 * locations that are still relevant. At every recursive step, it chooses a
 * fuel can that can safely be used last, removes it, and recursively solves
 * the remaining problem.
 *
 * The distances are sorted so that the safeguard case (see below) can be
 * checked in constant time. A HashSet is used for constant-time checks of
 * whether a location is forbidden, while the HashMap stores the original
 * index of every fuel can so that the final ordering can be converted back
 * to the required input indices.
 */
public class MadTrucker {

    private int n;

    private ArrayList<Integer> distances;

    private HashSet<Integer> locations;

    private HashMap<Integer, Integer> originalIndex;

    private int s;

    // Used to accumulate the solved distances in the void recursive function
    private ArrayList<Integer> resultDistances;

    /**
     * Initializes the puzzle using the input values.
     *
     * The total remaining distance is stored in s. The distances are sorted
     * to make the recursive cases easier to handle. Only forbidden locations
     * strictly before the destination are relevant, so locations at or beyond
     * the destination are ignored.
     *
     * The originalIndex map stores the original index of each distance
     * because the list of distances is sorted and consumed during the
     * algorithm.
     *
     * @param n number of fuel cans
     * @param distances travel distance provided by each fuel can
     * @param locations forbidden locations along the road
     */
    public void setup(int n, ArrayList<Integer> distances, ArrayList<Integer> locations) {
        this.n = n;
        this.distances = new ArrayList<>(distances);
        this.originalIndex = new HashMap<>();
        this.s = 0;
        this.resultDistances = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            this.originalIndex.put(this.distances.get(i), i);
            this.s += this.distances.get(i);
        }

        Collections.sort(this.distances);

        this.locations = new HashSet<>();

        for (int location : locations) {
            if (location < this.s) {
                this.locations.add(location);
            }
        }
    }

    /**
     * Calls the recursive solver, maps the solved distances back to their
     * original indices, and returns the final sequence.
     *
     * @return a valid ordering of the original fuel can indices
     */
    public ArrayList<Integer> solve() {
        recursiveSolve();

        ArrayList<Integer> result = new ArrayList<>();
        for (int distance : resultDistances) {
            result.add(originalIndex.get(distance));
        }

        return result;
    }

    /**
     * Recursively constructs a valid ordering of the remaining fuel cans.
     *
     * The current problem has total remaining distance s. If a fuel can with
     * distance x is used last, then immediately before using it the truck is
     * at position s - x. Therefore, x can safely be the final remaining can
     * exactly when s - x is not a forbidden location.
     *
     * There are at most k - 1 forbidden locations left for k remaining cans
     * (this invariant holds initially and is preserved by recurse()). Since
     * the k candidate final positions {s - x : x remaining} are pairwise
     * distinct, by the pigeonhole principle at least one of them is not
     * forbidden, so a safe can to use last can always be found.
     */
    private void recursiveSolve() {
        int k = distances.size();

        /**
         * BASE CASE.
         *
         * If only one can remains, it must be used. Likewise, if there are
         * no forbidden locations remaining, every ordering is valid.
         */
        if (k == 1 || locations.isEmpty()) {
            resultDistances.addAll(distances);
            return;
        }

        int maxLoc = Collections.max(locations);

        /**
         * SAFEGUARD: Jump over the largest forbidden location.
         *
         * If the largest remaining fuel can is longer than the largest
         * relevant forbidden location, using this can first is safe: the
         * truck leaps in one go to a position beyond every forbidden
         * location, so none of them can ever be visited afterwards.
         */
        if (distances.get(k - 1) > maxLoc) {
            int x = distances.remove(k - 1);
            resultDistances.add(x);
            resultDistances.addAll(distances);
            return;
        }

        /**
         * GENERAL CASE.
         *
         * Scan the remaining cans for one that is safe to use last (i.e.
         * s - x is not forbidden). The pigeonhole argument above guarantees
         * that this scan always finds one, so the loop always returns before
         * finishing.
         */
        for (int i = 0; i < k; i++) {
            int x = distances.get(i);
            if (!locations.contains(s - x)) {
                recurse(i);
                return;
            }
        }
    }

    /**
     * Removes the selected fuel can and recursively solves the remaining
     * problem.
     *
     * The state is modified in place because the algorithm never needs to
     * backtrack: once a safe final can has been identified, it can be fixed
     * as the final element of the current solution.
     *
     * The total remaining distance is reduced by x, and forbidden locations
     * beyond the new destination are removed because the truck can no longer
     * reach them.
     *
     * @param i index of the fuel can that will be used last
     */
    private void recurse(int i) {
        int x = distances.remove(i);
        s -= x;
        locations.removeIf(l -> l > s);

        recursiveSolve();

        // Append x after unwinding from the subproblem to preserve the backwards build order
        resultDistances.add(x);
    }

    /**
     * Reads the input, initializes the problem, solves it, and prints the
     * resulting ordering using the original fuel-can indices.
     */
    void run() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        ArrayList<Integer> cans = new ArrayList<>();
        ArrayList<Integer> stops = new ArrayList<>();

        for (int i = 0; i < num; ++i) {
            cans.add(scanner.nextInt());
        }

        for (int i = 0; i < num - 1; ++i) {
            stops.add(scanner.nextInt());
        }
        scanner.close();

        setup(num, cans, stops);

        ArrayList<Integer> result = solve();

        for (int i = 0; i < result.size(); ++i) {
            System.out.print(result.get(i));
            if (i < result.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new MadTrucker().run();
    }
}
