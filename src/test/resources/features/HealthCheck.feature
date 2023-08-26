@ui @healthcheck
Feature: E-commerce Project Website Health Checkup

@ProdSeach
Scenario: User is able to open the browser. nevigate to the URL and search for Product
#Given user opened browser
Given user navigate to the home application url
When user search for product "Laptop"
Then Search result page is displayed with page title keyword contains "Laptop"
#And Browser is closed

@ProdSeach
Scenario: User is able to open the browser. nevigate to the URL and search for Product
#Given user opened browser
Given user navigate to the home application url
When user search for product "Mobile"
Then Search result page is displayed with page title keyword contains "Mobile"
#And Browser is closed

@ProdSeach
Scenario: User is able to open the browser. nevigate to the URL and search for Product
#Given user opened browser
Given user navigate to the home application url
When user search for product "Headphone"
Then Search result page is displayed with page title keyword contains "Headphone"
#And Browser is closed

@ProdSeach
Scenario: User is able to open the browser. nevigate to the URL and search for Product
#Given user opened browser
Given user navigate to the home application url
When user search for product "Power Bank"
Then Search result page is displayed with page title keyword contains "Power Bank"
#And Browser is closed

@ProdSeachWithDesc
Scenario: User is click on the Product and check the Product Details
 #Given user opened browser
 Given user navigate to the home application url
 And user search for product "Power Bank"
 When User click on any product
 Then Product Description is displayed in new tab
 #And Browser is closed

