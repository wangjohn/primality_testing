package tester;

import java.math.BigInteger;

public class BasicPrimalityTester {

  /**
   * Simplest algorithm imaginable. Walks up until n/2, testing for divisibility.
   * @param n
   * @return
   * @throws NegativeNumberException 
   */
  public static boolean naivePrimalityTester(BigInteger n) throws NegativeNumberException{
    if (testSmallValues(n)){
      return true;
    }
    BigInteger endValue = n.divide(BigInteger.valueOf(2));
    return testAllValues(n, BigInteger.valueOf(2), endValue, BigInteger.valueOf(1));
  }
  
  /**
   * Only goes to the square root of n, and steps by 2.
   * @param n
   * @return
   */
  public static boolean naivePrimalityTester2(BigInteger n){
    if (testSmallValues(n)){
      return true;
    }
    BigInteger endValue = BigIntegerOperations.squareRoot(n).add(BigInteger.ONE);
    if (n.mod(BigInteger.valueOf(2)).compareTo(BigInteger.ZERO) == 0){
      return true;
    }
    return testAllValues(n, BigInteger.valueOf(3), endValue, BigInteger.valueOf(2));
  }
  
  /**
   * Tests divisibility by all numbers of the form 6k+1 or 6k-1 (as well as 2 and 3).  
   * @param n
   * @return
   */
  public static boolean naivePrimalityTester3(BigInteger n){
    if (testSmallValues(n)){
      return true;
    }
    BigInteger endValue = BigIntegerOperations.squareRoot(n).add(BigInteger.ONE);
    if (n.mod(BigInteger.valueOf(2)).compareTo(BigInteger.ZERO) == 0 || 
        n.mod(BigInteger.valueOf(3)).compareTo(BigInteger.ZERO) == 0){
      return true;
    }
    return (testAllValues(n, BigInteger.valueOf(7), endValue, BigInteger.valueOf(6)) || 
        testAllValues(n, BigInteger.valueOf(5), endValue, BigInteger.valueOf(6)));
  }
  
  /**
   * Uses Wilson's theorem: p is prime iff (p-1)! = -1 mod p. 
   * @param n
   * @return
   */
  public static boolean wilsonsTheoremTester(BigInteger n){
    BigInteger nMinusOneFactorial = BigInteger.ONE;
    for (BigInteger i=BigInteger.valueOf(2); i.compareTo(n)<0; i.add(BigInteger.ONE)){
      nMinusOneFactorial = nMinusOneFactorial.multiply(i);
    }
   
    return (nMinusOneFactorial.mod(n).compareTo(n.subtract(BigInteger.ONE)) == 0);
  }
  
  private static boolean testSmallValues(BigInteger n) throws NegativeNumberException{
    if (n.compareTo(BigInteger.ZERO) < 0){
      throw new NegativeNumberException("Cannot test primality of a negative number.");
    }
    if (n.compareTo(BigInteger.valueOf(3)) < 0){
      return true;
    }
    return false;
  }
  
  private static boolean testAllValues(BigInteger n, BigInteger start, BigInteger end, 
      BigInteger step){
    for (BigInteger i=start; i.compareTo(end) < 0; i.add(step)){
      if (n.mod(i).compareTo(BigInteger.ZERO) == 0){
        return true;
      }
    }
    return false;
  }
  
}
