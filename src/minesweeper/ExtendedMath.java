package minesweeper;

import java.math.BigInteger;

public class ExtendedMath
{
	public static BigInteger nCr(int n, int r)
	{
		return nPr(n, r).divide(factorial(r));
	}

	public static BigInteger nPr(int n, int r)
	{
		return factorial(n).divide(factorial(n - r));
	}
	
	public static BigInteger factorial(int n)
	{
		BigInteger number = BigInteger.valueOf(n);
		
		if(n == 0) return BigInteger.ONE;
		
		for (int i = 2; i < n; i++)
		{
			number = number.multiply(BigInteger.valueOf(i));
		}

		return number;
	}

}
