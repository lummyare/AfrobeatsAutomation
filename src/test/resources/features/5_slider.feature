Feature: Move slider

  Scenario: On moving slider, values should be changed

    Given I launch the App
    When I tap on the number beside the green light on the top left
    Then I should see a slider pop up
    And I "increase" slide to 170, I should see the same number reflect on the app
    And I "decrease" slide to 90, I should see the same number reflect on the app
    And I tap on back button, slider pop up should close