# Banking System

## Description of the project

In this banking system, `Admin` users can create `Account Holder` and `Third Party` users, and account holders' accounts.

There are four types of accounts: `Student Checking`, `Checking`, `Savings`, and `Credit Card`. Apart from their attributes (seen in the class diagram), these accounts have some specifications:
1. **Checking Accounts**
* Are created when selected, and when the *primary owner* of that account is over 24 years. If not a **Student Checking Account** is created.
* Their minimum balance is 250.
* Their monthly maintenance fee is 12.

2. **Savings Accounts**
* Their minimum balance must be between 100 and 1000, if not defined the default is 1000.
* Their interest rate, which is added yearly, must be between 0 and 0.5, if not defined the default is  0.0025.

3. **Credit Card Accounts**
* Their credit limit must be between 100 and 100,000, if not defined the default is 100.
* Their interest rate, which is added monthly, must be between 0.1 and 0.2, if not defined the default is  0.2.

## Technology Stack
Pre-requisites
The programming language of this repository is Java 17.0.3.1.

Other tools used in the development of the project:

[https://app.diagrams.net/]. 

[Maven] - Dependencies handler. 

[SpringBoot]. 

[MySQL]. 

[JUnit]. 

[Postman]. 


