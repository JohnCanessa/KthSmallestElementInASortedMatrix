import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
// import java.util.Random;


/**
 * 
 */
public class KthSmallestElementInASortedMatrix {


    /**
     * Count the # of values that is less than or equal to mid.
     * 
     * Time: O(n ^ 2) - Space: O(1)
     */
    static private int count0 (int[][] matrix, int mid) {

        // **** initialization ****
        int count   = 0;
        int n       = matrix.length;        // for ease of use
        int col     = n - 1;

        // **** traverse rows (top to bottom) - O(n)****
        for (int row = 0; row < n; row++) {

            // **** traverse columns (right to left) - O(n) ****
            for ( ; col >= 0; col--) {

                // **** increment count (if needed) ****
                if (matrix[row][col] <= mid) {

                    // **** increment count ****
                    count += (col + 1);

                    // **** exit inner loop ****
                    break;
                }
            }
        }

        // **** return count ****
        return count;
    }


    /**
     * Given an n x n matrix where each of the rows and columns are sorted in ascending order, 
     * return the kth smallest element in the matrix.
     * 
     * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
     */
    static int kthSmallest0 (int[][] matrix, int k) {

        // **** sanity checks ****
        if (k == 1) return matrix[0][0];

        // **** initialization ****
        int n       = matrix.length;            // for ease of use
        int low     = matrix[0][0];
        int high    = matrix[n - 1][n - 1];

        // **** binary search ****
        while (low < high) {

            // **** update mid value ****
            int mid = (high - low) / 2 + low;

            // **** update high or low value ****
            if (count0(matrix, mid) >= k)
                high = mid;
            else 
                low = mid + 1;
        }

        // **** return low ****
        return low;
    }


    /**
     * Check to update high (return true) or low (return false) value. 
     */
    private static boolean check (int[][] matrix, int mid, int k, int n) {

        // **** initialization ****
        int col = n - 1;
        int row = 0;
        int num = 0;

        // **** ****
        while (col >= 0 && row < n) {
            if (matrix[col][row] <= mid) {
                num += col + 1;
                row++;
            } else
                col--;
        }

        // **** ****
        return num >= k;
    }


    /**
     * Given an n x n matrix where each of the rows and columns are sorted in ascending order, 
     * return the kth smallest element in the matrix.
     * 
     * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
     */
    static int kthSmallest (int[][] matrix, int k) {

        // **** initialization ****
        int n       = matrix.length;        // for ease of use
        int low     = matrix[0][0];
        int high    = matrix[n - 1][n - 1];

        // **** ****
        while (low <= high) {

            // **** compute mid ****
            int mid = low + (high - low) / 2;

            // **** update high or low ****
            if (check(matrix, mid, k, n))
                high = mid - 1;
            else
                low = mid + 1;
        }

        // **** return low ****
        return low;
    }


    /**
     * Test scaffold
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));

        // **** read k ****
        int k = Integer.parseInt(br.readLine().trim());

        // **** read first line for matrix ****
        String[] line = br.readLine().trim().split(",");

        // **** extract value for n ****
        int n = line.length;

        // **** declare matrix ****
        int[][] matrix = new int[n][n];
        matrix[0] = Arrays.stream(line).mapToInt(Integer::parseInt).toArray();

        // **** populate the rest of the matrix ****
        for (int i = 1; i < n; i++) {
            matrix[i] = Arrays.stream(br.readLine().trim().split(","))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        }
    
        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<< k: " + k);
        System.out.println("main <<< n: " + n);
        System.out.println("main <<< matrix:");
        for (int i = 0; i < n; i++)
            System.out.println(Arrays.toString(matrix[i]));

        // ???? ????
        // for (int i = 0; i < 17; i++) {

        //     // ???? ????
        //     Random rand = new Random();

        //     // ???? generate mid ????
        //     int mid = matrix[0][0] + rand.nextInt(matrix[n - 1][n - 1] - matrix[0][0] + 1);
        //     if (mid < matrix[0][0] || mid > matrix[n - 1][n - 1]) {
        //         System.err.println("main <<< invalid mid: " + mid); System.exit(-1);
        //     }

        //     // ???? compute count of mid ????
        //     System.out.println("main <<< count(" + mid + "): " + count0(matrix, mid));
        // }

        // **** call function of interest and display result ****
        System.out.println("main <<< kthSmallest0: " + kthSmallest0(matrix, k));

        // **** call function of interest and display result ****
        System.out.println("main <<<  kthSmallest: " + kthSmallest(matrix, k));
    }
}