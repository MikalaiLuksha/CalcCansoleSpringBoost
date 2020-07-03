package by.tms.lesson36.cansole;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Reader {

    public double readDouble(){
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextDouble()) {
            double v = scanner.nextDouble();
            return v;
        }
        throw new IllegalStateException("Error");
    }

    public String readString(){
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();

        return s;
    }

}

