Feature: Todger promotion service

Scenario: You can promote a todger one rank at a time
	Given a minor todger
	When I promote it
	Then the todger goes up by one rank