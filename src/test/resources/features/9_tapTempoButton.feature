Feature: tap on TAP TEMPO Button Multiple times
  I want to use this feature for TAP TEMPO Button deactivated  

  Scenario: TAP TEMPO Button should be deactivated after tapping on that mutiple times
  Given I launch the App
  When I enable Tap Tempo button from Settings  
	When I tap on Tap Tempo button multiple times
	Then Tap tempo button should be deactivated