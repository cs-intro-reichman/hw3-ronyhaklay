public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

	// Returns x1 + x2
	public static int plus(int x1, int x2) {
		if(x2 > 0) {
			while(x2 != 0) {
				x1++;
				x2--;
			}
		} else {
			while(x2 != 0) {
				x1--;
				x2++;
			}
		}
		return x1;
	}


	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		return plus(x1, changeSign(x2));
	}

	public static int changeSign(int x)
	{
		int newNum = 0;
		if(x > 0)
		{
			while(x != 0)
			{
				x--;
				newNum--;
			}
		}
		else 
		{
			while (x != 0)
			{
				x++;
				newNum++;
			}
		}
		return newNum;
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) {
		int total = 0;
		int absoluteX1 = absoluteValue(x1);
		int absoluteX2 = absoluteValue(x2);
		while(absoluteX2 != 0)
		{
			total = plus(total, absoluteX1);
			absoluteX2--;
		}
		if((0 <= x1 && 0 <= x2) || ( x1 < 0 && x2 < 0))
			return total;
		else 
			return changeSign(total);
	}

	public static int absoluteValue(int y)
	{
		if(y >= 0)
		 return y;
		else
		 return changeSign(y);
	}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		int total = 1;
		while(n != 0)
		{
			total = times(total, x);
			n--;
		}
		return total;
	}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) {
		int total = 0;
		int absoluteX1 = absoluteValue(x1);
		int absoluteX2 = absoluteValue(x2);
		while(absoluteX1 >= absoluteX2) {
			absoluteX1 = minus(absoluteX1, absoluteX2);
			total++;
		}
		if((x1 >= 0 && x2 > 0) || (x1 < 0 && x2 < 0)) {
			return total;
		} else {
			return changeSign(total);
		}		
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		int absoluteX1 = absoluteValue(x1);
		int absoluteX2 = absoluteValue(x2);
		while(absoluteX1 >= absoluteX2)
			absoluteX1 = minus(absoluteX1, absoluteX2);
		if(x1 >= 0)
			return absoluteX1;
		else
		return changeSign(absoluteX1);
	}	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
		int total = 0;
		while(times(total,total) <= x)
			total++;
		return minus(total, 1);
	}	  	  
}