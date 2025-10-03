Feature: User input fields validation

	@TC_001
	Scenario: Empty fields validation
		Given User navigate to login page
		When User click on login button
		Then The error message "Both fields are required." should be displayed

	@TC_002
	Scenario: Invalid credentials
		Given User navigate to login page
		When User enter username "wrongUser" and password "wrongPass"
		And User click on login button
		Then User verify the error message "Invalid username or password." should be displayed
		