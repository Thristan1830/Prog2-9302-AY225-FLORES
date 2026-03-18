/**
 * 3x3 Matrix Determinant Solver
 * Uses cofactor expansion along Row 1
 * Assigned Matrix from reference image:
 *   | 6  2  3 |
 *   | 4  5  1 |
 *   | 3  1  4 |
 *
 * ===================================================
 *  HAND-WRITTEN SOLUTION (Step-by-step)
 * ===================================================
 *
 *  Given Matrix:
 *    | 6  2  3 |
 *    | 4  5  1 |
 *    | 3  1  4 |
 *
 *  Formula (cofactor expansion along Row 1):
 *    det(M) = a₁₁·C₁₁ + a₁₂·C₁₂ + a₁₃·C₁₃
 *           = a₁₁·(+1)·M₁₁ + a₁₂·(-1)·M₁₂ + a₁₃·(+1)·M₁₃
 *
 *  Where a₁₁ = 6, a₁₂ = 2, a₁₃ = 3
 *
 *  ── Step 1: Minor M₁₁ ──────────────────────────────
 *    Remove row 1 and col 1 → submatrix: | 5  1 |
 *                                         | 1  4 |
 *    M₁₁ = (5×4) - (1×1) = 20 - 1 = 19
 *
 *  ── Step 2: Minor M₁₂ ──────────────────────────────
 *    Remove row 1 and col 2 → submatrix: | 4  1 |
 *                                         | 3  4 |
 *    M₁₂ = (4×4) - (1×3) = 16 - 3 = 13
 *
 *  ── Step 3: Minor M₁₃ ──────────────────────────────
 *    Remove row 1 and col 3 → submatrix: | 4  5 |
 *                                         | 3  1 |
 *    M₁₃ = (4×1) - (5×3) = 4 - 15 = -11
 *
 *  ── Step 4: Cofactors ───────────────────────────────
 *    C₁₁ = (+1) × 6 × 19  =  114
 *    C₁₂ = (-1) × 2 × 13  =  -26
 *    C₁₃ = (+1) × 3 × -11 =  -33
 *
 *  ── Step 5: Determinant ─────────────────────────────
 *    det(M) = 114 + (-26) + (-33)
 *           = 114 - 26 - 33
 *           = 55
 *
 *  ✓  DETERMINANT = 55
 * ===================================================
 */
public class DeterminantSolver {

    public static void main(String[] args) {

        // ── Assigned matrix ──────────────────────────────────────────────
        int[][] M = {
            {6, 2, 3},
            {4, 5, 1},
            {3, 1, 4}
        };

        String studentName = "Flores, Thristan Eathan C."; // ← replace with your name

        // ── Header ───────────────────────────────────────────────────────
        System.out.println("===================================================");
        System.out.println("  3x3 MATRIX DETERMINANT SOLVER");
        System.out.println("  Student: " + studentName);
        System.out.println("  Assigned Matrix:");
        System.out.println("===================================================");
        printMatrix(M);
        System.out.println("===================================================");

        // ── Cofactor expansion along Row 1 ───────────────────────────────
        System.out.println();
        System.out.println("Expanding along Row 1 (cofactor expansion):");
        System.out.println();

        // Minor M11: submatrix removing row 0, col 0  →  [M[1][1], M[1][2]], [M[2][1], M[2][2]]
        int a11 = M[1][1], b11 = M[1][2], c11 = M[2][1], d11 = M[2][2];
        int det11 = a11 * d11 - b11 * c11;
        System.out.printf("  Step 1 — Minor M\u2081\u2081: det([%d,%d],[%d,%d]) = (%d\u00d7%d) - (%d\u00d7%d) = %d - %d = %d%n",
                a11, b11, c11, d11,
                a11, d11, b11, c11,
                a11 * d11, b11 * c11, det11);

        // Minor M12: submatrix removing row 0, col 1  →  [M[1][0], M[1][2]], [M[2][0], M[2][2]]
        int a12 = M[1][0], b12 = M[1][2], c12 = M[2][0], d12 = M[2][2];
        int det12 = a12 * d12 - b12 * c12;
        System.out.printf("  Step 2 — Minor M\u2081\u2082: det([%d,%d],[%d,%d]) = (%d\u00d7%d) - (%d\u00d7%d) = %d - %d = %d%n",
                a12, b12, c12, d12,
                a12, d12, b12, c12,
                a12 * d12, b12 * c12, det12);

        // Minor M13: submatrix removing row 0, col 2  →  [M[1][0], M[1][1]], [M[2][0], M[2][1]]
        int a13 = M[1][0], b13 = M[1][1], c13 = M[2][0], d13 = M[2][1];
        int det13 = a13 * d13 - b13 * c13;
        System.out.printf("  Step 3 — Minor M\u2081\u2083: det([%d,%d],[%d,%d]) = (%d\u00d7%d) - (%d\u00d7%d) = %d - %d = %d%n",
                a13, b13, c13, d13,
                a13, d13, b13, c13,
                a13 * d13, b13 * c13, det13);

        System.out.println();

        // ── Cofactors ────────────────────────────────────────────────────
        int elem1 = M[0][0], elem2 = M[0][1], elem3 = M[0][2];

        int C11 = +1 * elem1 * det11;   // sign = (+1)
        int C12 = -1 * elem2 * det12;   // sign = (-1)
        int C13 = +1 * elem3 * det13;   // sign = (+1)

        System.out.printf("  Cofactor C\u2081\u2081 = (+1) \u00d7 %d \u00d7 %d = %3d%n", elem1, det11, C11);
        System.out.printf("  Cofactor C\u2081\u2082 = (-1) \u00d7 %d \u00d7 %d = %3d%n", elem2, det12, C12);
        System.out.printf("  Cofactor C\u2081\u2083 = (+1) \u00d7 %d \u00d7 %d = %3d%n", elem3, det13, C13);

        System.out.println();

        // ── Determinant ──────────────────────────────────────────────────
        int det = C11 + C12 + C13;

        System.out.printf("  det(M) = %d + (%d) + %d%n", C11, C12, C13);
        System.out.println();
        System.out.println("===================================================");
        System.out.printf("  \u2713  DETERMINANT = %d%n", det);
        System.out.println("===================================================");
    }

    // ── Helper: print the matrix in the display format ───────────────────
    private static void printMatrix(int[][] M) {
        for (int[] row : M) {
            System.out.printf("  |  %d   %d   %d  |%n", row[0], row[1], row[2]);
        }
    }
}