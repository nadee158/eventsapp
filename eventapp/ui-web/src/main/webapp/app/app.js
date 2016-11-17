define(['routes','dependencyResolverFor','datatables','ngMaterial','select2','PubSub','lfNgMdFileInput','visualcaptcha.angular'], 
		 function(config, dependencyResolverFor,datatables,ngMaterial,select2,PubSub,lfNgMdFileInput,visualcaptcha_angular)
{
    var app = angular.module('app', ['ngRoute','angular-themer','datatables',
                                     'ngMaterial','PubSub', 'lfNgMdFileInput','angular-loading-bar',
                                     	'ngAnimate','pascalprecht.translate', 'pdf', 'thatisuday.dropzone','visualCaptcha','ngCookies']);

    app.config(
    [
        '$routeProvider',
        '$httpProvider',
        '$locationProvider',
        '$controllerProvider',
        '$compileProvider',
        '$filterProvider',
        '$provide',
        'themerProvider',
        'cfpLoadingBarProvider',
        '$translateProvider',
        'dropzoneOpsProvider',
        '$mdThemingProvider',
        

        function($routeProvider, $httpProvider, $locationProvider, $controllerProvider, $compileProvider,
        		$filterProvider, $provide,themerProvider,cfpLoadingBarProvider,
        		$translateProvider,dropzoneOpsProvider,$mdThemingProvider)
        {
          app.controller = $controllerProvider.register;
          app.directive  = $compileProvider.directive;
          app.filter     = $filterProvider.register;
          app.factory    = $provide.factory;
          app.service    = $provide.service;

            $locationProvider.html5Mode(false);
            
            $httpProvider.interceptors.push('TokenAuthInterceptorFactory');
            $httpProvider.interceptors.push('ApiInterceptorFactory');
            
            if(config.routes !== undefined)
            {
                angular.forEach(config.routes, function(route, path)
                {
                    $routeProvider.when(path, {templateUrl:route.templateUrl, resolve:dependencyResolverFor(route.dependencies), access:route.access});
                });
            }

            if(config.defaultRoutePaths !== undefined)
            {
                $routeProvider.otherwise({redirectTo:config.defaultRoutePaths});
            }
            
//            var styles = [
//          		{ key: 'DEFAULT', label: 'Default Theme', href: ['assets/app/styles/_/color.css','assets/app/styles/_/structure.css']},
//          		{ key: 'DARK', label: 'Dark Theme', href: ['assets/app/styles/theme-dark/color.css','assets/app/styles/theme-dark/structure.css']},
//          		{ key: 'LIGHT', label: 'LIGHT Theme', href: ['assets/app/styles/theme-light/color.css','assets/app/styles/theme-light/structure.css']}
//          	];
//
//          	themerProvider.setStyles(styles);
//          	themerProvider.setSelected(styles[1].key);
          	
          	
            //change default color for primary
            var green = $mdThemingProvider.extendPalette('blue', {
                '500': '4CAF50'
            });
            $mdThemingProvider.definePalette('blue', green);
            //change default color for warn
            var red = $mdThemingProvider.extendPalette('red', {
                '500': 'ff5800'
            });
            $mdThemingProvider.definePalette('red', red);
            $mdThemingProvider.theme('default').primaryPalette('blue').warnPalette('red').accentPalette('teal');  
            //here you change placeholder/foreground color.
            $mdThemingProvider.theme('default').foregroundPalette[3] = "rgba(0, 0, 0, 1)";
            
          	
          	cfpLoadingBarProvider.latencyThreshold = 1;
          	cfpLoadingBarProvider.parentSelector = '#loading-bar-container';
            cfpLoadingBarProvider.spinnerTemplate = '<div><span class="fa fa-spinner">Custom Loading Message...</div>';
            
            
             var simpleJavaPropertiesReader = function (lines) {
	            var result = {};
	            var patterns = [
	              /^\s*([^=:]+)\s*[:|=]\s*(.*)$/ // anything before = or : (the key), then everything unitl the end
	            ];
	            var quotePattern = /^"(.*)"$/;
	            for (var i = 0; i < lines.length; i++) {
	              for (var j = 0; j < patterns.length; j++) {	            	
	            	var match = lines[i].split("=");	               
	                if (match && match[0] && match[0].length > 0) {
	                  var key = match[0], value = match[1];	                 
	                  result[key] = value;
	                  break;
	                }
	              }
	            }
	            return result;
	          };
	          
	          // Register a loader for the static files
	          // So, the module will search missing translation tables under the specified urls.
	          // Those urls are [prefix][langKey][suffix].
	          $translateProvider.useStaticFilesLoader({
	            prefix: 'assets/app/l10n/',
	            suffix: '.properties',
	            $http: {
	              transformResponse: function (data, headersGetter, status) {
	                return simpleJavaPropertiesReader(data.split('\n'));
	              }
	            }
	          });
            
            // Tell the module what language to use by default
            $translateProvider.preferredLanguage('en_US');
            $translateProvider.useStorage('CommonStorageFactory');
            
        }
    ]);
    
    app.run(function ($rootScope, $location, CommonStorageFactory,Constants) {
       
    	var rootPath = $location.protocol() + '://' + $location.host() + ':' + $location.port();
        $rootScope.baseUrl =  rootPath + '/eventappservices';     
        $rootScope.reportBaseUrl = rootPath + '/eventappservices-reports';
        
		
		$rootScope.$on('$routeChangeStart', function (event, next, current) {
			if(!(next.access.isFree)){
				var userStorageKey= Constants.Keys.userobject; 
			    var userDTOObject=CommonStorageFactory.retrieve(userStorageKey);
				
				if(!(userDTOObject)) {				 
			        event.preventDefault();
			        $location.path('/login');
				}
			}
			

		});       
        
    });

   return app;
});


