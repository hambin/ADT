package exercise.java.java_new_feature.stream.transaction_operation;

import java.io.StreamCorruptedException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by hanbing on 2017/6/21.
 */
public class Main {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        List<Transaction> transOfYear11 = transactions.stream().filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(transOfYear11.toString());
        // (2) 交易员都在哪些不同的城市工作过？
        List<String> cities = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(cities.toString());
        // (3) 查找所有来自于剑桥的交易员，并按姓名排序。
        List<Trader> optionalOfTrader = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(optionalOfTrader.toString());
        // (4) 返回所有交易员的姓名字符串，按字母顺序排序。
        List<String> listOfName = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(listOfName.toString());
        // (5) 有没有交易员是在米兰工作的？
        boolean isTraderInMilan = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> trader.getCity() == "Cambridge");
        System.out.println(isTraderInMilan);
        // (6) 打印生活在剑桥的交易员的所有交易额。
        Optional<Integer> sum = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);
        System.out.println(sum.orElse(0));
        // (7) 所有交易中，最高的交易额是多少？
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .reduce((a, b) -> a > b ? a : b);
        OptionalInt maxForIntstream = transactions.stream().mapToInt(Transaction::getValue).max();
        System.out.println(max.orElse(0));
        // (8) 找到交易额最小的交易。
        Optional<Integer> min = transactions.stream()
                .map(Transaction::getValue)
                .reduce((a, b) -> a < b ? a : b);
        System.out.println(min.orElse(0));
    }
}
