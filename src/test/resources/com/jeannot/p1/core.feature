Feature: creating Todgers

Scenario: I can create a todger with a name
  When I create a todger called "Alan"
  Then the todger is called "Alan"
  
