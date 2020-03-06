Feature: purchase Store Items
  I want to use this Feature for purchase items from Store Page

  Scenario: Cart Items should be successfully purchase from Store Screen.
    Given I launch the App
    And The assert the store button is showing store count 40
    When I tap on the store count 
    Then I see the store page
    And I should see 40 items in list
    Then I refresh the page
    When I tap on RESTORE PURCHASES button and purchase all items
		Then Exit Store page and Go to Edit Page
		Then Assert all Folder names showing are all contents downloaded from Store page
    
