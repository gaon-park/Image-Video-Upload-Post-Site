<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/InstagramTest/css/carouselcss.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!--Bootsrap 4 CDN-->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<!--Fontawesome CDN-->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">

<meta charset="UTF-8">
<title>Insert title here</title>

<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id"
	content="309243741092-g07gfgi2epspcu0983rjklat7qpspv2l.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js?onload=renderButton"
	async defer></script>

</head>
<script>
	function onSuccess(googleUser) {
		
		var profile = googleUser.getBasicProfile();
		console.log("Name: " + profile.getName());
		console.log("Email: " + profile.getEmail());

		//document.getElementById("id").value = profile.getEmail();	
		location.href = "/InstagramTest/main.do?action=GOOGLELOGIN&id="
				+ profile.getEmail() + "&name=" + profile.getName();
	}
	function onFailure(error) {
		console.log(error);
	}
	function renderButton() {
		gapi.load('auth2', function() {
			gapi.auth2.init();
		});
		 gapi.signin2.render('my-signin2', {
			'scope' : 'profile email',
			'width' : 240,
			'height' : 50,
			'longtitle' : true,
			'theme' : 'dark',
			'onsuccess' : onSuccess,
			'onfailure' : onFailure
		});
		//gapi.signin2.render('my-signin2', additionalParams);
	}

	function onSignIn(googleUser) {
		var profile = googleUser.getBasicProfile();
		console.log("Name: " + profile.getName());
		console.log("Email: " + profile.getEmail());

		document.getElementById("user_id").value = profile.getEmail();

		/* location.href = "/InstagramTest/main.do?action=LOGIN&id="
				+ profile.getEmail() + "&name=" + profile.getName(); */
	}
	function signOut() {
		var auth2 = gapi.auth2.getAuthInstance();
		auth2.signOut().then(function() {
			console.log('User signed out.');

		});
		auth2.disconnect();
		location.href = "/InstagramTest/main.do?action=LOGOUT";

	}
	function onLoad() {
		gapi.load('auth2', function() {
			gapi.auth2.init();
		});
	}
</script>
<body>
	<a href="#" onclick="signOut();">Sign out</a>
</body>
</html>