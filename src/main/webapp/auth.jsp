<head>
  <link rel="stylesheet" href="style.css">
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
div.error_messages > b {
    color: red;
    font-style: italic;
}
</style>

<div class="wrapper">
	<div class="container">
		<h1>Welcome</h1>
				
		<form class="form" action="/ada_project_war/auth" method="POST">
			<div>
				<input id="femail" type="text" name="email" placeholder="e-mail"/>
			</div>
			<div>
				<input id="fpassword" type="password" name="password" placeholder="password"/>
			</div>
			<div style="clear: both"></div>
			<button id="login-button" type="submit" name="dosubmit" value="Submit"/> login </button>
		</form>
	</div>
	
	<ul class="bg-bubbles">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
</div>
<script> document.body.style.zoom="150%" </script>
<script src="script.js"></script>
