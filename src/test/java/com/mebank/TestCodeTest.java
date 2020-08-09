package com.mebank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestCodeTest
{

    @Test
    public void testACC334455()
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dtFrom = LocalDateTime.parse("20/10/2018 12:00:00", format);
        LocalDateTime dtTo = LocalDateTime.parse("20/10/2018 19:00:00", format);
        assertEquals("-25.00", new CodeTest().getBalance(getList(), "ACC334455", dtFrom, dtTo));
    }

    @Test
    public void testACC778899()
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dtFrom = LocalDateTime.parse("20/10/2018 12:00:00", format);
        LocalDateTime dtTo = LocalDateTime.parse("21/10/2018 19:00:00", format);
        assertEquals("37.25", new CodeTest().getBalance(getList(), "ACC778899", dtFrom, dtTo));
    }

    @Test
    public void testACC998877()
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dtFrom = LocalDateTime.parse("20/10/2018 12:00:00", format);
        LocalDateTime dtTo = LocalDateTime.parse("21/10/2018 19:00:00", format);
        assertEquals("-5.00", new CodeTest().getBalance(getList(), "ACC998877", dtFrom, dtTo));
    }

    private List<Transaction> getList()
    {
        List<Transaction> tList = new ArrayList<>();
        tList.add(new Transaction(new String[]
        {
            "TX10001", "ACC334455", "ACC778899", "20/10/2018 12:47:55", "25.00", "PAYMENT"
        }));
        tList.add(new Transaction(new String[]
        {
            "TX10002", "ACC334455", "ACC998877", "20/10/2018 17:33:43", "10.50", "PAYMENT"
        }));
        tList.add(new Transaction(new String[]
        {
            "TX10003", "ACC998877", "ACC778899", "20/10/2018 18:00:00", "5.00", "PAYMENT"
        }));
        tList.add(new Transaction(new String[]
        {
            "TX10004", "ACC334455", "ACC998877", "20/10/2018 19:45:00", "10.50", "REVERSAL", "TX10002"
        }));
        tList.add(new Transaction(new String[]
        {
            "TX10005", "ACC334455", "ACC778899", "21/10/2018 09:30:00", "7.25", "PAYMENT"
        }));
        return tList;
    }
}
