@LOGIN-FLOW
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
		
	@TC_003
	Scenario: Login as User
		Given User navigate to login page
		When User enter valid username "user" and password "user123"
		When User click on login button
		And User verify welcome message for user
		Then User verify the User Dashboard is shown
	
	@TC_004
	Scenario: Login as Admin
		Given User navigate to login page
		When User enter valid admins username "admin" and password "admin123"
		When User click on login button
		And User verify welcome message for admin
		Then User verify the Admin Dashboard is shown
		
	@TC_005
	Scenario: Logout functionality
		Given User login with valid credentials
		When User click on logout button
		And User verify login form is displayed
		Then User verify fields are reset to empty