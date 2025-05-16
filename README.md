# texg-interview

## Automation Framework using Selenium and Java

### Structure

1. Page class created for Login Page which is inherited from Base Page class
2. Test scripts are written in LoginTest file
3. Created Utilities class for Excel, Reporting and Elements 
4. Test Data and TestNG Runner files created under resourcess

## This framework currently tests 6 Posibble test scenarios for a Login Page

1. Log in with Valid Credentials
2. Log in with invalid credentials
3. Log in without password
4. Log in without username and password
5. Log in with performance glitch user
6. Log in with a locked user

### In all these scenarios we are validating session user name on successful login and validate error message for unsuccessful login
 
Note: I have tried to validate the session username in local storage but it is not storing. So ignored the validation.

How to run the tests

1. Navigate to project root directory
2. Run command _**mvn test -DsuiteXmlFile=src/test/resources/testng.xml**_