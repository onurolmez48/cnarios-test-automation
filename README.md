# 🧠 CNARIOS - Test Automation Challenges

[![Java](https://img.shields.io/badge/Java-21+-blue.svg?logo=java)](https://www.oracle.com/java/)  
[![Maven](https://img.shields.io/badge/Maven-Build%20Tool-red.svg?logo=apache-maven)](https://maven.apache.org/)  
[![Selenium](https://img.shields.io/badge/Selenium-4.x-green.svg?logo=selenium)](https://www.selenium.dev/)  
[![Cucumber](https://img.shields.io/badge/Cucumber-BDD-orange.svg?logo=cucumber)](https://cucumber.io/)  
[![JUnit](https://img.shields.io/badge/JUnit-5-blueviolet.svg?logo=junit5)](https://junit.org/junit5/)  

---

> 🚀 **Objective:** Automate all test cases within each [Cnarios Challenge](https://www.cnarios.com/challenges).  
> This project tests UI scenarios written in **Gherkin** using a **BDD (Behavior Driven Development)** approach.  
> By implementing the **Page Object Model (POM)**, it ensures that the test code is **maintainable**, **scalable**, and **DRY**.

---

## 🧩 Tech Stack

| Technology | Purpose |
|-------------|----------|
| **Java** | Core programming language |
| **Selenium WebDriver** | Web UI automation |
| **Cucumber** | BDD framework using Gherkin syntax |
| **JUnit 5** | Test runner |
| **Maven** | Dependency management & build tool |

---

## ⚙️ Prerequisites

| Requirement | Description |
|--------------|-------------|
| **JDK 21+** | Ensure that Java is installed and `JAVA_HOME` is set |
| **Maven** | Ensure that `.m2` class path is properly configured |
| **IDE Plugins** | Install **Maven** and **Cucumber** plugins |
| **Browser** | Chrome, Firefox, or Edge (at least one installed) |

---

## 📊 Reporting

After running the tests, an **HTML report** is automatically generated in: target/-cucumber-html-reports


📁 **Open the HTML report locally** to view detailed scenario results.  
📸 **Screenshots** are taken for failed scenarios and attached to the report.


## ▶️ Running Suite


1.Execute the test cases by Maven command mvn clean test

2.To run specific browser(default Chrome) for example Firefox mvn clean test -Dbrowser=firefox

3.To run specific tags on Linux-based CI Environments (bash): mvn clean test -Dcucumber.filter.tags=""

4.To run specific tags on Windows-based CI Environments (powershell): mvn clean test "-Dcucumber.filter.tags="




## 💬 Example Feature (Gherkin)
**Challenge Page:** [https://www.cnarios.com/challenges/login-flow](https://www.cnarios.com/challenges/login-flow)

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



## 🌐 Useful Links

- 🔗 **Challenge Page:** [Cnarios Challenges](https://www.cnarios.com/challenges)  
- 📘 **Cucumber Docs:** [https://cucumber.io/docs](https://cucumber.io/docs)  
- 💡 **Selenium Docs:** [https://www.selenium.dev/documentation](https://www.selenium.dev/documentation)  
- 🛠 **Maven Build & Dependencies:** [https://mvnrepository.com/](https://mvnrepository.com/)



## 🧠 Future Enhancements

- 🚀 **CI/CD pipeline** with GitHub Actions / Jenkins     
- 🌐 **Cross-browser & headless mode tests**  
- 🛠 **Environment configuration support** (dev, staging, prod)



