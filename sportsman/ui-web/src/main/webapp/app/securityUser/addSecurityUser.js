'use strict';

define(['app'], function (app) {
	app.controller('AddSecurityUserController', ['$scope', '$rootScope','$location','$filter',
	                                            'SecurityUserServiceFactory',
	                                            'OrganizationServiceFactory',
	                                            'CommonStorageFactory','NotificationServiceFactory','PubSub','Constants','Page','ModalDialogServiceFactory','CommonServiceFactory',
	    function ($scope, $rootScope,$location,$filter, 
	    										SecurityUserServiceFactory,
	    										OrganizationServiceFactory,
	    										CommonStorageFactory,NotificationServiceFactory,PubSub,Constants,Page,ModalDialogServiceFactory,CommonServiceFactory) {
			
		    var $translate = $filter('translate');
		
			Page.setTitle($translate('usrmgt.security_user.pagetitle.ADD_SECURITY_USER'));
			
	  
	        var baseUrl=$rootScope.baseUrl;
	        
	        $scope.initializeSecurityUserPage = function() {
	        	displayTopMenuButtons();
	        	subscribeToTopMenuButtonEvents();
	        	getOrganizations();
	        	getPrefixes();
	        };
	        
	       	function displayTopMenuButtons(){
	       		var formKey='add_securityUser_screen';
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
					    showSave:CommonServiceFactory.checkIfPermitted("ROLE_A_UM_SU_ASU_UDT"),
					    showCancel:CommonServiceFactory.checkIfPermitted("ROLE_A_UM_SU_LSU_VWE ROLE_A_UM_SU_LSU_UDT"),
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
	        
	        function listenerSaveClicked(topic, data) {
			    var formKey=data.formKey;
			    if(formKey==$scope.formKey){
			    	createSecurityUser();
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
	        
	        function createSecurityUser() {
	        	
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
	          
	  } 
	
	]);
});