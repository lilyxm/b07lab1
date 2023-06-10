// git checkout go to that branch
// step 1 git add . -- everything in the folder 

// step 2 git commit w/ message -m "s blah blah blah"

//git push

import java.util.Scanner;
import java.io.*;



class Polynomial{
    //global variables
    public double[] coeff;
    public int[] exponent;
    // contructor no args
    Polynomial(){
        coeff = new double[1];
        exponent = new int[1];
        exponent[0] = 0;
        coeff[0] = 0;
    }
    //constructor w/ one double array arg
    Polynomial(double[] coeff, int[] exponent){
        this.coeff = coeff;
        this.exponent = exponent;
    }
    Polynomial(File f) throws Exception{
        Scanner scan = new Scanner(f);
        /*
        if(scan.hasNextLine()){
            this.coeff = null;
            this.exponent = null;
        }*/
        
            String line = scan.nextLine();
            line = line.replace("-","+-");
            String[] poly_arr = line.split("\\+");

            this.coeff = new double[poly_arr.length];
            this.exponent = new int[poly_arr.length];
            for(int i =0; i<poly_arr.length;i++){
                /*
                int idx_x = poly_arr[i].indexOf('x');
                if(idx_x == 0 && poly_arr[i].length()==1){
                    coeff[i] = 1;
                    exponent[i] = 1;
                    
                }
                else if(idx_x == 0){
                    coeff[i] = 1;
                }
                else{
                    coeff[i] = Double.parseDouble(poly_arr[i].substring(0,idx_x));
                }
                if(idx_x == -1){
                    exponent[i] = 0;
                }
                else if(idx_)
        
                else{
                    System.out.println("\n"+poly_arr[i]);
                    exponent[i] = Integer.parseInt(poly_arr[i].substring(idx_x+1,poly_arr[i].length()-1));
                }
                */
    

                
                String[] subArr = poly_arr[i].split("x");
           // System.out.println("success! "+ poly_arr[i]);
            if(subArr.length==0){
                coeff[i] = 1;
                exponent[i] = 1;
            }
            else if(poly_arr[i].indexOf('x')==0){
                coeff[i] = 1;
                //System.out.println("subArr"+subArr[1]);
                exponent[i] = Integer.parseInt(subArr[1]);
            }
            else{
                if(poly_arr[i].indexOf('x')==-1)exponent[i] = 0;
                else if(poly_arr[i].charAt(poly_arr[i].length()-1)=='x')exponent[i] = 1;
                else exponent[i] = Integer.parseInt(subArr[1]);
                coeff[i] = Double.parseDouble(subArr[0]);
            }
            //System.out.println("success! "+i);
        
            }
            scan.close();

        
    }
    //add
    public Polynomial add(Polynomial p){
        double[] t_coeff = new double[coeff.length];
        double[] t_p_coeff = new double[p.coeff.length];
        for(int i = 0; i<p.coeff.length;i++){
            for(int j = 0; j<coeff.length;j++){
                if(p.exponent[i]==exponent[j]){
                    t_coeff[j] = p.coeff[i]+coeff[j];
                    t_p_coeff[i] = t_coeff[j];
                }
            }
        }
        
        if(p.coeff.length<coeff.length){
            Polynomial f = new Polynomial(t_coeff, exponent);
            return f;
        }
        else{
            Polynomial f = new Polynomial(t_p_coeff,p.exponent);
            return f;
        }
    }

    //multiply
    public Polynomial multiply(Polynomial p){
        double []sum_coeff = new double[p.coeff.length*coeff.length];
        int [] sum_exponents = new int[p.coeff.length*coeff.length];

        //initialize the vals
        for(int i = 0; i<sum_coeff.length;i++){
            sum_coeff[i] = 0;
            sum_exponents[i] = -1;
        }
        // FOIL
        int idx = -1;
        for(int i = 0; i<p.coeff.length; i++){
            for(int j = 0; j<coeff.length; j++){
                int k = 0;
                boolean not_in_arr = true;
                //check if exponent sum already exists
                for(k = 0;k<sum_exponents.length; k++){
                    if(sum_exponents[k]==-1)break;
                    if(p.exponent[i]+exponent[j]==sum_exponents[k]){
                        not_in_arr = false;
                        sum_coeff[k] += p.coeff[i]+coeff[j];
                        break;
                    }
                }
                if(not_in_arr){
                    idx++;
                    sum_coeff[idx] = p.coeff[i]*coeff[j];
                    sum_exponents[idx] = p.exponent[i]+exponent[j];
                }
            }
        }
        int i = 0;
        for(i = 0; i<sum_exponents.length;i++){
            if(sum_exponents[i] == -1)break;
        }
        double [] coeff_final = new double[i];
        int [] exp_final = new int[i];
        for(int j = 0; j<i;j++){
            coeff_final[j] = sum_coeff[j];
            exp_final[j] = sum_exponents[j];
        }
        Polynomial f1 = new Polynomial(coeff_final, exp_final);


        return f1;
    }
    //evaluate
    public double evaluate(double x){
        double sum = 0;
        for(int i = 0;i<coeff.length;i++){
            sum += Math.pow(x,exponent[i])*coeff[i];
        
            //System.out.println("\ncoeff: "+coeff[i]+"\neval sum: "+sum+"\n");
        }
        return sum;
    }

    //hasRoot
    public boolean hasRoot(double root){
        return this.evaluate(root) == 0;
    }

    public void saveToFile(String myFile)throws Exception{
        // check if theyre both null, and same length
        FileWriter f = new FileWriter(myFile);
        String writeString = "";
        for(int i =0;i<this.coeff.length;i++){
            if(coeff[i]>0 && i>0)writeString+="+";
            if(exponent[i] == 0||coeff[i]!=1)writeString+=coeff[i];
            if(exponent[i]==1){
                writeString+="x";
            }
            else if(exponent[i]!=0) writeString+="x"+exponent[i];;
            
        }
        //System.out.println(writeString);
        f.write(writeString);
        f.close();

    }

}