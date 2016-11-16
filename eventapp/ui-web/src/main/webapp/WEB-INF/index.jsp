<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<!-- TO STOP CACHING -->
	<meta http-equiv="cache-control" content="max-age=0" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<title ng-bind="'EventsApp | '+title"></title>
	
		<link rel="icon" href="favicon.ico" />
		
		<link rel="stylesheet"	href="<c:url value='assets/lib/bootstrap-3.3.6/css/bootstrap.min.css?v=${v}' />" />
		<link rel="stylesheet"	href="<c:url value='assets/lib/toastr-0.4.6/css/toastr.css?v=${v}' />" />
		<link rel="stylesheet"	href="<c:url value='assets/lib/jquery-datatables-1.10.11/css/jquery.dataTables.css?v=${v}' />" />
		<link rel="stylesheet"	href="<c:url value='assets/lib/angular-splash-0.0.1/css/splash.css?v=${v}' />" />
		<link rel="stylesheet"	href="<c:url value='assets/lib/nvd3-1.8.2/css/nv.d3.min.css?v=${v}' />" />
		<link rel="stylesheet"	href="<c:url value='assets/lib/angularjs-slider-2.13.0/css/rzslider.css?v=${v}' />" />
		<link rel="stylesheet"	href="<c:url value='assets/lib/select2-4.0.2/css/select2.min.css?v=${v}' />" />
		<link rel="stylesheet"	href="<c:url value='assets/lib/dropzone-4.3.0/css/dropzone.min.css?v=${v}' />" />
		<link rel="stylesheet"	href="<c:url value='assets/lib/angular-material-designlte/css/material.css?v=${v}' />" />
		<link rel="stylesheet"	href="<c:url value='assets/lib/angular-material-designlte/css/dataTables.material.css?v=${v}' />" />
		
		<!-- Font Awesome -->
		<link rel="stylesheet"	href="<c:url value='assets/lib/font-awesome-4.6.3/css/font-awesome.min.css?v=${v}' />" />
		<!-- Theme style -->
		<link rel="stylesheet"	href="<c:url value='assets/app/styles/_/AdminLTE.css?v=${v}' />" />
		<!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
		<link rel="stylesheet"	href="<c:url value='assets/app/styles/_/skins/_all-skins.css?v=${v}' />" />
		<!-- Iconic bootstrap icons -->
		<link rel="stylesheet"	href="<c:url value='assets/lib/ionicons-2.0.1/css/ionicons.min.css?v=${v}' />" />
		<link rel="stylesheet"	href="<c:url value='assets/lib/angular-material-1.1.0/css/angular-material.min.css?v=${v}' />" />
		<link rel="stylesheet"	href="<c:url value='assets/lib/angular-material-fileinput/css/lf-ng-md-file-input.min.css?v=${v}' />" />
		
		<!-- Angular material datatable -->
		<link rel="stylesheet"	href="<c:url value='assets/lib/md-angular-material-datatables/md-data-table.min.css?v=${v}' />" />
		<link rel="stylesheet"	href="<c:url value='assets/lib/angular-loading-bar-0.5.1/css/loading-bar.css?v=${v}' />" />
		<link rel="stylesheet"	href="<c:url value='assets/lib/visual-captcha-0.0.7/css/visualcaptcha.css?v=${v}' />" />
		
	<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	
	<style type="text/css">
		body {
			top:0px !important;
		}
		.md-dialog-container{
			z-index:1000;
		}
		._md-select-menu-container{
		   z-index:2000;
		}
		.dataTable td {
		    word-break: break-all;
		    white-space: normal;
		}
		.dataTable th {
		   text-align: center;
		}
		.mdl-data-table{white-space: normal;}
		.md-chips{
		 font-size: 12px;
		}
		.md-input-validation-message{
		 color: #ff0000;
		}
		.custom-chips md-chip {
     position: relative; 
  }
   .custom-chips md-chip .md-chip-remove-container {
    position: absolute;
    right: 4px;
    top: 4px;
    margin-right: 0;
    height: 24px; 
   }
    
	.custom-chips md-chip .md-chip-remove-container button.vegetablechip {
      position: relative;
      height: 24px;
      width: 24px;
/*       line-height: 30px; */
      text-align: center;
      background: rgba(0, 0, 0, 0.3);
      border-radius: 50%;
      border: none;
      box-shadow: none;
      padding: 0;
      margin: 0;
      transition: background 0.15s linear;
      display: block; 
	}
    .custom-chips md-chip .md-chip-remove-container button.vegetablechip md-icon {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate3d(-50%, -50%, 0) scale(0.7);
        color: white;
        fill: white; 
	}
    .custom-chips md-chip .md-chip-remove-container button.vegetablechip:hover, 
	.custom-chips md-chip .md-chip-remove-container button.vegetablechip:focus {
        background: rgba(255, 0, 0, 0.8); 
	}
	.custom-chips md-chips-wrap.md-removable md-chip md-chip-template {
		padding-right: 5px; 
	}
		
	</style>
	
	 <script type="text/javascript"> 
	// 	(function(i, s, o, g, r, a, m) {
	// 		i['GoogleAnalyticsObject'] = r;
	// 		i[r] = i[r] || function() {
	// 			(i[r].q = i[r].q || []).push(arguments)
	// 		}, i[r].l = 1 * new Date();
	// 		a = s.createElement(o), m = s.getElementsByTagName(o)[0];
	// 		a.async = 1;
	// 		a.src = g;
	// 		m.parentNode.insertBefore(a, m)
	// 	})(window, document, 'script', '//www.google-analytics.com/analytics.js',
	// 			'ga');
	
	// 	ga('create', 'UA-74239448-1', {
	// 		'cookieDomain' : 'www.cwpaaa.com'
	// 	});
</script> 
	<link themer-link>
	</head>
	<body dir="ltr" class="hold-transition skin-green sidebar-mini fixed" layout="row">
	
		<div class="wrapper" style="width: inherit;">
			<div id="loading-bar-container"></div>
			<div id="header" ng-include src="'app/header.html'"></div>
			<div id="sidebar" ng-include src="'app/sidebar.html'"></div>

			<div class="content-wrapper" style="height: 390px; width:auto; overflow-y: auto;" layout="column">
					<toaster-container
						toaster-options="{'time-out': 3000, 'close-button':true}"></toaster-container>
					<md-content md-no-momentum  md-no-flicker>
						<div ng-view></div>
					</md-content>
				
			</div>
				<div id="footer" ng-include src="'app/footer.html'"></div>

		</div>
		<script type="text/javascript">
			var APP_VERSION = "${v}";
		</script>
		
		<script type="text/javascript"	src="<c:url value='assets/lib/requirejs-2.2/require.js?v=${v}' />" data-main="<c:url value='app/main.js?v=${v}' />"></script>
		
	</body>
</html>


