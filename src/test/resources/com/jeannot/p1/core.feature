Feature: Todgers

Scenario: A todger has a name
  When I create a todger called "Alan"
  Then the todger is called "Alan"
  
Scenario: A todger will be created at lowest rank unless specified otherwise
  When I create a todger called "Brian"
  Then the todger type is minor
  
Scenario: A todger starts out as dormant
  When I create a todger called "Brian"
  Then the todger's status is dormant
  
Scenario: You can't create a todger without a name
  When I try to create a todger with no name
  Then I get an error
