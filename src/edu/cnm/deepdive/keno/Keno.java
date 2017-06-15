package edu.cnm.deepdive.keno;

import java.util.Arrays;
import java.util.Random;

public class Keno {
  
  private static final String USAGE_MESSAGE = "Usage: java %s poolSize [drawSize]%n%n"
      + "\twhere poolSize and drawSize (latter optional) are integers.%n";

  public static void main(String[] args) {
    try{
      int poolSize = Integer.parseInt(args[0]);
      int drawSize = (args.length > 1) ? Integer.parseInt(args[1]) : poolSize;
      if (poolSize <= 0 || drawSize <= 0 || drawSize > poolSize) {
        throw new IllegalArgumentException("Arguments must be positive, with poolSize greater than or equal to drawSize");
      }
      System.out.println(Arrays.toString(draw(poolSize, drawSize)));
    }
    catch (NumberFormatException ex){
      ex.printStackTrace();
      System.out.printf(USAGE_MESSAGE, Keno.class.getName());
    }
    catch (ArrayIndexOutOfBoundsException ex) {
      System.out.printf(USAGE_MESSAGE, Keno.class.getName());
    }
    catch(IllegalArgumentException ex) {
      ex.printStackTrace();
      System.out.printf(USAGE_MESSAGE, Keno.class.getName());
    }

  }
  
  public static int[] draw(int poolSize, int drawSize) {
    int[] pool = new int[poolSize];
    Random rng = new Random();
    for (int i = 0; i < pool.length; i++) {
      pool[i] = i + 1;
    }
    for (int i = pool.length - 1; i >= pool.length - drawSize; i--) {
      int swapPosition = rng.nextInt(i + 1);
      if (swapPosition != i) {
        int temp = pool[i];
        pool[i] = pool[swapPosition];
        pool[swapPosition] = temp;
      }
    }
    return Arrays.copyOfRange(pool, pool.length - drawSize, pool.length);
  }

}
