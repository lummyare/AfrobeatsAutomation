Feature: Verify all added Sessions

  Scenario: All created sessiond should be listed in Session screen and Play and Pause for each loaded pads on each sessions pages

    Given I launch the App
		Then go to Multiple session page
		And  Verify All Created session are listed and Play each loaded pads on each sessions pages and stop playing all playing pads 