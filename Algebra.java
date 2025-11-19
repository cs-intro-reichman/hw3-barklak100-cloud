// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
    public static void main(String args[]) {
        
        // Tests some of the operations
        System.out.println("2 + 3 = " + plus(2,3));          // 5
        System.out.println("7 - 2 = " + minus(7,2));          // 5
        System.out.println("2 - 7 = " + minus(2,7));          // -5
        System.out.println("3 * 4 = " + times(3,4));          // 12
        System.out.println("2 + 4 * 2 = " + plus(2,times(4,2)));  // 10
        System.out.println("5 ^ 3 = " + pow(5,3));            // 125
        System.out.println("3 ^ 5 = " + pow(3,5));            // 243
        System.out.println("12 / 3 = " + div(12,3));          // 4    
        System.out.println("5 / 5 = " + div(5,5));            // 1  
        System.out.println("25 / 7 = " + div(25,7));          // 3
        System.out.println("25 % 7 = " + mod(25,7));          // 4
        System.out.println("120 % 6 = " + mod(120,6));        // 0    
        System.out.println("sqrt(36) = " + sqrt(36));         // 6
        System.out.println("sqrt(263169) = " + sqrt(263169)); // 513
        System.out.println("sqrt(76123) = " + sqrt(76123));   // 275
    } 

    // Returns x1 + x2 (Correct)
    public static int plus(int x1, int x2) {
        
        while (x2 > 0) {
            x1++;
            x2--;
        }
        
        while (x2 < 0) {
            x1--;
            x2++;
        }
        
        return x1; 
    }

    // Returns x1 - x2 (Correct)
    public static int minus(int x1, int x2) {
        
        while (x2 > 0) {
            x1--;
            x2--;
        }
        
        while (x2 < 0) {
            x1++;
            x2++;
        }
        
        return x1;
    }
    
    // Returns x1 * x2 (Correct)
    public static int times(int x1, int x2) {
        if (x1 == 0 || x2 == 0) {
            return 0;
        }

        int absX1 = (x1 >= 0) ? x1 : minus(0, x1);
        int absX2 = (x2 >= 0) ? x2 : minus(0, x2);

        int result = 0;
        
        for (int i = 0; i < absX2; i++) {
            result = plus(result, absX1);
        }
        
        if ((x1 < 0 && x2 >= 0) || (x1 >= 0 && x2 < 0)) {
            return minus(0, result);
        }
        
        return result;
    }

    // Returns the integer part of x1 / x2 (Correct)
    public static int div(int x1, int x2) {
        if (x2 == 0) {
            System.err.println("Error: Division by zero.");
            return 0;
        }
        if (x1 == 0) {
            return 0;
        }

        int absX1 = (x1 >= 0) ? x1 : minus(0, x1);
        int absX2 = (x2 >= 0) ? x2 : minus(0, x2);

        int quotient = 0;
        int current = absX1;

        // חיסור חוזר
        while (current >= absX2) {
            current = minus(current, absX2);
            quotient++;
        }

        // קביעת סימן
        if ((x1 < 0 && x2 >= 0) || (x1 >= 0 && x2 < 0)) {
            return minus(0, quotient);
        }
        return quotient;
    }

    // Returns the modulo a % b (MISSING - Added)
    public static int mod(int a, int b) {
        if (b == 0) {
            System.err.println("Error: Modulo by zero.");
            return 0;
        }
        // a % b = a - (div(a, b) * b)
        int q = div(a, b);
        int product = times(q, b);
        return minus(a, product);
    }
    
    // Returns x^n (Correct)
    public static int pow(int x, int n) {
        
        if (n < 0) {
            System.err.println("Error: Negative exponent is not allowed in this integer implementation.");
            return 0;
        }

        if (n == 0) {
            return 1;
        }
        
        if (x == 0) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }
        
        if (x == minus(0, 1)) {
            if (mod(n, 2) == 0) { 
                return 1; 
            }
            else { 
                return minus(0, 1);
            }
        }

        int result = 1;
        for (int i = 0; i < n; i++) {
            result = times(result, x);
        }
        return result;
    }
    
    // Returns the integer part of sqrt(x) (FIXED: The original had an infinite loop)
    public static int sqrt(int x) {
        if (x < 0) {
            System.err.println("Error: Square root of negative number is undefined.");
            return 0;
        }
        if (x == 0) {
            return 0;
        }

        int i = 0;
        // לולאה שמגדילה את i עד ש- i * i גדול מ-x
        while (times(i, i) <= x) {
            // מניעת Integer Overflow אם נגיע לגבול ה-int
            // (46341 * 46341 > Integer.MAX_VALUE)
            if (i >= 46341) { 
                return i; 
            }
            i++;
        }
        // אם i*i > x, התוצאה היא i-1 (הערך האחרון שבו i*i <= x)
        return minus(i, 1);
    }
}