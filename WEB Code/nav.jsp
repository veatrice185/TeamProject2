<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<nav class="main-header navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand menu-recommend" href="#" value="recommend"><span class="glyphicon glyphicon-paperclip"></span>MusicClipper <span class="glyphicon glyphicon-headphones"></span></a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <form class="navbar-form navbar-left">
        <div class="form-group">
        	<div class="input-group">
         		<input type="text" class="form-control" id="searchForm" placeholder="Search">
         		  <span class="input-group-btn">
			        <button class="btn btn-success menu-search" id="searchBtn" type="button" value="search"><span class="glyphicon glyphicon-search"></span></button>
			      </span>
          	</div>
        </div>
      </form>
      <ul class="nav navbar-nav navbar-right">
      	<li class="menu-recommend" value="recommend"><a href="javascript:show()"><span class="glyphicon glyphicon-equalizer"></span> Recommend</a></li>
      	<li class="menu-mylist" value="mylist"><a href="#"><span class="glyphicon glyphicon-paperclip"></span> MyList</a></li>
        <li class="menu-login" value="login"><a href="#"><span class="glyphicon glyphicon-off"></span> Log In</a></li>
        <li class="menu-logout"><a href="#"><span class="glyphicon glyphicon-share"></span> Log Out</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>