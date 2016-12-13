'use strict';

define(['app'], function (app) {
	app.controller('AddSecurityUserController', ['$scope', '$rootScope','$location','$filter',
	                                            'SecurityUserServiceFactory',
	                                            'CommonStorageFactory','NotificationServiceFactory','PubSub','Constants','Page','ModalDialogServiceFactory','CommonServiceFactory',
	    function ($scope, $rootScope,$location,$filter, 
	    										SecurityUserServiceFactory,
	    										CommonStorageFactory,NotificationServiceFactory,PubSub,Constants,Page,ModalDialogServiceFactory,CommonServiceFactory) {
			
		    var $translate = $filter('translate');
		
			Page.setTitle($translate('usrmgt.security_user.pagetitle.ADD_SECURITY_USER'));
			
	  
	        var baseUrl=$rootScope.baseUrl;
	        
	        $scope.initializeSecurityUserPage = function() {
	        };
	        
	       	
	        $scope.createSecurityUser= function() {	        
	        	
	        	angular.forEach($scope.securityUserForm.$error.required, function(field) {
	        	    field.$setDirty();
	        	});
	        	
	        	if($scope.securityUserForm.$valid) {
	        		
	        		ModalDialogServiceFactory.confirmBox(
	              		  $translate('common.notification.message.CONFIRM_SUBMIT_TITLE'), 
	              		  $translate('common.notification.message.CONFIRM_SUMIT_CONTENT'), 
	              		  '', 
	              		  $translate('common.button.text.SUBMIT'), 
	              		  $translate('common.button.text.CANCEL'), 
	              		  submitSecurityUser, 
	              		  $scope.securityUserCreationRequest, 
	              		  null, 
	              		  null
	                );
	        	    
	        	}else{
	        		ModalDialogServiceFactory.alert(
	              		  $translate('common.notification.message.NOTIFY_FORM_VALIDATION_ERRORS'), 
	              		  $translate('common.notification.message.NOTIFY_FORM_VALIDATION_ERRORS_CONTENT'), 
	              		  '', 
	              		  $translate('common.button.text.OK'), 
	              		  null, 
	              		  null
	                );
	        	} 
	        };
	        
	        function submitSecurityUser(securityUserCreationRequest){
	        	//Do something
	        	var response=SecurityUserServiceFactory.createSecurityUser($scope.securityUserCreationRequest,baseUrl);		         
                
		        response.success(function(data, status, headers, config) {
		      		if(data.status==409){
		      			NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_SAVING_RECORD') + ' ' + $translate(data.message));
		      		}else{
		      			$scope.SecurityUserCreation=data;
		      			
		      			NotificationServiceFactory.info($translate('common.notification.message.SUCCESSFULLY_SAVED'));
		      			
		      			$location.path("/listsecurityusers");
		      		}	
	                
	            }).error(function(data, status, headers, config){
	            	NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_SAVING_RECORD'));
	            	console.error($translate('common.notification.message.ERROR_WHILE_SAVING_RECORD') + " " + data.message);
	            }) 
	        }
	        
	        
	        $scope.resetForm = function() {
		      	  ModalDialogServiceFactory.confirmBox(
		            		  'Confirm Form Reset', 
		            		  'Are you sure you want to clear the form content?', 
		            		  '', 
		            		  'Ok', 
		            		  $translate('common.button.text.CANCEL'), 
		            		  resetFormInner, 
		            		  null, 
		            		  null, 
		            		  null
		              );
		        };
		        
		        function resetFormInner(){
		      	  $scope.securityUserCreationRequest=new Object();
		        }
		        
		        $scope.exitForm = function() {
		      	  ModalDialogServiceFactory.confirmBox(
		            		  'Confirm Exit', 
		            		  'Are you sure you want to leave the page?', 
		            		  '', 
		            		  'Ok', 
		            		  $translate('common.button.text.CANCEL'), 
		            		  exitFormInner, 
		            		  null, 
		            		  null, 
		            		  null
		              );
		        };
		        
		        function exitFormInner(){
		        	$location.path("/listsecurityusers");
		        }
	          
	  } 
	
	]);
});