package by.tms.lesson36.cansole;

import org.springframework.stereotype.Component;

@Component
public class Writer {

    public void writer( Object message){
       System.out.println(message);
    }
}
