package by.tms.lesson36.action;


import by.tms.lesson36.entity.Operation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CalculationAction {

    private List<Operation> histories;

    public CalculationAction(List<Operation> histories) {
        this.histories = histories;
    }

    public Operation getRes (Operation operation){
        double res = 0;
            switch (operation.getOperation()) {
                case "sum":
                    res = operation.getNum1() + operation.getNum2();
                    break;
                case "minus":
                    res = operation.getNum1() - operation.getNum2();
                    break;
                case "div":
                    res = operation.getNum1() / operation.getNum2();
                    break;
                case "tines":
                    res = operation.getNum1() * operation.getNum2();
                    break;
            }
            histories.add(new Operation( operation.getNum1(), operation.getNum2(), res, operation.getOperation()));
            return new Operation(operation.getNum1(), operation.getNum2(), res, operation.getOperation());
        }

    public boolean endCalc(String choice){
        switch (choice){
            case "1":
                return true;
            case "2":
                return false;
        }
        return true;
    }

    public List<Operation> choiceHis (String choice){
        switch (choice){
            case "1":
                return histories;
            case "2":
                return null;
        }
        return null;
    }

    }

