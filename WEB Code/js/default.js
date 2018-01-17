var playBtn = $(".play");
var stopBtn = $(".stop");
var playlist = $(".playlist");
var playlistToggleBtn = $(".toggle-list");

// play&stop button toggle 
playBtn.click( function() {
	playBtn.css("display","none");
	stopBtn.css("display","inline");
});
stopBtn.click( function() {
	playBtn.css("display","inline");
	stopBtn.css("display","none");
});

// play list toggle
playlistToggleBtn.click( function() {
	playlist.toggleClass("show");
});