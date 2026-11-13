@tag
Feature: Purchase the order from Ecommerce website.

Background:
Given: I landed on the Ecommerce Page.

@Regression
Scenario Outline: Positive test of submitting the order.

Given Logged in with username <name> and password <password>.
When I add product <productname> to cart.
And checkout <productname> and submit the order.
Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page.

Examples:
|name						|password						|productname		|
|rahulshetty@gmail.com		|Iamking@000					|ZARA COAT 3		|