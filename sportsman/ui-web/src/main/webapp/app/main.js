require
		.config({
			baseUrl : '/ui-web/app',
			urlArgs : "v=" + APP_VERSION,
			paths : {
				'require-domready' : '../assets/lib/requirejs-domready-2.0.1/domReady',
				'angular' : '../assets/lib/angularjs-1.5.8/angular.min',
				'angular-route' : '../assets/lib/angular-route-1.4.9/angular-route',
				'bootstrap' : '../assets/lib/bootstrap-3.3.6/js/bootstrap.min',
				'jquery' : '../assets/lib/jquery-1.12.0/jquery.min',
				'apptheme' : 'apptheme',
				'slimscroll' : '../assets/lib/slimscroll-1.3.3/jquery.slimscroll.min',
				'datatables' : '../assets/lib/jquery-datatables-1.10.11/js/jquery.dataTables',
				'toastr' : '../assets/lib/toastr-0.4.6/js/toastr.min',
				'angular-themer' : '../assets/lib/angular-themer-0.3.0/themer.min',
				'd3' : '../assets/lib/d3-3.5.16/d3',
				'nvd3' : '../assets/lib/nvd3-1.8.2/js/nv.d3',
				'angular_nvd3' : '../assets/lib/angular-nvd3-1.0.6/angular-nvd3',
				'ngAnimate' : '../assets/lib/angular-animate-1.5.8/angular-animate',
				'ngStorage' : '../assets/lib/ngStorage-0.3.10/ngStorage.min',
				'ngAria' : '../assets/lib/angular-aria-1.5.8/angular-aria.min',
				'ngMessages' : '../assets/lib/angular-messages-1.5.8/angular-messages.min',
				'ngMaterial' : '../assets/lib/angular-material-1.1.0/js/angular-material.min',
				'ui_bootstrap' : '../assets/lib/ui-bootstrap-1.1.2/ui-bootstrap-tpls-1.1.2.min',
				'angular-datatables' : '../assets/lib/angular-datatables-0.5.3/js/angular-datatables',
				'xtForm' : '../assets/lib/xtform-1.0/xtForm',
				'jquery-ui' : '../assets/lib/jquery-ui-1.12/js/jquery-ui.min',
				'moment' : '../assets/lib/moment-2.9.0/moment-with-locales',
				'bootstrap-datetimepicker' : '../assets/lib/bootstrap-datetimejs-4.15.35/bootstrap-datetimepicker',
				'select2' : '../assets/lib/select2-4.0.2/js/select2.min',
				'rzslider' : '../assets/lib/angularjs-slider-2.13.0/rzslider',
				'dropzone' : '../assets/lib/dropzone-4.3.0/js/dropzone',
				'ngDropzone' : '../assets/lib/dropzone-4.3.0/js/ngDropzone',
				'lfNgMdFileInput':'../assets/lib/angular-material-fileinput/js/lf-ng-md-file-input.min',
				'PubSub'   : '../assets/lib/angular-pubsub-0.0.1/angular-pubsub',
				'datatable_material':'../assets/lib/angular-material-designlte/js/dataTables.material.min',
				'angular-loading-bar':'../assets/lib/angular-loading-bar-0.5.1/js/loading-bar',
				'ng-Loader':'../assets/lib/angular-loader/js/ngLoader',
				'angular-translate':'../assets/lib/angular-translate-2.11.0/angular-translate.min',
				'angular-translate-loader-static-files':'../assets/lib/angular-translate-loader-static-files-2.11.0/angular-translate-loader-static-files.min',
				'pdf':'../assets/lib/angular-pdf-1.3.0/angular-pdf.min',
				'pdfjs':'../assets/lib/angular-pdf-1.3.0/pdf',
				'pdfworker':'../assets/lib/angular-pdf-1.3.0/pdf.worker',
				'visualcaptcha.angular':'../assets/lib/visual-captcha-0.0.7/visualcaptcha.angular',
				'angular-cookies':'../assets/lib/angular-cookies-1.5.8/angular-cookies.min',
				'angular-material-datetimepicker':'../assets/lib/angular-material-datetimepicker-1.0/js/angular-material-datetimepicker'
			},			
			shim : {
				'apptheme' : {
					deps : [ 'jquery', 'bootstrap', 'slimscroll' ]
				},
				'app' : {
					deps : [ 'jquery', 'angular', 'angular-route', 'bootstrap',
							'angular-themer', 'apptheme','ngMaterial'],
				},
				'angular-route' : {
					deps : [ 'angular' ]
				},
				'angular-themer' : {
					deps : [ 'angular' ]
				},
				'bootstrap' : {
					deps : [ 'jquery' ]
				},
				'toastr' : {
					deps : [ 'jquery' ],
					exports : "toastr"
				},
				'd3' : {
					deps : [ 'angular' ],
					exports : "d3"
				},
				'nvd3' : {
					deps : [ 'angular', 'd3' ],
					exports : "nvd3"
				},
				'angular_nvd3' : {
					deps : [ 'angular', 'd3', 'nvd3' ],
					exports : "angular_nvd3"
				},
				'ngAnimate' : {
					deps : [ 'angular' ]
				},
				'ngAria' : {
					deps : [ 'angular' ]
				},
				'ngMessages' : {
					deps : [ 'angular' ]
				},
				'ui_bootstrap' : {
					deps : [ 'angular' ]
				},
				'ngMaterial' : {
					deps : [ 'angular', 'ngAnimate', 'ngAria', 'ngMessages']
				},
				'datatable_material':{
					deps:['jquery']
				},
				'datatables':{
					deps:['jquery', 'angular']
				},
				'angular-datatables' : {
					deps : [ 'jquery', 'angular', 'datatables','ngMaterial'],
					exports : 'angular-datatables'
				},
				'ngStorage' : {
					deps : [ 'angular' ]
				},
				'xtForm' : {
					deps : [ 'angular', 'jquery', 'jquery-ui' ]
				},
				'dateTime-Picker' : {
					deps : [ 'jquery', 'moment', 'bootstrap',
							'bootstrap-datetimepicker' ],
					exports : 'dateTime-Picker'
				},
				'select2' : {
					deps : [ 'jquery' ],
					exports : '$.select2'
				},
				'dropzone' : {
					deps : [ 'jquery' ]
				},
				'ngDropzone' : {
				  deps : [ 'dropzone', 'angular']
				},
				'lfNgMdFileInput' : {
				  deps : [ 'ngMaterial' ]
				},
				'slimscroll':{
					deps:['jquery']
				},
				'PubSub':{
					deps:['angular']
				},
				'angular-loading-bar':{
					deps:['angular','ngAnimate']
				},
				'angular-translate':{
					deps:['angular']
				},
				'angular-translate-loader-static-files':{
					deps:['angular','angular-translate']
				},
				'pdf':{
				  deps:['angular', 'pdfjs', 'pdfworker']
				},
				'visualcaptcha.angular':{
					  deps:['angular','jquery']					 
				},
				'angular-cookies' : {
					deps : [ 'angular' ]
				},
				'angular-material-datetimepicker' : {
					deps : [ 'angular','ngMaterial','moment','ngAnimate','ngAria' ]
				},
				
			},
			priority : [ "angular" ]
		});

require([ 'app', 'require-domready', 'angular-datatables', 'angular-loading-bar','angular-translate-loader-static-files',
          'ngStorage', 'angular-translate', 'dropzone', 'ngDropzone', 'angular-cookies',
          'pdf', 'ngMessages', 'angular-material-datetimepicker', 
          '../common/services/common-service',
          '../common/services/commonstorage-service',
          '../common/services/security-service',
          '../common/services/notification-service',
          '../common/interceptors/tokenauthinterceptor',
          '../common/interceptors/apinterceptor',
          '../app/header',
          '../app/sidebar',
          '../common/services/common-constants',
          '../common/services/share-value-service',
          '../common/services/share-array-service',
          '../common/services/set-pagetitle',
          '../common/services/modal-dialog-service',
          '../common/directives/input_modifier',
          '../common/directives/access_restrict'
          ], function(
		app, domReady) {
	domReady(function(doc) {
		angular.bootstrap(doc, [ 'app', 'ngStorage']);
	});
});
