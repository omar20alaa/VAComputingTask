package app.task;

import java.io.Serializable;

public class Equation implements Serializable {

   private   int first_num , sec_num , time , result;
    private  String sign ;
    private  boolean completed ;

    public Equation() { }


    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getFirst_num() {
        return first_num;
    }

    public void setFirst_num(int first_num) {
        this.first_num = first_num;
    }

    public int getSec_num() {
        return sec_num;
    }

    public void setSec_num(int sec_num) {
        this.sec_num = sec_num;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
