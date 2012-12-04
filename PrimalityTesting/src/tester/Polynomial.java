package tester;

import java.math.BigInteger;
import java.util.Collection;

public interface Polynomial {

  public BigInteger getCoefficient(BigInteger i);
  
  public Polynomial multiply(Polynomial poly);
  public Polynomial multiply(BigInteger n);
  public Polynomial divide(Polynomial poly);
  public Polynomial add(Polynomial poly);
  public Polynomial subtract(Polynomial poly);
  public Polynomial modulo(BigInteger n);
  
  public Collection<BigInteger> getNonZeroTerms();
  public BigInteger getMaximumTerm();
  
}
