<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
	<meta charset="utf-8">
	<title>Registration</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- Animate.css -->
	<link rel="stylesheet" href="css/animate.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="css/bootstrap.css">
	<!-- Superfish -->
	<link rel="stylesheet" href="css/superfish.css">

	<link rel="stylesheet" href="css/style.css">

	<link rel="import" href="navigation.jsp">

	<!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

	<jsp:include page="navigation.jsp" />

</head>
<body>

	<div class="container-fluid">
		<div class="row">
			<div id="fh5co-blog-section">
				<div class="container">
					<div class="row">
						<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-center fh5co-table">
							<div class="fh5co-intro fh5co-table-cell animate-box">
								<h1 class="text-center">Registration</h1>
								<div class="animate-box">
									<form class="registration-form">
										<div class="form-group">
											<input type="login" class="form-control" id="registrationLogin" placeholder="Login"
												   oninput="check_color(this)">
										</div>
										<div class="form-group">
											<input type="email" class="form-control" id="registrationEmail" placeholder="Email">
										</div>
										<div class="form-group">
											<input type="Password" class="form-control" id="registrationPassword1" placeholder="Password">
										</div>
										<div class="form-group">
											<input type="Password" class="form-control" id="registrationPassword2" placeholder="Repeat password"
												   oninput="check_matching_passwords(this.form)">
										</div>
										<div class="form-group">
											<input type="Registration" id="btn-submit" class="btn btn-send-message btn-md"
												   value="Registration" onclick="post_registration()">
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<!-- Stellar -->
	<script src="js/jquery.stellar.min.js"></script>
	<!-- Superfish -->
	<script src="js/hoverIntent.js"></script>
	<script src="js/superfish.js"></script>

	<!-- Main JS (Do not remove) -->
	<script src="js/main.js"></script>

	<script src="ajax/registration.js"></script>

</body>
</html>

