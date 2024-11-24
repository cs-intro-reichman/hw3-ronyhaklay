public class LoanCalc {

    static double epsilon = 0.001;  
    static int iterationCounter;    

	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

    private static double endBalance(double loan, double rate, int n, double payment) {	 
        for (int i = 1; i <= n; i++)
            loan = (loan - payment) * (1 + (rate / 100));
        return loan;
    }
	
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		iterationCounter = 0;
		double payment = loan / n;
		while (endBalance(loan, rate, n, payment) > epsilon){
			payment += epsilon;
			iterationCounter++;
		}
		return payment;
		}

    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
		iterationCounter = 0;
		double L = loan / n;
		double H = loan;
		double g = (H + L) / 2;

		while ((H - L) > epsilon){
			if (endBalance(loan, rate, n, g) > 0){
				L = g;
				g = (H + L) / 2;
			}
			else {
				H = g;
				g = (H + L) / 2;
			}
			iterationCounter++;
		}
		return g;
    }
}