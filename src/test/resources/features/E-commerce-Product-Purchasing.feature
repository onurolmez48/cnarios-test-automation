@Product_Purchasing
Feature: MEDIUM: E-Commerce-End-to-End-Purchasing-Flow

		Background:
			Given User navigate to product listing page

		@PP_001
		Scenario: Add product to cart and verify
			When User click Add to Card on 'Wireless Headphones'
			And User click cart icon in the navbar
			Then User verify Wireless Headphones is diplayed with correct price and quantity
			
		@PP_002
		Scenario: Increase and decrease product quantity
			When User add 'Smartphone Stand' to card
			And User open the cart
			And User click + button to increase quantity
			Then User verify quantity increases and total updates
			And User click - button to decrease quantity
			Then User verify quantity decrease and total updates
			
		@PP_003
		Scenario: Remove product from cart
			When User add 'Laptop Backpack' to card
			And User open the cart
			And User click - button to decrease quantity
			Then User verify 'Laptop Backpack' is no longer in the cart
			Then User verify cart count in navbar is updated
			
		@PP_004
		Scenario: Billing form validation
			When User add 'Bluetooth Speaker' to card
			And User open the cart
			And User click Proceed to Address button
			And User leave all address fields empty
			Then User verify Proceed to Payment button is disabled
		
		@PP_005
		Scenario: Successful payment flow
			When User add 'Fitness Band' to card
			And User open the cart
			And User click Proceed to Address button
			Then User enter valid first name 'Onur', last name 'Olmez', address 'Marmaris'
			And User click Proceed to Payment button
			And User click Pay Now button
			Then User Verify success message with billing details and total is displayed
			
		@PP_006
		Scenario: Failed payment flow
			When User add 'Wireless Headphones' to card
			And User open the cart
			And User click Proceed to Address button
			Then User enter valid first name 'Onur', last name 'Olmez', address 'Marmaris'
			And User click Proceed to Payment button
			And User click Cancel button
			Then User verify failure message and Go Home button are displayed
			
		@PP_007
		Scenario: Go Home resets flow
			When User add 'Fitness Band' to card
			And User open the cart
			And User click Proceed to Address button
			Then User enter valid first name 'Onur', last name 'Olmez', address 'Marmaris'
			And User click Proceed to Payment button
			And User click Pay Now button
			And User click Go Home button
			Then User verify product listing page is shown and navbar is empty
			
			
			
			
	