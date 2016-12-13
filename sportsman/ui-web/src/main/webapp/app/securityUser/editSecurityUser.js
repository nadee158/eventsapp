'use strict';

define(['app'], function (app) {
	app.controller('EditSecurityUserController', ['$scope', '$rootScope','$location','$filter', 
	                                            'SecurityUserServiceFactory',
		                                        'OrganizationServiceFactory',
	                                            'CommonStorageFactory','NotificationServiceFactory','PubSub','Constants','Page','ModalDialogServiceFactory','CommonServiceFactory',
	    function ($scope, $rootScope,$location,$filter, 
									    		SecurityUserServiceFactory,
												OrganizationServiceFactory,
	    										CommonStorageFactory,NotificationServiceFactory,PubSub,Constants,Page,ModalDialogServiceFactory,CommonServiceFactory) {
			
		    var $translate = $filter('translate');
		
			Page.setTitle($translate('usrmgt.security_user.pagetitle.EDIT_SECURITY_USER'));
			
			var securityUserId = 0;
	  
	        var baseUrl=$rootScope.baseUrl;
	        
	        $scope.initializeEditSecurityUserPage = function() {
	        	$scope.securityUserUpdateRequest={};
	        	displayTopMenuButtons();
	        	subscribeToTopMenuButtonEvents();
	        	var queryString = $location.search();
	        	securityUserId=queryString["id"]
	        	loadEditedSecurityUser(securityUserId);
	        	getOrganizations();
	        	getPrefixes();
	        };
	        
	       	function displayTopMenuButtons(){
	       		var formKey='edit_security_user_screen';
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
					    showSave:CommonServiceFactory.checkIfPermitted("ROLE_A_UM_SU_USU_UDT"),
					    showCancel:CommonServiceFactory.checkIfPermitted("ROLE_A_UM_SU_LSU_VWE ROLE_A_UM_SU_LSU_UDT"),
					    showAddNew:false,
	        	};
	        	PubSub.publish(Constants.Events.displayheaderbuttons,topMenuButtonDisplay);
	        }
	       	
	       	function subscribeToTopMenuButtonEvents(){
	       		//first unsubscribe from previous subscribed events
	       		PubSub.unsubscribe(Constants.Events.saved);
	       		PubSub.unsubscribe(Constants.Events.cancel);
	       		PubSub.unsubscribe(Constants.Events.add_new);
	       		//then subscribe to new events
	        	var subTopMenuSaveButton = PubSub.subscribe(Constants.Events.saved, listenerSaveClicked);
	        	var subTopMenuEditButton = PubSub.subscribe(Constants.Events.add_new, listenerAddNewClicked);
	        	var subTopMenuCancelButton = PubSub.subscribe(Constants.Events.cancel, listenerCancelClicked);
	        }
	        
	        function listenerSaveClicked(topic, data) {
			    var formKey=data.formKey;
			    if(formKey==$scope.formKey){
			    	editSecurityUser();
			    }
			}
	        
	        function listenerAddNewClicked(topic, data) {
	    	    var formKey=data.formKey;
	    	    if(formKey==$scope.formKey){
	    	    	$location.path("/addsecurityuser");
	    	    }
	    	  }
	        
	        function listenerCancelClicked(topic, data) {
			    var formKey=data.formKey;
			    if(formKey==$scope.formKey){
			    	$location.path('/listsecurityusers');
			    }
			}
	        
	        function getOrganizations(){
	        	var response=OrganizationServiceFactory.getActiveOrganization(baseUrl);	        	 
	        	response.success(function(data, status, headers, config) {		      			
		      		$scope.organizations=data.dtoList;		                
		        }).error(function(data, status, headers, config){
		            	NotificationServiceFactory.error(data.message);
		            	console.error('Error while creating SecurityUser ' + data.message);
		        })
	        }
	        
	        function getPrefixes(){
	        	var response=SecurityUserServiceFactory.getPrefixes(baseUrl);	        	 
	        	response.success(function(data, status, headers, config) {		      			
		      		$scope.prefixes=data.dtoList;		                
		        }).error(function(data, status, headers, config){
		            	NotificationServiceFactory.error(data.message);
		            	console.error('Error while creating SecurityUser ' + data.message);
		        })
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
	          
	  } 
	
	]);
});