# 3×3 Matrix Determinant Solver

**Flores, Thristan Eathan C. — 9302 -AY225**
**Course:** BSIT - 1
**Topic:** Linear Algebra / Assignment-01

---

## Assigned Matrix

The following 3×3 matrix was assigned for this activity:

```
| 6  2  3 |
| 4  5  1 |
| 3  1  4 |
```

The matrix is displayed in a readable bordered format when either program is run.

---

## How to Run

### Java Program

> Requirements: Java JDK installed (`javac` and `java` commands available)

```bash
# Step 1 — Compile the Java file
javac DeterminantSolver.java

# Step 2 — Run the program
java DeterminantSolver
```

---

### JavaScript Program

> Requirements: Node.js installed (`node` command available — download at https://nodejs.org)

```bash
# Run directly — no compile step needed
node DeterminantSolverJS.js
```

---

## Sample Output

```
===================================================
  3x3 MATRIX DETERMINANT SOLVER
  Student: Flores, Thristan Eathan C.
  Assigned Matrix:
===================================================
  |  6   2   3  |
  |  4   5   1  |
  |  3   1   4  |
===================================================

Expanding along Row 1 (cofactor expansion):

  Step 1 — Minor M₁₁: det([5,1],[1,4]) = (5×4) - (1×1) = 20 - 1 = 19
  Step 2 — Minor M₁₂: det([4,1],[3,4]) = (4×4) - (1×3) = 16 - 3 = 13
  Step 3 — Minor M₁₃: det([4,5],[3,1]) = (4×1) - (5×3) = 4 - 15 = -11

  Cofactor C₁₁ = (+1) × 6 × 19  =  114
  Cofactor C₁₂ = (-1) × 2 × 13  =  -26
  Cofactor C₁₃ = (+1) × 3 × -11 =  -33

  det(M) = 114 + (-26) + (-33)

===================================================
  ✓  DETERMINANT = 55
===================================================
```

---

## Final Answer

> **Determinant = 55**
