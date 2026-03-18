public class MatrixSolver {
    public static void main(String[] args) {
        // Your specific data
        double a=6, b=1, c=1;
        double d=4, e=-2, f=5;
        double g=2, h=8, i=7;

        // Formula: a(ei - fh) - b(di - fg) + c(dh - eg)
        double term1 = a * (e * i - f * h);
        double term2 = b * (d * i - f * g);
        double term3 = c * (d * h - e * g);

        double determinant = term1 - term2 + term3;

        System.out.println("The Determinant of your matrix is: " + determinant);
    }
}