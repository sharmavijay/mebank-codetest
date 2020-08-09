package com.mebank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction
{

    private final String transactionId;
    private final String fromAccountId;
    private final String toAccountId;
    private final LocalDateTime createAt;
    private final BigDecimal amount;
    private final String transactionType;
    private final String relatedTransaction;

    public Transaction(String[] data)
    {
        this.transactionId = data[0].trim();
        this.fromAccountId = data[1].trim();
        this.toAccountId = data[2].trim();
        this.amount = new BigDecimal(data[4].trim());
        this.transactionType = data[5].trim();
        if (data.length == 7)
        {
            this.relatedTransaction = data[6].trim();
        } else
        {
            this.relatedTransaction = "";
        }

        //DateTimeFormatter format = DateTimeFormatter.ofPattern("DD/MM/YYYY hh:mm:ss");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime lDt = LocalDateTime.parse(data[3].trim(), format);
        this.createAt = lDt;
    }

    /**
     * @return the transactionId
     */
    public String getTransactionId()
    {
        return transactionId;
    }

    /**
     * @return the fromAccountId
     */
    public String getFromAccountId()
    {
        return fromAccountId;
    }

    /**
     * @return the toAccountId
     */
    public String getToAccountId()
    {
        return toAccountId;
    }

    /**
     * @return the createAt
     */
    public LocalDateTime getCreateAt()
    {
        return createAt;
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount()
    {
        return amount;
    }

    /**
     * @return the transactionType
     */
    public String getTransactionType()
    {
        return transactionType;
    }

    /**
     * @return the relatedTransaction
     */
    public String getRelatedTransaction()
    {
        return relatedTransaction;
    }

    @Override
    public String toString()
    {
        return "[" + transactionId + ", " + fromAccountId + ", " + toAccountId + ", " + createAt + ", " + amount + ", " + transactionType + ", " + relatedTransaction + ']';
    }

}
