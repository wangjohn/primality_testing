package tester;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BasicPolynomial implements Polynomial {
  
  private final Map<BigInteger,BigInteger> coefficients;
  private BigInteger maximumTerm;
  
  public BasicPolynomial(Map<BigInteger,BigInteger> coefficients){
    this.coefficients = coefficients;
  }

  @Override
  public BigInteger getCoefficient(BigInteger i) {
    BigInteger coefficient = coefficients.get(i);
    if (coefficient == null){
      return BigInteger.ZERO;
    }
    return coefficient;
  }

  @Override
  public Polynomial multiply(Polynomial poly) {
    BigInteger otherCoefficient;
    BigInteger multipliedResultCoefficient;
    Map<BigInteger, BigInteger> newCoefficientSet = new HashMap<BigInteger,BigInteger> ();
    
    for (BigInteger i : coefficients.keySet()){
      otherCoefficient = poly.getCoefficient(i);
      multipliedResultCoefficient = coefficients.get(i).multiply(otherCoefficient);
      newCoefficientSet.put(i, multipliedResultCoefficient);
    }
    
    for (BigInteger i : poly.getNonZeroTerms()){
      if (!newCoefficientSet.containsKey(i)){
        otherCoefficient = coefficients.get(i);
        multipliedResultCoefficient = poly.getCoefficient(i).multiply(otherCoefficient);
        newCoefficientSet.put(i, multipliedResultCoefficient);
      }
    }
    
    Polynomial result = new BasicPolynomial(newCoefficientSet);
    return result;
  }

  @Override
  public Polynomial multiply(BigInteger n) {
    Map<BigInteger,BigInteger> newCoefficients = new HashMap<BigInteger,BigInteger> ();
    for (Map.Entry<BigInteger,BigInteger> entry: coefficients.entrySet()){
      newCoefficients.put(entry.getKey(), entry.getValue().multiply(n));
    }
    Polynomial output = new BasicPolynomial(newCoefficients);
    return output;
  }

  @Override
  public Polynomial modulo(BigInteger n) {
    Map<BigInteger,BigInteger> newCoefficients = new HashMap<BigInteger,BigInteger> ();
    for (Map.Entry<BigInteger,BigInteger> entry: coefficients.entrySet()){
      newCoefficients.put(entry.getKey(), entry.getValue().mod(n));
    }
    Polynomial output = new BasicPolynomial(newCoefficients);
    return output;
  }

  @Override
  public Collection<BigInteger> getNonZeroTerms() {
    return coefficients.keySet();
  }

  @Override
  public BigInteger getMaximumTerm() {
    if (this.maximumTerm == null){
      BigInteger maxSoFar = BigInteger.ZERO;
      for (BigInteger i : coefficients.keySet()){
        if (i.compareTo(maxSoFar) < 0){
          maxSoFar = i;
        }
      }
      this.maximumTerm = maxSoFar;
    }
    return this.maximumTerm;
  }

  @Override
  public Polynomial divide(Polynomial poly) {
    Map<BigInteger,BigInteger> currentCoefficients = coefficients;
    
    while ()
    
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Polynomial add(Polynomial poly) {
    Map<BigInteger,BigInteger> newCoefficients = new HashMap<BigInteger,BigInteger> ();
    BigInteger newCoefficient;
    for (Map.Entry<BigInteger,BigInteger> entry : coefficients.entrySet()){
      newCoefficient = entry.getValue().add(poly.getCoefficient(entry.getKey()));
      newCoefficients.put(entry.getKey(), newCoefficient);
    }
    
    for (BigInteger i : poly.getNonZeroTerms()){
      if (!newCoefficients.containsKey(i)){
        newCoefficient = coefficients.get(i).add(poly.getCoefficient(i));
        newCoefficients.put(i, newCoefficient);
      }
    }
    
    Polynomial output = new BasicPolynomial(newCoefficients);
    return output;
  }

  @Override
  public Polynomial subtract(Polynomial poly) {
    Map<BigInteger,BigInteger> newCoefficients = new HashMap<BigInteger,BigInteger> ();
    BigInteger newCoefficient;
    for (Map.Entry<BigInteger,BigInteger> entry : coefficients.entrySet()){
      newCoefficient = entry.getValue().subtract(poly.getCoefficient(entry.getKey()));
      newCoefficients.put(entry.getKey(), newCoefficient);
    }
    
    for (BigInteger i : poly.getNonZeroTerms()){
      if (!newCoefficients.containsKey(i)){
        newCoefficient = coefficients.get(i).subtract(poly.getCoefficient(i));
        newCoefficients.put(i, newCoefficient);
      }
    }
    
    Polynomial output = new BasicPolynomial(newCoefficients);
    return output;
  }
  
}
