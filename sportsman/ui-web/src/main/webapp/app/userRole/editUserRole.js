'use strict';

define(['app'], function (app) {
	
	app.controller('EditUserRoleController', ['$scope', '$rootScope','$location','$filter', 'PubSub','Constants',
	                                            'CommonStorageFactory','NotificationServiceFactory','Page','ModalDialogServiceFactory',
	                                            'UserRoleServiceFactory','CommonServiceFactory',
	                                            
	    function ($scope, $rootScope,$location,$filter, PubSub,Constants,
                	CommonStorageFactory,NotificationServiceFactory, Page,ModalDialogServiceFactory,
                	UserRoleServiceFactory,CommonServiceFactory) {
		
		    
		    var userRoleId = 0;
			
		    var $translate = $filter('translate');
		
			Page.setTitle($translate('usrmgt.user_role.pagetitle.UPDATE_USER_ROLE'));
			
	  
	        var baseUrl=$rootScope.baseUrl;
	        
	        $scope.initializeEditUserRolePage = function() {
	        	var queryString = $location.search();
	        	userRoleId=queryString["id"]
	        	loadEditedUserRole(userRoleId);
	        };
	        
	       	
	       	
	       	
	       	function loadEditedUserRole(userRoleId){
	        	var ObjectRetrievalRequest={id:userRoleId};
	        	
	        	var response=UserRoleServiceFactory.getUserRoleById(ObjectRetrievalRequest,baseUrl);		         
                
		        response.success(function(data, status, headers, config) {
		      			
	      			var objectRetrievalResponse=data;
	      			$scope.userRoleUpdateRequest=data.dto;
	      			if(data.dto.permissions){
	      				$scope.existingScreenPermissionList=data.dto.permissions;
	      			}
	            }).error(function(data, status, headers, config){
	            	NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_LOADING_RECORD'));
	            	console.error($translate('common.notification.message.ERROR_WHILE_LOADING_RECORD') + " " + data.message);
	            })    
	        }
	       	
	       	
	        
	      $scope.updateUserRole= function() {
	        	    	
	        	angular.forEach($scope.editUserRoleForm.$error.required, function(field) {
	        	    field.$setDirty();
	        	});
	        	
	        	if($scope.editUserRoleForm.$valid) {
		        		
		        		ModalDialogServiceFactory.confirmBox(
		              		  $translate('common.notification.message.CONFIRM_SUBMIT_TITLE'), 
		              		  $translate('common.notification.message.CONFIRM_SUMIT_CONTENT'), 
		              		  '', 
		              		  $translate('common.button.text.SUBMIT'), 
		              		  $translate('common.button.text.CANCEL'), 
		              		  submitUserRole, 
		              		  $scope.userRoleUpdateRequest, 
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
	        
	        function submitUserRole(userRoleUpdateRequest){
	        	//Do something
	        	var response=UserRoleServiceFactory.updateUserRole(userRoleUpdateRequest,baseUrl);		         
                
		        response.success(function(data, status, headers, config) {
		      		if(data.status==409){
		      			NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_UPDATING_RECORD') + ' ' + $translate(data.message));
		      		}else{
		      			$scope.UserRoleCreation=data;
		      			
		      			NotificationServiceFactory.info($translate('common.notification.message.SUCCESSFULLY_UPDATED'));
		      			
		      			$location.path("/listuserroles");
		      		}	
	                
	            }).error(function(data, status, headers, config){
	            	NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_UPDATING_RECORD'));
	            	console.error($translate('common.notification.message.ERROR_WHILE_UPDATING_RECORD') + " " + data.message);
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
		      	  $scope.userRoleUpdateRequest=new Object();
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