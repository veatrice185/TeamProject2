var serach = $(".search-container"),
	recommend = $(".recommend-container"),
	mylist = $(".mylist-container"),
	login = $(".login-container"),
	join = $(".join-container"),
	page = $(".jsp-page");

$(".menu-mylist, .menu-recommend, .menu-login, .menu-join, .menu-search").click( function() {
	var select = $(this).attr("value")+"-container";
	page.css("display","none");
	$("." + select).css("display","block");
	console.log(select);
})