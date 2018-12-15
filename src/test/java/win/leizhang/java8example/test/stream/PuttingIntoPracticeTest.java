package win.leizhang.java8example.test.stream;

import org.junit.Test;
import win.leizhang.java8example.bo.Trader;
import win.leizhang.java8example.bo.Transaction;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * Created by zealous on 2018/12/15.
 */
public class PuttingIntoPracticeTest {

    @Test
    public void test1() {
        // Query 1: Find all transactions from year 2011 and sort them by value (small to high).
        List<Transaction> tr2011 = Transaction.txns.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());
        System.out.println(tr2011);

        // Query 2: What are all the unique cities where the traders work?
        List<String> cities = Transaction.txns.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(toList());
        System.out.println(cities);

        // Query 3: Find all traders from Cambridge and sort them by name.
        List<Trader> traders = Transaction.txns.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());
        System.out.println(traders);

        // Query 4: Return a string of all traders’ names sorted alphabetically.
        String traderStr = Transaction.txns.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(traderStr);

        // Query 5: Are there any trader based in Milan?
        boolean milanBased = Transaction.txns.stream()
                .anyMatch(transaction -> transaction.getTrader()
                        .getCity()
                        .equals("Milan")
                );
        System.out.println(milanBased);

        // Query 6: Update all transactions so that the traders from Milan are set to Cambridge.
        Transaction.txns.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Milan"))
                .forEach(trader -> trader.setCity("Cambridge"));
        System.out.println(Transaction.txns);

        // Query 7: What's the highest value in all the transactions?
        int highestValue = Transaction.txns.stream()
                .map(Transaction::getValue)
                .reduce(0, Integer::max);
        System.out.println(highestValue);
    }
}
