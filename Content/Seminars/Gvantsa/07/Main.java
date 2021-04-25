import core.Account;
import core.Grad;
import core.Moodable;
import core.Student;

import java.util.*;

public class Main {

    public static void main(String[] args){
        List<Account> arr = new ArrayList<>();

        Random rand = new Random();

        for (int i = 0; i < 10; i++){
            int amount = rand.nextInt(100);
            System.out.println(amount);
            arr.add(new Account(amount));
        }

        Collections.sort(arr, new Comparator<Account>(){
            @Override
            public int compare(Account account, Account t1) {
                return account.getAmount() - t1.getAmount();
            }
        });

        System.out.println();

        for (Account account: arr){
            System.out.println(account.getAmount());
        }
    }
}