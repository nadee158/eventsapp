'use strict';
define(['app'], function (app) {
	app.controller('ResetPasswordController', ['$scope', '$rootScope','$location', '$translate',
	                                    'SecurityServiceFactory', 'CommonStorageFactory','NotificationServiceFactory', 
	                                    'PubSub','Constants','Page','ModalDialogServiceFactory', '$filter','CommonServiceFactory',
	    function ($scope, $rootScope, $location, $translate,
	    		SecurityServiceFactory, CommonStorageFactory, NotificationServiceFactory,
	    		PubSub, Constants, Page, ModalDialogServiceFactory, $filter,CommonServiceFactory) {
		
		    var $translate_filter = $filter('translate');
		    Page.setTitle($translate_filter('usrmgt.reset_password.label.reset_password'));
			
			$scope.authenticated = false;
			$scope.token; // For display purposes only
	  
	        var baseUrl=$rootScope.baseUrl;
	        
	        $scope.cancel= function() {
	        	$location.path("/login");
	        }
	        
	        $scope.resetPassword = function() {
	        	
	        	angular.forEach($scope.resetPwForm.$error.required, function(field) {
	        	    field.$setDirty();
	        	});
	        	
	        	if($scope.resetPwForm.$valid && $scope.captcha.getCaptchaData().valid) {
	        		
	        	  var captchaData = $scope.captcha.getCaptchaData();
		          var captchaValidationRequest={};
		          captchaValidationRequest.captchaData=captchaData;
		        	
		          var captchaResponse=CommonServiceFactory.validateCaptcha(captchaValidationRequest,baseUrl);
                     
		          captchaResponse.success(function(data, status, headers, config) {

		        	    var validStatus=data.validStatus;
	                    if(validStatus==1){
	                    	ModalDialogServiceFactory.confirmBox(
	      	              		  $translate_filter('common.notification.message.CONFIRM_SUBMIT_TITLE'), 
	      	              		  $translate_filter('common.notification.message.CONFIRM_SUMIT_CONTENT'), 
	      	              		  '', 
	      	              		  $translate_filter('common.button.text.SUBMIT'), 
	      	              		  $translate_filter('common.button.text.CANCEL'), 
	      	              		  resetPassword, 
	      	              		  $scope.resetPasswordRequest, 
	      	              		  null, 
	      	              		  null
	      	                );	                    	
	                    }else{
	                    	$scope.captcha.refresh();
	                    	ModalDialogServiceFactory.alert(
	      	              		  $translate_filter('common.notification.message.NOTIFY_FORM_VALIDATION_ERRORS_CAPTCHA'), 
	      	              		  $translate_filter('common.notification.message.NOTIFY_FORM_VALIDATION_ERRORS_CONTENT_CAPTCHA'), 
	      	              		  '', 
	      	              		  $translate_filter('common.button.text.OK'), 
	      	              		  null, 
	      	              		  null
	      	                );
	                    }
		      			
		                
		            }).error(function(data, status, headers, config){
		            	$scope.captcha.refresh();
		            	NotificationServiceFactory.error('Error while validating captcha!');
		            	console.error('Error while validating captcha!');
		            })
		        	
	        	    
	        	}else{
	        		$scope.captcha.refresh();
	        		ModalDialogServiceFactory.alert(
	              		  $translate_filter('common.notification.message.NOTIFY_FORM_VALIDATION_ERRORS'), 
	              		  $translate_filter('common.notification.message.NOTIFY_FORM_VALIDATION_ERRORS_CONTENT'), 
	              		  '', 
	              		  $translate_filter('common.button.text.OK'), 
	              		  null, 
	              		  null
	                );
	        	} 
	        }
	        
	        function resetPassword(resetPasswordRequest){
	        	
	        	var currentLang = $translate.proposedLanguage() || $translate.use();
	        	if(currentLang==Constants.Languages.si){
	        		resetPasswordRequest.language='si';
	        	}else if(currentLang==Constants.Languages.ta){
	        		resetPasswordRequest.language='ta';
	        	}else{
	        		resetPasswordRequest.language='en';
	        	}
	        	
		          var userStorageKey= Constants.Keys.userobject;
		          var userDTO=CommonStorageFactory.retrieve(userStorageKey);
		          
		          if(userDTO){
		        	  resetPasswordRequest.userId=userDTO.suid;
		          }
		          
		          var response=SecurityServiceFactory.resetPassword(resetPasswordRequest,baseUrl);
		                      
		          response.success(function(data, status, headers, config) {
		      			// For display purposes only
		      			$scope.resetPasswordResponse=data;
		      			
		      			if(data.status==200){
		      				NotificationServiceFactory.info("Password was reset successfully!");
		      				$location.path("/login");
		      			}else{
		      				NotificationServiceFactory.error(data.message);
		      			}
		                
		            }).error(function(data, status, headers, config){
		            	CommonServiceFactory.handleResponseError(data.status);
		            	console.error('Error while changing password!');
		            })
	        	
	        }
	        
	        var captchaUrl=baseUrl + '/Captcha';
	        $scope.captchaOptions = {
                imgPath: 'assets/lib/visual-captcha-0.0.7/img/',
                captcha: {
                    numberOfImages: 5,
                    url:captchaUrl
                },
                init: function ( captcha ) {
                    $scope.captcha = captcha;
                }
            };

            $scope.isVisualCaptchaFilled = function() {
                if ( $scope.captcha.getCaptchaData().valid ) {
                    window.alert( 'visualCaptcha is filled!' );
                } else {
                    window.alert( 'visualCaptcha is NOT filled!' );
                }
            };
	          
	          
	  } 
	
	]);
});




