@Job_Application_Form
Feature: EXPERT: Job Application Form Automation Challenge


	Background: 
		Given Navigate to url
	
	@JAF_001
	Scenario: Submit form with valid data
		When User enter valid input forms
			| 	  Input		|		Value			|
			|	Salutation  | Mr.					|
			|	First Name  | Onur					|
			|	Last Name	| Olmez					|
			|	Email		| onurolmez@gmail.com	|
			|	Mobile		| 1234567890			|
		And User select gender and languagess
			| 	Input		|	Value				|
			|	Gender		|	Male				|
			|	Language	|	English				|
		And User upload vaild .pdf 'Onur OLMEZ - CV.pdf' resume
		And User add skills using Enter key
			|	Java		|
			|	Selenium	|
			|	Cucumber	|
		And User Select multiple job roles
			|	Job Roles	|
			|	Frontend	|
			|	Backend		|
			|	QA			|
		And User set rating slider to 7
		And User pick valid date "12-10-2023" and time "14:30"
		And User Check I accept terms checkbox
		And User click Submit
		Then User verify success snackbar message
		
	@JAF_002
	Scenario: Attempt submission with invalid email
		When User enter invalid email 'abc@xyz'
		And User fill remaining required fields
		And User try submit the form
		Then User verify email error helper text is shown
		
	@JAF_003
	Scenario: Upload invalid resume file
		When User select a .jpg 'IMG_8017.JPG' file from system upload
		Then User verify snackbar shows error message
		
	@JAF_004
	Scenario: Add and delete skill chips
		When User type 'React' in skills input and press enter
		Then User verify 'React' chip appears
		When User delete chip
		Then User verify chip is removed
		
	@JAF_005
	Scenario: Preview form data
		When User Fill some fields in the form
		And User click Preview button
		Then User verify JSON preview matches entered data
		
	@JAF_006
	Scenario: Clear all form fields and verify
		When User Fill some fields in the form
		And User click clear button
		Then User verify all fields reset to defaults

	@JAF_007
	Scenario: Download form data as JSON
		When User fill the firstname 'Onur' and lastname 'Olmez' fields
		Then User click download button and verify file download as firstname.lastname.json
		
	@JAF_008
	Scenario: Submit without accepting terms
		When User Fill all fields correctly except leave terms unchecked
		And User click Submit
		Then User Verify error snackbar shows 'Please fill all fields.'

	
