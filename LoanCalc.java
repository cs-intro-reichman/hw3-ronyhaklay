public class LoanCalc {

    static double epsilon = 0.001;  
    static int iterationCounter;    

    public static void main(String[] args) {		
        double loan = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);
        System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

        System.out.print("\nPeriodical payment, using brute force: ");
        System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
        System.out.println("number of iterations: " + iterationCounter);

        System.out.print("\nPeriodical payment, using bi-section search: ");
        System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
        System.out.println("number of iterations: " + iterationCounter);
    }

    private static double endBalance(double loan, double rate, int n, double payment) {	 
        for (int i = 0; i < n; i++)
            loan = (loan - payment) * (1 + rate / 100);
        return loan;
    }

    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
        double payment = loan / n;
        iterationCounter = 0;

        while (true) {
            double total = endBalance(loan, rate, n, payment);
            iterationCounter++;
            if (Math.abs(total) <= epsilon)
                return payment;
            if (total > 0)
                payment += epsilon;
            else
                payment -= epsilon;
        }
    }

    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
        double lowValue = 0; 
        double highValue = loan * Math.pow(1 + rate / 100, n);
        double midValue = 0;
        iterationCounter = 0;

        while (highValue - lowValue > epsilon) {
            midValue = (lowValue + highValue) / 2;
            double total = endBalance(loan, rate, n, midValue);
            iterationCounter++;
            if (Math.abs(total) <= epsilon)
                return midValue;
            if (total > 0)
                lowValue = midValue;
            else
                highValue = midValue;
        }
        return midValue;
    }
}