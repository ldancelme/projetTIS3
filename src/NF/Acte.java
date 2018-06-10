package NF;

public class Acte {
    private Code code;
    private int coef;
    private Date date;
    private String type;
    
    
    
    public Acte(Code code, int coef) {
        this.code = code;
        this.coef = coef;
        this.date=date;
        this.type=type;
       
        
        }
    
    public String toString() {
        return getCode().toString() + ", coefficient : " + coef;
        }
    
    public double cout() {
        return getCode().calculerCout(coef);
        }

    /**
     * @return the code
     */
    public Code getCode() {
        return code;
    }
    public String codeToString(){
        return getCode().toStrin();
    }
    }
