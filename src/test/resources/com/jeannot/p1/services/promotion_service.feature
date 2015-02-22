Feature: Worker promotion service

Scenario: You can promote a worker one rank at a time
	Given a minor worker
	When I promote it
	Then the worker goes up by one rank