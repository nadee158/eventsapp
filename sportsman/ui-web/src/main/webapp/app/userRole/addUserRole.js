'use strict';

define(['app'], function (app) {
	
	app.controller('AddUserRoleController', ['$scope', '$rootScope','$location','$filter', 'PubSub','Constants',
	                                            'CommonStorageFactory','NotificationServiceFactory','Page','ModalDialogServiceFactory',
	                                            'UserRoleServiceFactory','CommonServiceFactory',
	                                            
	    function ($scope, $rootScope,$location,$filter, PubSub,Constants,
                	CommonStorageFactory,NotificationServiceFactory, Page,ModalDialogServiceFactory,
                	UserRoleServiceFactory,CommonServiceFactory) {
		
			
		    var $translate = $filter('translate');
		
			Page.setTitle($translate('usrmgt.user_role.pagetitle.ADD_USER_ROLE'));
			
	  
	        var baseUrl=$rootScope.baseUrl;
	        
	        
	        $scope.createUserRole= function() {
	        	    	
	        	angular.forEach($scope.userRoleForm.$error.required, function(field) {
	        	    field.$setDirty();
	        	});
	        	
	        	if($scope.userRoleForm.$valid) {
	        		
	        		ModalDialogServiceFactory.confirmBox(
	              		  $translate('common.notification.message.CONFIRM_SUBMIT_TITLE'), 
	              		  $translate('common.notification.message.CONFIRM_SUMIT_CONTENT'), 
	              		  '', 
	              		  $translate('common.button.text.SUBMIT'), 
	              		  $translate('common.button.text.CANCEL'), 
	              		  submitUserRole, 
	              		  $scope.userRoleCreationRequest, 
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
	        
	        function submitUserRole(userRoleCreationRequest){
	        	//Do something
	        	var response=UserRoleServiceFactory.createUserRole(userRoleCreationRequest,baseUrl);		         
                
		        response.success(function(data, status, headers, config) {
		      		if(data.status==409){
		      			NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_SAVING_RECORD') + ' ' + $translate(data.message));
		      		}else{
		      			$scope.UserRoleCreation=data;
		      			
		      			NotificationServiceFactory.info($translate('common.notification.message.SUCCESSFULLY_SAVED'));
		      			
		      			$location.path("/listuserroles");
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
	      	  $scope.userRoleCreationRequest=new Object();
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
	        	$location.path("/listuserroles");
	        }
	          
	  } 
	
	]);
});