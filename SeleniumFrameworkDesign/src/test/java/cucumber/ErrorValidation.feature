@tag
Feature: Error validation


@ErrorValidation
Scenario Outline: Positive test of submitting the order.

Given I landed on Ecommerce Page
When Logged in with username <name> and password <password>.
Then "Incorrect email or password." message is displayed

Examples:
|name						|password		|
|rahulshetty@gmail.com		|Iamking@00	    |				