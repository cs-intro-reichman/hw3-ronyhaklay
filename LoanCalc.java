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
		double g = loan / n;
		Double r = 1.0 + rate / 100.0;
		iterationCounter = 0;
		while(endBalance(loan, r, n, g) > epsilon) {
				g = g + epsilon;
				iterationCounter++;
			}
			return g;
		}

    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
		iterationCounter = 0;
		Double r = 1.0 + rate / 100.0;
		double low = loan/n;
		double high = loan;
		double g = (low + high) / 2.0;
		while(Math.abs(high - low) > epsilon) {
			if((endBalance(loan, r, n, g)) * (endBalance(loan, r, n, low)) > 0){
				low = g;
			} else {
				high = g;
			}
			g = (low + high) / 2.0;
			iterationCounter++;
		}
		return g;
    }
}