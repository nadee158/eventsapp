'use strict';
define(['app'], function (app) {
	app.controller('SignInController', ['$scope', '$rootScope','$location', 
	                                    'SecurityServiceFactory', 'CommonStorageFactory','NotificationServiceFactory', 
	                                    'PubSub','Constants','Page', 'CommonServiceFactory',
	    function ($scope, $rootScope,$location, 
	    		SecurityServiceFactory,CommonStorageFactory,NotificationServiceFactory,
	    		PubSub,Constants,Page,CommonServiceFactory) {
			
		    Page.setTitle("SignIn");
			$scope.authenticated = false;
			$scope.token; // For display purposes only
	  
	        var baseUrl=$rootScope.baseUrl;
	        
	        $scope.login = function() {
	        
	          
	          var storageKey = Constants.Keys.authtoken;
	          var userStorageKey= Constants.Keys.userobject;
	          
	          var response=SecurityServiceFactory.signIn($scope.user,baseUrl);
	         
	                      
	          response.success(function(data, status, headers, config) {
	        	  
	        	    if(data.suid && data.suid>0){
	        	    	
	        	    	$scope.authenticated = true;
		                CommonStorageFactory.store(storageKey, headers(Constants.Headers.xauthtoken));
		      			// For display purposes only
		      			$scope.token = JSON.parse(atob(CommonStorageFactory.retrieve(storageKey).split('.')[0]));
		      			
		      			$scope.SignInResponse=data;
		      			      			
		      			NotificationServiceFactory.info("Welcome " + $scope.SignInResponse.fN + "!");
		      				      			
		      			CommonStorageFactory.store(userStorageKey, $scope.SignInResponse);
		      			
		      			PubSub.publish(Constants.Events.loggedin, {
		      				userObject: $scope.SignInResponse
		      			});
		      			
		      			$location.path("/home");
		      			
	        	    }else{
	        	    	
	        	    	var msg=data.message;
	        	    	if(data.error && data.error.message){
	        	    		msg=msg + ' - ' + data.error.message;
	        	    	}
	        	    	NotificationServiceFactory.error(msg);
	        	    }
	        	  
	                
	            }).error(function(data, status, headers, config){
	            	CommonServiceFactory.handleResponseError(data.status);
	            	console.error('Error while authenticating user ' + data.message);
	            })
	        }
	          
	          
	  } 
	
	]);
});




