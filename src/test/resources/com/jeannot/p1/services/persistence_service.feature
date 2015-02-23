Feature: Worker persistence service persists worker data

Scenario: You can store and retrieve a worker
	Given I create a worker called "Alan"
	When I store it in the database
	Then I can retrieve it

Scenario: You can update a worker
	Given I create a worker called "Alan"
	When I store it in the database
	And I promote and update it
	Then the updated worker can be retrieved
	
Scenario: You can delete a worker
	Given I create a worker called "Alan"
	When I store it in the database
	And I delete it
	Then that worker can no longer be retrieved