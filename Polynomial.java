// git checkout go to that branch
// step 1 git add . -- everything in the folder 

// step 2 git commit w/ message -m "s blah blah blah"


class Polynomial{
    //global variables
    public double[] coeff;
    // contructor no args
    Polynomial(){
        coeff = new double[1];
        coeff[0] = 0;
    }
    //constructor w/ one double array arg
    Polynomial(double[] coeff){
        this.coeff = coeff;
    }

    //add
    public Polynomial add(Polynomial p){
        double[] max_array;
        double[] min_array;
        if(p.coeff.length>=coeff.length){
            max_array = p.coeff;
            min_array = coeff;
        }
        else{
            max_array = coeff;
            min_array = p.coeff;
        }
        
        for(int i = 0;i<min_array.length;i++){
            max_array[i] = max_array[i]+min_array[i];
        }
        Polynomial p_final = new Polynomial(max_array);
        return p_final;
        
        
    }

    //evaluate
    public double evaluate(double x){
        double sum = 0;
        for(int i = 0;i<coeff.length;i++){
            sum += coeff[i]*Math.pow(x,i);
        }
        return sum;
    }

    //hasRoot
    public boolean hasRoot(double root){
        return this.evaluate(root) == 0;
    }

    

}