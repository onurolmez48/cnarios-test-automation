Feature: User input fields validation

	@TC_001
	Scenario: Empty fields validation
		Given User navigate to login page
		When User click on login button
		Then The error message "Both fields are required." should be displayed
