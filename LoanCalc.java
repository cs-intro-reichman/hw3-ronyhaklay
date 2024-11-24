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
		double charge = loan / n;
		iterationCounter = 0;
		while (epsilon < endBalance(loan, rate, n, charge))
		{
			charge += epsilon;
			iterationCounter++;
		}
		return charge;
		}

    public static double bisectionSolver(double loan, double rate, int n, double epsilon) 
	{  	double high = loan; double low = loan / n;
		double mid = (high + low) / 2;
		iterationCounter = 0;

		while (epsilon < (high - low))
		{
			if (endBalance(loan, rate, n, mid) > 0){
				low = mid;
				mid = (high+ low) / 2;
			}
			else 
			{
				high = mid;
				mid = (high + low) / 2;
			}
			iterationCounter++;
		}
		return mid;
    }
}