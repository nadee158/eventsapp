<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

	<!-- TO STOP CACHING -->
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<!-- Tell the browser to be responsive to screen width -->
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<title>Events App</title>
  
  	<link rel="icon" href="favicon.ico" />
		
	<!-- Bootstrap 3.3.6 -->
	<link rel="stylesheet"	href="<c:url value='assets/lib/bootstrap-3.3.6/css/bootstrap.min.css?v=${v}' />" />
	<!-- Font Awesome -->
	<link rel="stylesheet"	href="<c:url value='assets/lib/font-awesome-4.6.3/css/font-awesome.min.css?v=${v}' />" />
	<!-- Ionicons -->
	<link rel="stylesheet"	href="<c:url value='assets/lib/ionicons-2.0.1/css/ionicons.min.css?v=${v}' />" />
	<!-- Theme style -->
    <link rel="stylesheet"	href="<c:url value='assets/app/styles/_/AdminLTE.css?v=${v}' />" />
    <!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
	<link rel="stylesheet"	href="<c:url value='assets/app/styles/_/skins/skin-blue.css?v=${v}' />" />
	
	<link rel="stylesheet"	href="<c:url value='assets/lib/toastr-0.4.6/css/toastr.css?v=${v}' />" />
	<link rel="stylesheet"	href="<c:url value='assets/lib/jquery-datatables-1.10.11/css/jquery.dataTables.css?v=${v}' />" />
	<link rel="stylesheet"	href="<c:url value='assets/lib/angular-splash-0.0.1/css/splash.css?v=${v}' />" />
	<link rel="stylesheet"	href="<c:url value='assets/lib/nvd3-1.8.2/css/nv.d3.min.css?v=${v}' />" />
	<link rel="stylesheet"	href="<c:url value='assets/lib/angularjs-slider-2.13.0/css/rzslider.css?v=${v}' />" />
	<link rel="stylesheet"	href="<c:url value='assets/lib/select2-4.0.2/css/select2.min.css?v=${v}' />" />
	<link rel="stylesheet"	href="<c:url value='assets/lib/dropzone-4.3.0/css/dropzone.min.css?v=${v}' />" />
	<link rel="stylesheet"	href="<c:url value='assets/lib/angular-material-designlte/css/material.css?v=${v}' />" />
	<link rel="stylesheet"	href="<c:url value='assets/lib/angular-material-designlte/css/dataTables.material.css?v=${v}' />" />
	
	<link rel="stylesheet"	href="<c:url value='assets/lib/angular-material-1.1.0/css/angular-material.min.css?v=${v}' />" />
	<link rel="stylesheet"	href="<c:url value='assets/lib/angular-material-fileinput/css/lf-ng-md-file-input.min.css?v=${v}' />" />
		
	<!-- Angular material datatable -->
	<link rel="stylesheet"	href="<c:url value='assets/lib/md-angular-material-datatables/md-data-table.min.css?v=${v}' />" />
	<link rel="stylesheet"	href="<c:url value='assets/lib/angular-loading-bar-0.5.1/css/loading-bar.css?v=${v}' />" />
	<link rel="stylesheet"	href="<c:url value='assets/lib/visual-captcha-0.0.7/css/visualcaptcha.css?v=${v}' />" />
  
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
  
</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<!-- Main Header -->
		<header class="main-header">
			<div id="header" ng-include src="'app/header.html'"></div>
		</header>

		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">
			<div id="lsb" ng-include src="'app/sidebar.html'"></div>
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<div ng-view></div>
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->
		<footer class="main-footer">
			<div id="ftr" ng-include src="'app/footer.html'"></div>
		</footer>
		
  	    
	</div>
	<!-- ./wrapper -->

	<script type="text/javascript">
	  var APP_VERSION = "${v}";
	</script>
		
	<script type="text/javascript"	src="<c:url value='assets/lib/requirejs-2.2/require.js?v=${v}' />" data-main="<c:url value='app/main.js?v=${v}' />"></script>
	
	<!-- Optionally, you can add Slimscroll and FastClick plugins.
	     Both of these plugins are recommended to enhance the
	     user experience. Slimscroll is required when using the
	     fixed layout. -->
	</body>
</html>
