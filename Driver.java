import java.io.*;
public class Driver {

   
    public static void main(String [] args) throws Exception {
        
        File f = new File("tester.txt");

        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1 = {1,3,3,1};
        int [] e1 = {0,1,2,3};
        Polynomial p1 = new Polynomial(c1,e1);
        printPolynomial(p1);
        double [] c2 = {1,-4,6,-4,1};
        int [] e2 = {0,1,2,3,4};
        Polynomial p2 = new Polynomial(c2,e2);
        
        
        printPolynomial(p2);
        Polynomial p3 = new Polynomial(f);
        printPolynomial(p3);
        
        Polynomial s = p1.add(p2);
        printPolynomial(s);
        Polynomial product = p1.multiply(p2);
        printPolynomial(product);

        System.out.println("p1(-1) = " + p1.evaluate(-1));
        if(p1.hasRoot(-1))
        System.out.println("-1 is a root of p1");
        else
        System.out.println("-1 is not a root of p1");
        p2.saveToFile("tester.txt");
    }

    public static void printPolynomial(Polynomial p){
        System.out.println();
        for(int i = 0; i<p.coeff.length;i++){
            System.out.print(p.coeff[i]+"x"+p.exponent[i]+"+");
        }
        System.out.println();
    }
}