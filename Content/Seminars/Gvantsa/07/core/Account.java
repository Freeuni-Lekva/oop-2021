package core;

import java.util.ArrayList;

public class Account implements Moodable/*, Comparable<Account>*/ {
    private int amount;

    public Account(int amount){
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int newAmount){
        amount = newAmount;
    }

    public void transact(Account other, int transactionAmount){
        if (amount < transactionAmount){
            return;
        }
        amount -= transactionAmount;
        other.setAmount(other.getAmount() + transactionAmount);
    }

    @Override
    public Boolean getMood() {
        System.out.println("Account Mood");
        return amount > 1000;
    }

//    @Override
//    public int compareTo(Account account) {
//        return getAmount() - account.getAmount();
//    }
}
