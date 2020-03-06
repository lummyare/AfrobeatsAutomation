Feature: Title verification

  Scenario: When the application is launched, title should be "African Kit"

    Given I launch the App
    When App is launched
    Then Assert the Page name is African Kit