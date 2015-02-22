Feature: Workers

Scenario: A worker has a name
  When I create a worker called "Alan"
  Then the worker is called "Alan"
  
Scenario: A worker will be created at lowest rank unless specified otherwise
  When I create a worker called "Brian"
  Then the worker type is minor
  
Scenario: A worker starts out as dormant
  When I create a worker called "Brian"
  Then the worker's status is dormant
  
Scenario: You can't create a worker without a name
  When I try to create a worker with no name
  Then I get an error
