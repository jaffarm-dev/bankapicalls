Hi,

Assumption \

1).Used h2 in memory database for data storing. because of no need to install the database. Just to get it and use. \
2).In get account balance request used account number as path variable. \
3).To fetch transactional data for given parameters used headers to collect parameters. \

Please see needed all curls in curls.txt in src/main/resources. \

You can just start the application and try the attached curls on their. \
Step 1.
Create new  account. \

Step 2.(Optional)
See the balance

Step 3.
Do some transaction based the particular account no. \
Only transaction type "WITHDRAW" & "DEPOSIT" are allowed.

Step 4.
Then you can get transaction information based on the given parameters. \

(Attention) We will probably ask you what issues might come about if the size of the response from query 2 and 3 is too large. \ 
What if you had 1 million transactions in that time range range. Think about that and maybe implement a solution for it.

**issues might come about if the size of the response from query 2 and 3 is too large : \
1). It will take much time to get the response.
2). Stack overflows can be occurred and api will be failed. \
Solution for this
We can use pageable option to break down the dataset in to small pieces. Then based on the demand data can be served in few requests instead of a single request.










