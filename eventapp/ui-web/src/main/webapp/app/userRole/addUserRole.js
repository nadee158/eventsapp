'use strict';

define(['app'], function (app) {
	
	app.controller('AddUserRoleController', ['$scope', '$rootScope','$location','$filter', 'PubSub','Constants',
	                                            'CommonStorageFactory','NotificationServiceFactory','Page','ModalDialogServiceFactory',
	                                            'UserRoleServiceFactory','CommonServiceFactory',
	                                            
	    function ($scope, $rootScope,$location,$filter, PubSub,Constants,
                	CommonStorageFactory,NotificationServiceFactory, Page,ModalDialogServiceFactory,
                	UserRoleServiceFactory,CommonServiceFactory) {
		
			$scope.screenPermissionList=[];
			$scope.selectedScreenPermissionList=[];
		    
		    $scope.showApplicationModule=false;	
       	    $scope.showApplicationModuleService=false;
		    $scope.showScreen=false;
		    $scope.showScreenPermissions=false;
			
		    var $translate = $filter('translate');
		
			Page.setTitle($translate('usrmgt.user_role.pagetitle.ADD_USER_ROLE'));
			
	  
	        var baseUrl=$rootScope.baseUrl;
	        
	        $scope.initializeUserRolePage = function() {
	        	displayTopMenuButtons();
	        	subscribeToTopMenuButtonEvents();
	        	getApplications();
	        };
	        
	       	function displayTopMenuButtons(){
	       		var formKey='add_user_role_screen';
	       	    $scope.formKey=formKey;
	        	var topMenuButtonDisplay={
	        			formKey:formKey,
					    showMenu:true,
					    showNext:false,
					    showPrevious:false,
					    showPrint:false,
					    showCopy:false,
					    showEdit:false,
					    showSearch:false,
					    showSave:CommonServiceFactory.checkIfPermitted("ROLE_A_UM_UR_AUR_UDT"),
					    showCancel:CommonServiceFactory.checkIfPermitted("ROLE_A_UM_UR_LUR_VWE ROLE_A_UM_UR_LUR_UDT"),
					    showAddNew:false,
	        	};
	        	PubSub.publish(Constants.Events.displayheaderbuttons,topMenuButtonDisplay);
	        }
	       	
	       	function subscribeToTopMenuButtonEvents(){
	       		//first unsubscribe from previous subscribed events
	       		PubSub.unsubscribe(Constants.Events.saved);
	       		PubSub.unsubscribe(Constants.Events.cancel);
	       		//then subscribe to new events
	        	var subTopMenuSaveButton = PubSub.subscribe(Constants.Events.saved, listenerSaveClicked);
	        	var subTopMenuCancelButton = PubSub.subscribe(Constants.Events.cancel, listenerCancelClicked);
	        }
	       	
	        $scope.addScreenPermission= function(screenPermission) {
	        	appendToPermissions(screenPermission);
	        	removeFromExistingPermissionList(screenPermission);
	        }
	        
	        $scope.removeScreenPermission= function(screenPermission) {
	        	appendToExistingPermissions(screenPermission);
	        	removeFromSelectedPermissionList(screenPermission);
	        }
	        
	        $scope.removeAllFromSelected=function(){
	        	if($scope.selectedScreenPermissionList && $scope.selectedScreenPermissionList.length>0){
	        		angular.forEach($scope.selectedScreenPermissionList, function(selectedScreenPermission, index) {
	        			appendToExistingPermissions(selectedScreenPermission);
	       			});
	        		$scope.selectedScreenPermissionList=[];
	        	}else{
	        		ModalDialogServiceFactory.alert(
		              		  $translate('usrmgt.security_user.alert.NO_ITEMS_TO_REMOVE_HEADING'), 
		              		  $translate('usrmgt.security_user.alert.NO_ITEMS_TO_REMOVE_DESCRIPTION'), 
		              		  '', 
		              		  $translate('common.button.text.OK'), 
		              		  null, 
		              		  null
		                );
	        	}
	        }
	        
	        $scope.addAllToSelected=function(){
	        	if($scope.screenPermissionList && $scope.screenPermissionList.length>0){
	        		angular.forEach($scope.screenPermissionList, function(existingScreenPermission, index) {
	        			appendToPermissions(existingScreenPermission);
	       			});
	        		$scope.screenPermissionList=[];
	        	}else{
	        		ModalDialogServiceFactory.alert(
		              		  $translate('usrmgt.security_user.alert.NO_ITEMS_TO_ADD_HEADING'), 
		              		  $translate('usrmgt.security_user.alert.NO_ITEMS_TO_ADD_DESCRIPTION'), 
		              		  '', 
		              		  $translate('common.button.text.OK'), 
		              		  null, 
		              		  null
		                );
	        	}
	        }
	        
	        function appendToExistingPermissions(screenPermission){
	        	if(screenPermission){
	   				if(!(checkIfAlreadyAvailableInExisting(screenPermission))) {
	   					$scope.screenPermissionList.push(screenPermission);
	       			}else{
	       				console.log('Already Exist');
	       				console.log(screenPermission);
	       			}
        		}
	        	$scope.showScreenPermissions=true;
	        }
	        
	        function appendToPermissions(screenPermission){
	        	if(screenPermission){
	   				if(!(checkIfAlreadyAvailable(screenPermission))) {
	   					$scope.selectedScreenPermissionList.push(screenPermission);
	       			}else{
	       				console.log('Already Exist');
	       				console.log(screenPermission);
	       			}
        		}
	        	$scope.showSelectedScreenPermissions=true;
	       	}
	        
	        function checkIfAlreadyAvailable(permission){
	        	var isAlreadyAvailable=false;
	        	angular.forEach($scope.selectedScreenPermissionList, function(existingScreenPermission, index) {
       				if(permission.permissionCode==existingScreenPermission.permissionCode) {
       					isAlreadyAvailable=true;
	       			}
       			});
	        	return isAlreadyAvailable;
	        }
	        
	        function checkIfAlreadyAvailableInExisting(permission){
	        	var isAlreadyAvailable=false;
	        	angular.forEach($scope.screenPermissionList, function(screenPermission, index) {
       				if(permission.permissionCode==screenPermission.permissionCode) {
       					isAlreadyAvailable=true;
	       			}
       			});
	        	return isAlreadyAvailable;
	        }
	        
	        function removeFromExistingPermissionList(permission){
	        	angular.forEach($scope.screenPermissionList, function(screenPermission, index) {
       				if(permission.permissionCode==screenPermission.permissionCode) {
       					var index = $scope.screenPermissionList.indexOf(screenPermission);
       					$scope.screenPermissionList.splice(index, 1);  
	       			}
       			});
	        }
	        
	        function removeFromSelectedPermissionList(permission){
	        	angular.forEach($scope.selectedScreenPermissionList, function(screenPermission, index) {
       				if(permission.permissionCode==screenPermission.permissionCode) {
       					var index = $scope.selectedScreenPermissionList.indexOf(screenPermission);
       					$scope.selectedScreenPermissionList.splice(index, 1);  
	       			}
       			});
	        }
	       	
	       	function getApplications(){
	       		$scope.showApplicationModule=false;	
	       	    $scope.showApplicationModuleService=false;
			    $scope.showScreen=false;
			    $scope.showScreenPermissions=false;
			         		
	        	var response=ApplicationServiceFactory.getActiveApplications(baseUrl);	        	 
	        	response.success(function(data, status, headers, config) {		      			
		      		$scope.applications=data.dtoList;	
		        }).error(function(data, status, headers, config){
		            	NotificationServiceFactory.error(data.message);
		            	console.error('Error while creating Application ' + data.message);
		        })
	        }
	       	
	        $scope.getApplicationModulesByApplicationId=function(){
	        	$scope.showApplicationModule=false;	
	       	    $scope.showApplicationModuleService=false;
			    $scope.showScreen=false;
			    $scope.showScreenPermissions=false;
			    
	        	var applicationId=$scope.applicationId;
	        	var applicationModuleListRequest={applicationId:applicationId};
	        	var response=ApplicationModuleServiceFactory.getActiveApplicationModulesByApplicationId(applicationModuleListRequest,baseUrl);	        	 
	        	response.success(function(data, status, headers, config) {	
		      		$scope.applicationModuleList=data.dtoList;	
		      		getActiveScreenPermissionsByApplicationId(applicationId);
		      		$scope.showApplicationModule=true;	
		        }).error(function(data, status, headers, config){
		            	NotificationServiceFactory.error(data.message);
		            	console.error('Error while creating Application ' + data.message);
		        })
	        }
	        
	        $scope.getApplicationModuleServicesByApplicationModuleId=function(){
	       	    $scope.showApplicationModuleService=false;
			    $scope.showScreen=false;
			    $scope.showScreenPermissions=false;
				    
	        	var applicationId=$scope.applicationId;
	        	var applicationModuleId=$scope.applicationModuleId;
	        	var applicationModuleServiceListRequest={applicationId:applicationId,applicationModuleId:applicationModuleId};
	        	var response=ApplicationModuleServiceServiceFactory
	        						.getActiveApplicationModuleServicesByApplicationModuleId(applicationModuleServiceListRequest,baseUrl);	        	 
	        	response.success(function(data, status, headers, config) {	
		      		$scope.applicationModuleServiceList=data.dtoList;		
		      		getActiveScreenPermissionsByApplicationModuleId(applicationModuleId);
		      		$scope.showApplicationModuleService=true;
		        }).error(function(data, status, headers, config){
		            	NotificationServiceFactory.error(data.message);
		            	console.error('Error while creating Application ' + data.message);
		        })
	        }
	        
	        $scope.getScreensByApplicationModuleServiceId=function(){
				$scope.showScreen=false;
				$scope.showScreenPermissions=false;
	        	
	        	var applicationId=$scope.applicationId;
	        	var applicationModuleId=$scope.applicationModuleId;
	        	var applicationModuleServiceId=$scope.applicationModuleServiceId;
	        	var applicationModuleServiceListRequest={applicationId:applicationId,
	        			applicationModuleId:applicationModuleId,
	        			applicationModuleServiceId:applicationModuleServiceId};
	        	var response=ScreenServiceFactory
	        						.getActiveScreensByModuleServiceId(applicationModuleServiceListRequest,baseUrl);	        	 
	        	response.success(function(data, status, headers, config) {	
		      		$scope.screenList=data.dtoList;
		      		getActiveScreenPermissionsByApplicationModuleServiceId(applicationModuleServiceId);
		      		$scope.showScreen=true;
		        }).error(function(data, status, headers, config){
		            	NotificationServiceFactory.error(data.message);
		            	console.error('Error while creating Application ' + data.message);
		        })
	        }
	        
	       
	        
	        function getActiveScreenPermissionsByApplicationId(applicationId){
	        	$scope.screenPermissionList=[];
	        	$scope.showScreenPermissions=false;
	        	
	        	var screenPermissionListRequest={applicationId:applicationId};
	        	var response=ScreenPermissionServiceFactory.getActiveScreenPermissionsByApplicationId(screenPermissionListRequest,baseUrl);	        	 
	        	response.success(function(data, status, headers, config) {	
	        		if(data.dtoList){
	        			$scope.screenPermissionList=data.dtoList;		
	        		}else{
	        			$scope.screenPermissionList=[];
	        		}    
	        		$scope.showScreenPermissions=true;
		        }).error(function(data, status, headers, config){
		            	NotificationServiceFactory.error(data.message);
		            	console.error('Error while creating Application ' + data.message);
		        })
	        }
	        
	        function getActiveScreenPermissionsByApplicationModuleId(applicationModuleId){
	        	$scope.screenPermissionList=[];
	        	$scope.showScreenPermissions=false;
	        	
	        	var screenPermissionListRequest={applicationModuleId:applicationModuleId};
	        	var response=ScreenPermissionServiceFactory.getActiveScreenPermissionsByApplicationModuleId(screenPermissionListRequest,baseUrl);	        	 
	        	response.success(function(data, status, headers, config) {
	        		if(data.dtoList){
	        			$scope.screenPermissionList=data.dtoList;		
	        		}else{
	        			$scope.screenPermissionList=[];
	        		}
	        		$scope.showScreenPermissions=true;
		        }).error(function(data, status, headers, config){
		            	NotificationServiceFactory.error(data.message);
		            	console.error('Error while creating Application ' + data.message);
		        })
	        }
	        
	        function getActiveScreenPermissionsByApplicationModuleServiceId(applicationModuleServiceId){
	        	$scope.screenPermissionList=[];
	        	$scope.showScreenPermissions=false;
	        	
	        	var screenPermissionListRequest={applicationModuleServiceId:applicationModuleServiceId};
	        	var response=ScreenPermissionServiceFactory.getActiveScreenPermissionsByApplicationModuleServiceId(screenPermissionListRequest,baseUrl);	        	 
	        	response.success(function(data, status, headers, config) {	
	        		if(data.dtoList){
	        			$scope.screenPermissionList=data.dtoList;		
	        		}else{
	        			$scope.screenPermissionList=[];
	        		}
	        		$scope.showScreenPermissions=true;
		        }).error(function(data, status, headers, config){
		            	NotificationServiceFactory.error(data.message);
		            	console.error('Error while creating Application ' + data.message);
		        })
	        }
	        
	        $scope.getActiveScreenPermissionsByScreenId=function(){
	        	$scope.screenPermissionList=[];
	        	$scope.showScreenPermissions=false;
	        	
	        	var screenId=$scope.screenId;
	        	var screenPermissionListRequest={screenId:screenId};
	        	var response=ScreenPermissionServiceFactory.getActiveScreenPermissionsByScreenId(screenPermissionListRequest,baseUrl);	        	 
	        	response.success(function(data, status, headers, config) {	
	        		if(data.dtoList){
	        			$scope.screenPermissionList=data.dtoList;		
	        		}else{
	        			$scope.screenPermissionList=[];
	        		} 
	        		$scope.showScreenPermissions=true;
		        }).error(function(data, status, headers, config){
		            	NotificationServiceFactory.error(data.message);
		            	console.error('Error while creating Application ' + data.message);
		        })
	        }
	        
	        function listenerSaveClicked(topic, data) {
			    var formKey=data.formKey;
			    if(formKey==$scope.formKey){
			    	createUserRole();
			    }
			}
	        
	        function listenerCancelClicked(topic, data) {
			    var formKey=data.formKey;
			    if(formKey==$scope.formKey){
			    	$location.path('/listuserroles');
			    }
			}
	        
	        
	        function createUserRole() {
	        	    	
	        	$scope.userRoleCreationRequest.permissions=$scope.selectedScreenPermissionList;
	        	
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
	          
	  } 
	
	]);
});