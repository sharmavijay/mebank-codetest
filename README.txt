Solution:

The solution consists of following steps:
- Read input file line by line
- split each line based on "," and create a Transaction Object
- add this Transaction object to an ArrayList
- ArrayList tList now consists of all lines from input file as Transactions
- Read console inputs for accountId, from and to
- filter tList using streams to create a List of REVERSAL transactionIds
- filter tList using streams to get a list of transactions for accountId based on date "to" and date "from" and excluding REVERSAL transactions 
- calculate sum for all "to accountId" transactions
- calculate sum for all "from accountId" transactions
- subtract "from accountId sum" from "to accountId sum" to get balance
- print balance and number of transactions


Build and Run:

- clone git repository 
- build using "mvn clean package"
- run tests using "mvn test"
- run project for input file input.csv using 
  "java -jar target/CodeTest-1.0-SNAPSHOT-jar-with-dependencies.jar input.csv"

- run project for input file input2.csv using 
  "java -jar target/CodeTest-1.0-SNAPSHOT-jar-with-dependencies.jar input2.csv"

- Enter accountId , from and to to get balance


Example:

vijay@DellLatitude-E6520 ~/test/MEBank/CodeTest $ java -jar target/CodeTest-1.0-SNAPSHOT-jar-with-dependencies.jar input.csv 
accountId: ACC334455
from: 20/10/2018 12:00:00
to: 20/10/2018 19:00:00
Relative balance for the period is: -25.00
Number of transactions included is: 1
vijay@DellLatitude-E6520 ~/test/MEBank/CodeTest $ 

