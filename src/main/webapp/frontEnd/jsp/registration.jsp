<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
	<meta charset="utf-8">
	<title>Registration</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- Animate.css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
	<!-- Superfish -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/superfish.css">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

	<link rel="import" href="${pageContext.request.contextPath}/navigation.jsp">

	<!-- Modernizr JS -->
	<script src="${pageContext.request.contextPath}/js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
	<![endif]-->

	<jsp:include page="/frontEnd/jsp/navigation.jsp" />

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
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="${pageContext.request.contextPath}/js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="${pageContext.request.contextPath}/js/jquery.waypoints.min.js"></script>
	<!-- Stellar -->
	<script src="${pageContext.request.contextPath}/js/jquery.stellar.min.js"></script>
	<!-- Superfish -->
	<script src="${pageContext.request.contextPath}/js/hoverIntent.js"></script>
	<script src="${pageContext.request.contextPath}/js/superfish.js"></script>

	<!-- Main JS (Do not remove) -->
	<script src="${pageContext.request.contextPath}/js/main.js"></script>

	<script src="${pageContext.request.contextPath}/ajax/registration.js"></script>

</body>
</html>

