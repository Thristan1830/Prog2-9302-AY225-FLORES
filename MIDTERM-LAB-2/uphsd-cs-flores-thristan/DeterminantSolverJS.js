/**
 * 3x3 Matrix Determinant Solver (JavaScript / Node.js)
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

// ── Helper: print the matrix in display format ───────────────────────────────
function printMatrix(M) {
    for (const row of M) {
        console.log(`  |  ${row[0]}   ${row[1]}   ${row[2]}  |`);
    }
}

// ── Main ─────────────────────────────────────────────────────────────────────

// Assigned matrix
const M = [
    [6, 2, 3],
    [4, 5, 1],
    [3, 1, 4]
];

const studentName = "Flores, Thristan Eathan C."; // ← replace with your name

// ── Header ───────────────────────────────────────────────────────────────────
console.log("===================================================");
console.log("  3x3 MATRIX DETERMINANT SOLVER");
console.log(`  Student: ${studentName}`);
console.log("  Assigned Matrix:");
console.log("===================================================");
printMatrix(M);
console.log("===================================================");

// ── Cofactor expansion along Row 1 ───────────────────────────────────────────
console.log();
console.log("Expanding along Row 1 (cofactor expansion):");
console.log();

// Minor M11: submatrix removing row 0, col 0  →  [M[1][1], M[1][2]], [M[2][1], M[2][2]]
const a11 = M[1][1], b11 = M[1][2], c11 = M[2][1], d11 = M[2][2];
const det11 = a11 * d11 - b11 * c11;
console.log(`  Step 1 — Minor M\u2081\u2081: det([${a11},${b11}],[${c11},${d11}]) = (${a11}\u00d7${d11}) - (${b11}\u00d7${c11}) = ${a11 * d11} - ${b11 * c11} = ${det11}`);

// Minor M12: submatrix removing row 0, col 1  →  [M[1][0], M[1][2]], [M[2][0], M[2][2]]
const a12 = M[1][0], b12 = M[1][2], c12 = M[2][0], d12 = M[2][2];
const det12 = a12 * d12 - b12 * c12;
console.log(`  Step 2 — Minor M\u2081\u2082: det([${a12},${b12}],[${c12},${d12}]) = (${a12}\u00d7${d12}) - (${b12}\u00d7${c12}) = ${a12 * d12} - ${b12 * c12} = ${det12}`);

// Minor M13: submatrix removing row 0, col 2  →  [M[1][0], M[1][1]], [M[2][0], M[2][1]]
const a13 = M[1][0], b13 = M[1][1], c13 = M[2][0], d13 = M[2][1];
const det13 = a13 * d13 - b13 * c13;
console.log(`  Step 3 — Minor M\u2081\u2083: det([${a13},${b13}],[${c13},${d13}]) = (${a13}\u00d7${d13}) - (${b13}\u00d7${c13}) = ${a13 * d13} - ${b13 * c13} = ${det13}`);

console.log();

// ── Cofactors ─────────────────────────────────────────────────────────────────
const elem1 = M[0][0], elem2 = M[0][1], elem3 = M[0][2];

const C11 = +1 * elem1 * det11;  // sign = (+1)
const C12 = -1 * elem2 * det12;  // sign = (-1)
const C13 = +1 * elem3 * det13;  // sign = (+1)

console.log(`  Cofactor C\u2081\u2081 = (+1) \u00d7 ${elem1} \u00d7 ${det11} = ${C11}`);
console.log(`  Cofactor C\u2081\u2082 = (-1) \u00d7 ${elem2} \u00d7 ${det12} = ${C12}`);
console.log(`  Cofactor C\u2081\u2083 = (+1) \u00d7 ${elem3} \u00d7 ${det13} = ${C13}`);

console.log();

// ── Determinant ───────────────────────────────────────────────────────────────
const det = C11 + C12 + C13;

console.log(`  det(M) = ${C11} + (${C12}) + ${C13}`);
console.log();
console.log("===================================================");
console.log(`  \u2713  DETERMINANT = ${det}`);
console.log("===================================================");