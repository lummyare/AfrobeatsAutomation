Feature: Play & Pause Music

  Scenario: When the music is played/paused, progress bar should be enabled/disabled

    Given I launch the App
    And I Tap to play and tap to stop playing each loaded pads
    When I tap on music pad
    Then Music should be played & progress bar should be displayed
    And I tap on pause
    Then Music should be paused & progress bar should be removed