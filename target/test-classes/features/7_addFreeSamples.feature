Feature: Add Free Samples

  Scenario: Free samples should be added to music pad
    Given I launch the App
    When I tap the Edit page
    Then I should see the edit page show a Free Samples folder
    And I tap to open the folder
    Then I can see audio contents of the folder
	When I tap an empty virtual pad and I tap the first audio content in the Free Samples folder and assert the name of the audio content loaded is the same name of the audio content lapped in free sample folder.
	Then Tap the Edit Pad button again
	Then tap to play and tap to stop each loaded pads Assert I can see all icons and each loaded pads are playing audio.