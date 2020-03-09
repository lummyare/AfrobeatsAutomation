Feature: Vol Norm

  Scenario: VOL NORM should be enabled/disabled

    Given I launch the App
    When I tap the VOL NORM button
    Then assert the button is deactivated
    And when I tap the button again
    Then assert the button is activated back