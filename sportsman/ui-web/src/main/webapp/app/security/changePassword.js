'use strict';
define(['app'], function (app) {
	app.controller('ChangePasswordController', ['$scope', '$rootScope','$location', 
	                                    'SecurityServiceFactory', 'CommonStorageFactory','NotificationServiceFactory', 
	                                    'PubSub','Constants','Page','ModalDialogServiceFactory', '$translate','$filter','CommonServiceFactory',
	    function ($scope, $rootScope,$location, 
	    		SecurityServiceFactory,CommonStorageFactory,NotificationServiceFactory,
	    		PubSub,Constants,Page,ModalDialogServiceFactory,$translate,$filter,CommonServiceFactory) {
		
		    //var $translate = $filter('translate');
		    Page.setTitle($filter('translate')('usrmgt.change_password.label.change_password'));
			
			$scope.authenticated = false;
			$scope.token; // For display purposes only
	  
	        var baseUrl=$rootScope.baseUrl;
	        
	        $scope.cancel= function() {
	        	$location.path("/home");
	        }
	        
	        
	        
	        
	        $scope.changePassword = function() {
	        	
	        	angular.forEach($scope.chnagePwForm.$error.required, function(field) {
	        	    field.$setDirty();
	        	});
	        	
	        	if($scope.chnagePwForm.$valid) {
	        		
	        		ModalDialogServiceFactory.confirmBox(
	        				$filter('translate')('common.notification.message.CONFIRM_SUBMIT_TITLE'), 
	        				$filter('translate')('common.notification.message.CONFIRM_SUMIT_CONTENT'), 
	              		  	'', 
		              		$filter('translate')('common.button.text.SUBMIT'), 
		              		$filter('translate')('common.button.text.CANCEL'), 
		              		changePassword, 
		              		$scope.changePasswordRequest, 
		              		null, 
		              		null
	                );
	        	    
	        	}else{
	        		ModalDialogServiceFactory.alert(
	        			  $filter('translate')('common.notification.message.NOTIFY_FORM_VALIDATION_ERRORS'), 
	        			  $filter('translate')('common.notification.message.NOTIFY_FORM_VALIDATION_ERRORS_CONTENT'), 
	              		  '', 
	              		  $filter('translate')('common.button.text.OK'), 
	              		  null, 
	              		  null
	                );
	        	} 
	        }
	        
	        function changePassword(changePasswordRequest){
	        	
	        	var currentLang = $translate.proposedLanguage() || $translate.use();
	        	if(currentLang==Constants.Languages.si){
	        		changePasswordRequest.language='si';
	        	}else if(currentLang==Constants.Languages.ta){
	        		changePasswordRequest.language='ta';
	        	}else{
	        		changePasswordRequest.language='en';
	        	}
	        	
		          var userStorageKey= Constants.Keys.userobject;
		          var userDTO=CommonStorageFactory.retrieve(userStorageKey);
		          
		          if(userDTO){
		        	  changePasswordRequest.userId=userDTO.suid;
		          }
		          
		          var response=SecurityServiceFactory.changePassword(changePasswordRequest,baseUrl);
		                      
		          response.success(function(data, status, headers, config) {
		      			// For display purposes only
		      			$scope.ChangePasswordResponse=data;
		      			
		      			if(data.status==200){
		      				$location.path("/home");
		      				NotificationServiceFactory.info("Password was changed successfully!");
		      			}else{
		      				NotificationServiceFactory.error(data.message);
		      			}
		      			      			
		      			
		                
		            }).error(function(data, status, headers, config){
		            	CommonServiceFactory.handleResponseError(data.status);
		            	console.error('Error while changing password!');
		            })
	        	
	        }
	          
	          
	  } 
	
	]);
});




