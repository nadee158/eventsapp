'use strict';

define(['app'], function (app) {
	app.controller('EditSecurityUserController', ['$scope', '$rootScope','$location','$filter', 
	                                            'SecurityUserServiceFactory',
	                                            'CommonStorageFactory','NotificationServiceFactory','PubSub','Constants','Page','ModalDialogServiceFactory','CommonServiceFactory',
	    function ($scope, $rootScope,$location,$filter, 
									    		SecurityUserServiceFactory,
	    										CommonStorageFactory,NotificationServiceFactory,PubSub,Constants,Page,ModalDialogServiceFactory,CommonServiceFactory) {
			
		    var $translate = $filter('translate');
		
			Page.setTitle($translate('usrmgt.security_user.pagetitle.EDIT_SECURITY_USER'));
			
			var securityUserId = 0;
	  
	        var baseUrl=$rootScope.baseUrl;
	        
	        $scope.initializeEditSecurityUserPage = function() {
	        	$scope.securityUserUpdateRequest={};
	        	var queryString = $location.search();
	        	securityUserId=queryString["id"]
	        	loadEditedSecurityUser(securityUserId);
	        };
	        
	        
	        $scope.listenerSaveClicked = function() {
			    	editSecurityUser();
			}
	        
	        
	        
	        function editSecurityUser() {
	        	
	        	angular.forEach($scope.editSecurityUserForm.$error.required, function(field) {
	        	    field.$setDirty();
	        	});
	        	
	        	if($scope.editSecurityUserForm.$valid) {
	        		
	        		ModalDialogServiceFactory.confirmBox(
	              		  $translate('common.notification.message.CONFIRM_SUBMIT_TITLE'), 
	              		  $translate('common.notification.message.CONFIRM_SUMIT_CONTENT'), 
	              		  '', 
	              		  $translate('common.button.text.SUBMIT'), 
	              		  $translate('common.button.text.CANCEL'), 
	              		  submitSecurityUser, 
	              		  $scope.securityUserUpdateRequest, 
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
	        
	        function submitSecurityUser(securityUserUpdateRequest){
	        	//Do something
	        	var response=SecurityUserServiceFactory.updateSecurityUser(securityUserUpdateRequest,baseUrl);		         
                
		        response.success(function(data, status, headers, config) {
		        	
		        	if(data.status==409){
		      			NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_SAVING_RECORD') + ' ' + $translate(data.message));
		      		}else{
		      			$scope.SecurityUserCreation=data;
		      			
		      			NotificationServiceFactory.info($translate('common.notification.message.SUCCESSFULLY_UPDATED'));
		      			
		      			$location.path("/listsecurityusers");
		      		}	
		      			
	            }).error(function(data, status, headers, config){
	            	NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_UPDATING_RECORD'));
	            	console.error($translate('common.notification.message.ERROR_WHILE_UPDATING_RECORD') + " " + data.message);
	            }) 
	        }
	        
	        function loadEditedSecurityUser(securityUserId){
	        	var ObjectRetrievalRequest={id:securityUserId};
	        	
	        	var response=SecurityUserServiceFactory.getSecurityUserById(ObjectRetrievalRequest,baseUrl);		         
                
		        response.success(function(data, status, headers, config) {
		      	
		        	  var objectRetrievalResponse=data;
	      			   var securityUserDto=data.dto;
		        	
		        	  $scope.securityUserUpdateRequest.id=securityUserDto.id;

		        	  $scope.securityUserUpdateRequest.userName = securityUserDto.userName;

		        	  if(securityUserDto.organization){
		        		  $scope.securityUserUpdateRequest.organizationId=securityUserDto.organization.id;
		        	  }
		        	 
		        	  if(securityUserDto.person){
		        		  if(securityUserDto.person.prefix){
		        			  $scope.securityUserUpdateRequest.prefixCode=securityUserDto.person.prefix.code;
		        		  }
		        		  $scope.securityUserUpdateRequest.fullName=securityUserDto.person.fullName;
		        		  $scope.securityUserUpdateRequest.email=securityUserDto.person.email;
		        		  $scope.securityUserUpdateRequest.nic=securityUserDto.person.nic;
		        		  $scope.securityUserUpdateRequest.mobileNumber=securityUserDto.person.mobileNumber;
		        		  $scope.securityUserUpdateRequest.address=securityUserDto.person.address;
		        	  }
		        	  
		        	  $scope.securityUserUpdateRequest.versionNumber=securityUserDto.versionNumber;
		        	  $scope.securityUserUpdateRequest.isDeleted=securityUserDto.isDeleted;
	      			
	      			
	            }).error(function(data, status, headers, config){
	            	NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_LOADING_RECORD'));
	            	console.error($translate('common.notification.message.ERROR_WHILE_LOADING_RECORD') + " " + data.message);
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
		      	  $scope.securityUserUpdateRequest=new Object();
		      	  loadEditedSecurityUser(securityUserId);
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