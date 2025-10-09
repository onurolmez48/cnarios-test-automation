@E-commerce
Feature: E-commerce Product Filtering & Search
	
	@PF_001
	Scenario: Filter products by category
		Given User navigate to product listing page
		When User select category 'Electronics' from filter
		Then Verify all displayed products belong to 'Electronics'
		
	@PF_002
	Scenario: Filter products by price range
		Given User navigate to product listing page
		When User adjust price range slider to 5000 and 50000
		Then User verify all displayed products fall within selected price range
		
	@PF_003
	Scenario: Filter products by minimum rating
		Given User navigate to product listing page
		When User set minimum rating filter to 4 stars
		Then User verify all displayed product have rating >= 4 
		
	@PF_004
	Scenario: Show only in-stock products
		Given User navigate to product listing page
		When User enable In Stock Only filter
		Then User verify all displayed products are in stock
		
	@PF_005
	Scenario: Reset Filters
		Given User navigate to product listing page
		When User apply category, price, and stock filters
		And User click reset button
		And User Verify all filters are cleared
		Then User verify full default product list is restored
		
	@PF_006
	Scenario: Verify product card details format after filtering
		Given User navigate to product listing page
		When User apply category filter
		And User verify each product card displays name
		And User verify price is shown with currency symbol
		And User verify category label is present
		Then User verify rating stars are visible and read-only
		