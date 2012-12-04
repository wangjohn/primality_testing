package tester;

import java.math.BigInteger;

public class BasicAKSTester {
  public static boolean originalAKSTester(BigInteger n){
    
    // Step 1: find if n is composite through power decomposition.
    if (isCompositeThroughPowerDecomposition(n)){
      return false;
    }
    
    // Step 2: get the smallest r such that o_r(n) > log^2(n)
    BigInteger logN = BigIntegerOperations.log(n, BigInteger.valueOf(2));
    BigInteger logNSquared = logN.pow(2);
    BigInteger r = getSmallestOrderLargerThanLogNSquared(n, logNSquared);
    
    // Step 3: compute the gcd of r numbers.
    BigInteger greatestCommonDiv;
    for (BigInteger a=BigInteger.ONE; a.compareTo(r)<=0; a.add(BigInteger.ONE)){
      greatestCommonDiv = BigIntegerOperations.greatestCommonDivisor(a, n);
      if (greatestCommonDiv.compareTo(BigInteger.ONE)>0 && greatestCommonDiv.compareTo(n)<0){
        return false;
      }
    }
    
    // Step 4: if n <= r, output prime
    if (n.compareTo(r)<= 0){
      return true;
    }
    
    // Step 5: for a range of a, check if (X+a)^n \neq X^n+a (mod X^r − 1,n)
    BigInteger sqrtPhi = BigIntegerOperations.squareRoot(BigIntegerOperations.eulerTotient(n));
    BigInteger endForLoop = sqrtPhi.multiply(logN);
    for (BigInteger a=BigInteger.ONE; a.compareTo(endForLoop)<= 0; a.add(BigInteger.ONE)){
      if (checkStepFiveEquality(n, a, r)){
        return false;
      }
    }
    
    // Step 6: otherwise output prime
    return true;
  }
  
  /**
   * check if (X+a)^n \neq X^n+a (mod X^r − 1,n)
   * 
   * @param n
   * @param a
   * @param r
   * @return
   */
  private static boolean checkStepFiveEquality(BigInteger n, BigInteger a, BigInteger r){
    return false;
  }
  
  /**
   * Find smallest r such that o_r(n) > log^2(n). We do this by trying out successive
   * values of r and testing if n^k \neq 1 mod r for all k <= log^2(n).
   * 
   * @return
   */
  private static BigInteger getSmallestOrderLargerThanLogNSquared(BigInteger n, BigInteger logNSquared){
    BigInteger residual = n;
    BigInteger r = BigInteger.ONE;
    
    boolean actualRFound = false;
    while (!actualRFound){
      r = r.add(BigInteger.ONE);
      actualRFound = true;
      for (BigInteger k=BigInteger.valueOf(1); k.compareTo(logNSquared)<=0;
          k.add(BigInteger.ONE)){
        residual = residual.add(n).mod(r);
        if (residual.compareTo(BigInteger.ONE) == 0){
          actualRFound = false;
          break;
        }
      }
    }
    return r;
  }
  
  /**
   * Checks compositeness by examining if n = a^b for a > 0 and b > 1. 
   * We assume n is greater than 3.
   * 
   * @param n
   * @return
   */
  private static boolean isCompositeThroughPowerDecomposition(BigInteger n){
    BigInteger a = BigInteger.valueOf(1);
    BigInteger aPowerB = a;
    while (a.pow(2).compareTo(n) <= 0){
      a = a.add(BigInteger.ONE);
      aPowerB = a;
      while (aPowerB.compareTo(n) < 0){
        aPowerB = aPowerB.multiply(a);
      }
      if (aPowerB.compareTo(n) == 0){
        return true;
      }
    }
    return false;
  }
}
