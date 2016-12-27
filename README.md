# ersystem
#### This is an Expense Reimbursement System for [Revature](https://revature.com/) Training. ####

This Expense Reimbursement System (ERS) manages the processes of reimbursing employees for expenses incurred while on company time.
All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. 
Finance managers can log in and view all reimbursement requests and past history for all employees in the company. 
Finance managers are authorized to approve and deny requests for expense reimbursement.

Technologies: This system uses an MVC Framework--
      
*   Data Tier: Java, JDBC connected to an Oracle 11g XE database.

*   Middle Tier: JSP/Servlet technology for dynamic Web application development.

*   Web Tier: JNDI, HTML, CSS, & Bootstrap for dynamic front-end views.

The application is deployed on WebLogic Server (version 12.2.1)

Passwords are encrypted in Java using [JBCrypt](https://github.com/jeremyh/jBCrypt) and securely stored in the database.
