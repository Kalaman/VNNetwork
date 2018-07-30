package AI;

public class Utilities {

    public static double sigmoid(double x) {
        return (1.0 / (1.0 + Math.exp(-x)));
    }
    public static double sigmoidPrime(double x) {
        return x * (1-x);
    }
}
