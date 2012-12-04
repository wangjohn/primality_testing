package tester;

import java.math.BigInteger;

public class BigIntegerOperations {
  
  /**
   * Use the Newton Raphson method to compute square roots. Update function is
   * X_{i+1} = (1/2)*(X_i + a/X_i)
   * 
   * @param n
   * @return The floor of the square root.
   */
  public static BigInteger squareRoot(BigInteger n){
    BigInteger prevX;
    int digits = n.bitLength();

    // Get approximation for sqrt(n). See Wikipedia for math behind this.
    if ((digits % 2) == 0){
      prevX = BigInteger.valueOf(6).multiply(BigInteger.TEN.pow(digits/2+1));
    } else {
      prevX = BigInteger.valueOf(2).multiply(BigInteger.TEN.pow(digits/2));
    }
    
    
    BigInteger nextX = null;
    while (prevX.compareTo(nextX) != 0){
      prevX = nextX;
      nextX = (prevX.add(n.divide(prevX))).divide(BigInteger.valueOf(2));
    }
    return nextX;
  }
  
  /**
   * 
   * @param n
   * @param base
   * @return The floor of the logarithm n in base base.
   */
  public static BigInteger log(BigInteger n, BigInteger base){
    BigInteger l = BigInteger.ONE;
    BigInteger basePowerL = base;
    while (basePowerL.compareTo(n) <= 0){
      basePowerL = basePowerL.multiply(base);
      l = l.add(BigInteger.ONE);
    }
    return l.subtract(BigInteger.ONE);
  }
  
  /**
   * Order of a mod n or O_n(a) is the smallest integer k such that 
   * a^k = 1 mod n.
   * 
   * @param a
   * @param n
   * @return
   */
  public static BigInteger multiplicativeOrder(BigInteger a, BigInteger n){
    BigInteger k = BigInteger.ONE;
    BigInteger residual = a;
    while (residual.compareTo(BigInteger.ONE) != 0){
      residual = residual.multiply(a).mod(n);
      k = k.add(BigInteger.ONE);
    }
    return k;
  }
  
  /**
   * Returns the gcd of integers a and b. For one less cycle, a should b larger than b.
   * However, this doesn't necessarily have to be the case and the function will take 
   * care of it if b > a. 
   * 
   * @param a
   * @param b
   * @return
   */
  public static BigInteger greatestCommonDivisor(BigInteger a, BigInteger b){
    BigInteger t; 
    while (b.compareTo(BigInteger.ZERO) != 0){
      t = b;
      b = a.mod(b);
      a = t;
    }
    return a;
  }
  
  /**
   * Slow implementation of Euler's Totient Function, does it the naive way.
   * @param n
   * @return
   */
  public static BigInteger eulerTotient(BigInteger n){
    BigInteger output = BigInteger.ZERO;
    for (BigInteger i=BigInteger.ONE; i.compareTo(n)<0; i.add(i)){
      if (greatestCommonDivisor(i,n)==BigInteger.ONE){
        output = output.add(BigInteger.ONE);
      }
    }
    return output;
  }
}
