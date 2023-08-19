@ui @healthcheck
Feature: E-commerce Project Website Health Checkup

Scenario: User is able to open the browser. nevigate to the URL and search for Product
Given user opened browser
And user navigate to the home application url
When user search for product "Laptop"
Then Search result page is displayed with page title keyword contains "Laptop"

Scenario: User is able to open the browser. nevigate to the URL and search for Product
Given user opened browser
And user navigate to the home application url
When user search for product "Mobile"
Then Search result page is displayed with page title keyword contains "Mobile"


Scenario: User is able to open the browser. nevigate to the URL and search for Product
Given user opened browser
And user navigate to the home application url
When user search for product "Headphone"
Then Search result page is displayed with page title keyword contains "Headphone"

Scenario: User is able to open the browser. nevigate to the URL and search for Product
Given user opened browser
And user navigate to the home application url
When user search for product "Power Bank"
Then Search result page is displayed with page title keyword contains "Power Bank"

