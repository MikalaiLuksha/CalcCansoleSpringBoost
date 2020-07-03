package by.tms.lesson36;

import by.tms.lesson36.action.CalculationAction;
import by.tms.lesson36.action.UserAction;
import by.tms.lesson36.cansole.Reader;
import by.tms.lesson36.cansole.Writer;
import by.tms.lesson36.entity.Operation;
import by.tms.lesson36.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Lesson36Application implements CommandLineRunner {

    @Autowired
    CalculationAction calculationAction;
    @Autowired
    UserAction userAction;

    @Autowired
    Reader reader;
    @Autowired
    Writer writer;

    @Bean
    public List<Operation> histories() {
        return new ArrayList<>();
    }

    @Bean
    public List<User> userList() {
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        SpringApplication.run(Lesson36Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        boolean enterCalc = false;
        while (!enterCalc) {
            String s = menuUser();
            enterCalc = choiceRegAuth(s);
        }
        cacl();
    }

    private String menuUser() {
        writer.writer("Регистрация - 1, Авторизация -2");
        return reader.readString();
    }

    private boolean choiceRegAuth(String opt) {
        switch (opt) {
            case "1":
                registration();
                return false;
            case "2":
                return authorisation();
        }
        return false;
    }

    private void registration(){
        User user = menuRegAuth();
        saveUser(user);
       }

     private boolean authorisation(){
           User user = menuRegAuth();
           return authUser(user);
       }

    private void cacl(){
        boolean next = true;
        while (next) {
            Operation operationStart = startMenu();
            Operation operationCalc = startCalculation(operationStart);
            outRes(operationCalc);
            next = endMenu();
        }
        outHistory();
    }

    private Operation startMenu(){
         this.writer.writer("Выберите операцию sum, minus, times, div");
        String operation = reader.readString();
        this.writer.writer("Введите первое числло");
        double num1 = reader.readDouble();
        this.writer.writer("Введите второе числло");
        double num2 = reader.readDouble();
        return new Operation(num1, num2, operation);
    }

    private Operation startCalculation(Operation operation){
    return calculationAction.getRes(operation);
    }

    private void outRes(Operation operation){
    writer.writer("Ваш результат");
    writer.writer(operation.getRes());
    }

    private boolean endMenu(){
    writer.writer("Выберите продолжить - 1, завершить - 2");
    String s = reader.readString();
    return calculationAction.endCalc(s);
    }

    private void outHistory(){
        writer.writer("Показать историю да - 1, нет - 2");
        String s = reader.readString();
        System.out.println(calculationAction.choiceHis(s));
    }

    private User menuRegAuth(){
        this.writer.writer("Enter login");
        String login = reader.readString();
        this.writer.writer("Enter password");
        String password = reader.readString();
        return new User(login, password);
    }

    private void saveUser(User user){
    userAction.addUser(user);
    }

    private boolean authUser(User user){
        return userAction.authCheck(user);
    }

}
