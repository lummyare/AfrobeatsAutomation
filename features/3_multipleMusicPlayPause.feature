Feature: Play & Pause Music for multiple pads

Scenario: When the music is played/paused for multiple pads, play/pause buttons should be displayed accordingnly
  
  	Given I launch the App
    When I tap on music pad
    Then Music should be played & progress bar should be displayed
    And I tap on 2nd music pad
    Then Music should be played for 2nd pad
    And I tap on pause for 2nd pad
    Then Music should be paused & progress bar should be removed