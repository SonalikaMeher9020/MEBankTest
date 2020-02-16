# # # ME Bank Coding Challenge # # #

## System Requirements
JDK 1.8 or above

## Given CSV File data
transactionId,fromAccountId,toAccountId,createdAt,amount,transactionType,relatedTransaction
TX10001,	ACC334455,	ACC778899,	20/10/2018 12:47:55,	25,	PAYMENT	
TX10002,	ACC334455,	ACC998877,	20/10/2018 17:33:43,	10.5,	PAYMENT	
TX10003,	ACC998877,	ACC778899,	20/10/2018 18:00:00,	5,	PAYMENT	
TX10004,	ACC334455,	ACC998877,	20/10/2018 19:45:00,	10.5,	REVERSAL,	TX10002
TX10005,	ACC334455,	ACC778899,	21/10/2018 09:30:00,	7.25,	PAYMENT

## User Inputs/Output:
#Inputs:
accountId: ACC334455
from: 20/10/2018 12:00:00
to: 20/10/2018 19:00:00

#Outputs:
Account Id: ACC334455
From DateTime: 20/10/2018 12:00:00
To DateTime: 20/10/2018 19:00:00
Relative balance for the period is: -$25.0
Number of transactions included is: 1


## Assumptions:
1. The name of the csv file, which contains the transactions, will always be 'transrec.csv'.
2. User will provide the inputs either in the command line or in the program argument (when using Eclipse).


	
## Design:
Keeping design simple and robust with different modules(data, service, serviceImpl) so that the enhancement on the top of this design will be easy and scalable. Implemented testcases for services and validation to have a better quality of code.
Attached jar file(If system is not configured with required platform to run) so that can be run from command line.
	

## Steps for Running the project(Service & Testcase) using IDE (Eclipse)

1. Clone or Download repository to the local location
2. Navigate to the downloaded path and Unzip the file into a location
3. Open an IDE (preferable Eclipse)
4. Import the project using Eclipse's import existing maven project and provide the root directory as per the path of step-2 above
5. Update the project using the maven-update option
6. Clean the project using Maven-clean option
7. Build/install the project using Maven build/install
8. Now, run the project as 'Java Application' and select the main class name as 'TransactionMain' and provide program argument for AccountId, from date, to date(ex: "ACC334455" "20/10/2018 12:00:00" "21/10/2018 19:00:00") .
9. Run Test case(TransactionTest.java)- select TransactionTest.java from folder /src/test/java/service and run as JUnit Test.

## Steps for Running the project with custom input in the csv file

1. Follow the steps 1-3 as per above.
2. Navigate to the relative path "/MeTest/src/main/resources" where the code is copied. Now, edit the contents of the csv file 'transrec.csv' as per requirement. Or, create a new csv file (with name 'transrec.csv') containing the required input.
3. Follow the steps 4-8 as per above.

## Steps for Running the project using the runnable jar

1. Clone or Download repository to the local location
2. Navigate to the downloaded path and Unzip the file into a location
3. Go inside of the folder and note the name of the jar file
4. Open the command prompt and navigate to the above path (step-3) where the jar file is located
5. Now run the command in the command prompt: "java -jar meTest.jar" AccountId fromdate todate (ex: java -jar meTest.jar "ACC334455" "20/10/2018 12:00:00" "21/10/2018 19:00:00")to get the desired output


