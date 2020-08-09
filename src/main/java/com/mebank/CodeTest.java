package com.mebank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CodeTest
{

    public static void main(String[] args)
    {
        // Just Catch all Exceptions 
        try
        {
            // read the input CSV file and load data as Transactions
            BufferedReader buf = new BufferedReader(new FileReader(args[0]));
            String row;
            List<Transaction> tList = new ArrayList<>();
            while ((row = buf.readLine()) != null)
            {
                Transaction t = new Transaction(row.split(","));
                tList.add(t);
            }
            buf.close();

            // Now ask for input parameters
            System.out.print("accountId: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String accountId = reader.readLine().trim();

            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            System.out.print("from: ");
            reader = new BufferedReader(new InputStreamReader(System.in));
            String dateFrom = reader.readLine().trim();
            LocalDateTime dtFrom = LocalDateTime.parse(dateFrom, format);

            System.out.print("to: ");
            reader = new BufferedReader(new InputStreamReader(System.in));
            String dateTo = reader.readLine().trim();
            LocalDateTime dtTo = LocalDateTime.parse(dateTo, format);

            new CodeTest().getBalance(tList, accountId, dtFrom, dtTo);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String getBalance(List<Transaction> tList, String accountId, LocalDateTime dtFrom, LocalDateTime dtTo)
    {
        // Do the job
        List<String> rev = tList.stream().filter(t -> "REVERSAL".equals(t.getTransactionType())).map(Transaction::getRelatedTransaction).collect(Collectors.toList());
        //rev.forEach(System.out::println);
        List<Transaction> r = tList.stream().filter(t ->
        {
            return (accountId.equals(t.getFromAccountId()) || accountId.equals(t.getToAccountId())) && (t.getCreateAt().isAfter(dtFrom) && t.getCreateAt().isBefore(dtTo));
        }).filter(t -> !rev.contains(t.getTransactionId())).filter(t -> !t.getTransactionType().equals("REVERSAL")).collect(Collectors.toList());
        //r.forEach(System.out::println);
        BigDecimal toSum = r.stream().filter(t -> accountId.equals(t.getToAccountId())).map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal fromSum = r.stream().filter(t -> accountId.equals(t.getFromAccountId())).map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal balance = toSum.subtract(fromSum);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        System.out.println("Relative balance for the period is: " + formatter.format(balance));
        System.out.println("Number of transactions included is: " + r.size());
        return balance.toPlainString();
    }
}
