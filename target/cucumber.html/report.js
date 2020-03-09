$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("1_pageTitle.feature");
formatter.feature({
  "line": 1,
  "name": "Title verification",
  "description": "",
  "id": "title-verification",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "When the application is launched, title should be \"African Kit\"",
  "description": "",
  "id": "title-verification;when-the-application-is-launched,-title-should-be-\"african-kit\"",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "I launch the App",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "App is launched",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "Assert the Page name is African Kit",
  "keyword": "Then "
});
formatter.match({
  "location": "PageTitle.iLaunchTheApp()"
});
formatter.result({
  "duration": 420288875,
  "status": "passed"
});
formatter.match({
  "location": "PageTitle.whenAppIsLaunched()"
});
formatter.result({
  "duration": 54487,
  "status": "passed"
});
formatter.match({
  "location": "PageTitle.verifyPageName()"
});
formatter.result({
  "duration": 6645211254,
  "status": "passed"
});
formatter.uri("2_musicPlayPause.feature");
formatter.feature({
  "line": 1,
  "name": "Play \u0026 Pause Music",
  "description": "",
  "id": "play-\u0026-pause-music",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "When the music is played/paused, progress bar should be enabled/disabled",
  "description": "",
  "id": "play-\u0026-pause-music;when-the-music-is-played/paused,-progress-bar-should-be-enabled/disabled",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "I launch the App",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I Tap to play and tap to stop playing each loaded pads",
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "I tap on music pad",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "Music should be played \u0026 progress bar should be displayed",
  "keyword": "Then "
});
formatter.step({
  "line": 9,
  "name": "I tap on pause",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "Music should be paused \u0026 progress bar should be removed",
  "keyword": "Then "
});
formatter.match({
  "location": "PageTitle.iLaunchTheApp()"
});
formatter.result({
  "duration": 1016986,
  "status": "passed"
});
formatter.match({
  "location": "MusicPlayPause.playandPauseAllTrack()"
});
formatter.result({
  "duration": 179869482323,
  "status": "passed"
});
formatter.match({
  "location": "MusicPlayPause.iTapOnMusicPad()"
});
formatter.result({
  "duration": 12251185811,
  "status": "passed"
});
formatter.match({
  "location": "MusicPlayPause.musicShouldBePlayed()"
});
formatter.result({
  "duration": 1139808677,
  "status": "passed"
});
formatter.match({
  "location": "MusicPlayPause.iTapOnPause()"
});
formatter.result({
  "duration": 13364561872,
  "status": "passed"
});
formatter.match({
  "location": "MusicPlayPause.musicShouldBePuased()"
});
formatter.result({
  "duration": 1097651829,
  "status": "passed"
});
formatter.uri("3_multipleMusicPlayPause.feature");
formatter.feature({
  "line": 1,
  "name": "Play \u0026 Pause Music for multiple pads",
  "description": "",
  "id": "play-\u0026-pause-music-for-multiple-pads",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "When the music is played/paused for multiple pads, play/pause buttons should be displayed accordingnly",
  "description": "",
  "id": "play-\u0026-pause-music-for-multiple-pads;when-the-music-is-played/paused-for-multiple-pads,-play/pause-buttons-should-be-displayed-accordingnly",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "I launch the App",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I tap on music pad",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "Music should be played \u0026 progress bar should be displayed",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "I tap on 2nd music pad",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "Music should be played for 2nd pad",
  "keyword": "Then "
});
formatter.step({
  "line": 10,
  "name": "I tap on pause for 2nd pad",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "Music should be paused \u0026 progress bar should be removed",
  "keyword": "Then "
});
formatter.match({
  "location": "PageTitle.iLaunchTheApp()"
});
formatter.result({
  "duration": 1524345,
  "status": "passed"
});
formatter.match({
  "location": "MusicPlayPause.iTapOnMusicPad()"
});
formatter.result({
  "duration": 12014676704,
  "status": "passed"
});
formatter.match({
  "location": "MusicPlayPause.musicShouldBePlayed()"
});
formatter.result({
  "duration": 1070512937,
  "status": "passed"
});
formatter.match({
  "location": "MusicPlayPause.iTapOnSecondPad()"
});
formatter.result({
  "duration": 12142893453,
  "status": "passed"
});
formatter.match({
  "location": "MusicPlayPause.musicMultiplePad()"
});
formatter.result({
  "duration": 1108658498,
  "status": "passed"
});
formatter.match({
  "location": "MusicPlayPause.iPasueSecondPad()"
});
formatter.result({
  "duration": 13287045466,
  "status": "passed"
});
formatter.match({
  "location": "MusicPlayPause.musicShouldBePuased()"
});
formatter.result({
  "duration": 1098402673,
  "status": "passed"
});
formatter.uri("5_slider.feature");
formatter.feature({
  "line": 1,
  "name": "Move slider",
  "description": "",
  "id": "move-slider",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "On moving slider, values should be changed",
  "description": "",
  "id": "move-slider;on-moving-slider,-values-should-be-changed",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "I launch the App",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I tap on the number beside the green light on the top left",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "I should see a slider pop up",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "I \"increase\" slide to 170, I should see the same number reflect on the app",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "I \"decrease\" slide to 90, I should see the same number reflect on the app",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "I tap on back button, slider pop up should close",
  "keyword": "And "
});
formatter.match({
  "location": "PageTitle.iLaunchTheApp()"
});
formatter.result({
  "duration": 1068799,
  "status": "passed"
});
formatter.match({
  "location": "SliderSteps.iTapOnNumberSlider()"
});
formatter.result({
  "duration": 15645978598,
  "status": "passed"
});
formatter.match({
  "location": "SliderSteps.iShouldSeeSlider()"
});
formatter.result({
  "duration": 8711598336,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "increase",
      "offset": 3
    },
    {
      "val": "170",
      "offset": 22
    }
  ],
  "location": "SliderSteps.slideToNumner(String,int)"
});
formatter.result({
  "duration": 75509448640,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "decrease",
      "offset": 3
    },
    {
      "val": "90",
      "offset": 22
    }
  ],
  "location": "SliderSteps.slideToNumner(String,int)"
});
formatter.result({
  "duration": 235117963921,
  "status": "passed"
});
formatter.match({
  "location": "SliderSteps.goBack()"
});
formatter.result({
  "duration": 10178014494,
  "status": "passed"
});
formatter.uri("6_volumeNorm.feature");
formatter.feature({
  "line": 1,
  "name": "Vol Norm",
  "description": "",
  "id": "vol-norm",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "VOL NORM should be enabled/disabled",
  "description": "",
  "id": "vol-norm;vol-norm-should-be-enabled/disabled",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "I launch the App",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I tap the VOL NORM button",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "assert the button is deactivated",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "when I tap the button again",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "assert the button is activated back",
  "keyword": "Then "
});
formatter.match({
  "location": "PageTitle.iLaunchTheApp()"
});
formatter.result({
  "duration": 795242,
  "status": "passed"
});
formatter.match({
  "location": "VolNorm.iTapOnVolNorm()"
});
formatter.result({
  "duration": 1792246352,
  "status": "passed"
});
formatter.match({
  "location": "VolNorm.verifyButtonUnchecked()"
});
formatter.result({
  "duration": 974673277,
  "status": "passed"
});
formatter.match({
  "location": "VolNorm.iTapButtonAgain()"
});
formatter.result({
  "duration": 1647947126,
  "status": "passed"
});
formatter.match({
  "location": "VolNorm.verifyButtonCheckedBack()"
});
formatter.result({
  "duration": 1769159965,
  "status": "passed"
});
formatter.uri("7_addFreeSamples.feature");
formatter.feature({
  "line": 1,
  "name": "Add Free Samples",
  "description": "",
  "id": "add-free-samples",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Free samples should be added to music pad",
  "description": "",
  "id": "add-free-samples;free-samples-should-be-added-to-music-pad",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "I launch the App",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "I tap the Edit page",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "I should see the edit page show a Free Samples folder",
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "I tap to open the folder",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "I can see audio contents of the folder",
  "keyword": "Then "
});
formatter.step({
  "line": 9,
  "name": "I tap an empty virtual pad and I tap the first audio content in the Free Samples folder and assert the name of the audio content loaded is the same name of the audio content lapped in free sample folder.",
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "Tap the Edit Pad button again",
  "keyword": "Then "
});
formatter.step({
  "line": 11,
  "name": "tap to play and tap to stop each loaded pads Assert I can see all icons and each loaded pads are playing audio.",
  "keyword": "Then "
});
formatter.match({
  "location": "PageTitle.iLaunchTheApp()"
});
formatter.result({
  "duration": 549560,
  "status": "passed"
});
formatter.match({
  "location": "AddFreeSamples.iTapOnEditPad()"
});
formatter.result({
  "duration": 1743806769,
  "status": "passed"
});
formatter.match({
  "location": "AddFreeSamples.verifyFolderDisplayed()"
});
formatter.result({
  "duration": 1115232066,
  "status": "passed"
});
formatter.match({
  "location": "AddFreeSamples.openFolder()"
});
formatter.result({
  "duration": 1902782848,
  "status": "passed"
});
formatter.match({
  "location": "AddFreeSamples.seeAudioContent()"
});
formatter.result({
  "duration": 18921327514,
  "status": "passed"
});
formatter.match({
  "location": "AddFreeSamples.tapOnEmptyPAD()"
});
