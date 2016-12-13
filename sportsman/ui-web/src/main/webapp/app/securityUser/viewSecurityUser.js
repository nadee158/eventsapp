'use strict';

define(['app'], function (app) {
	app.controller('ViewSecurityUserController', ['$scope', '$rootScope','$location','$filter', 'SecurityUserServiceFactory',
	                                            'CommonStorageFactory','NotificationServiceFactory','PubSub','Constants','Page','CommonServiceFactory',
	    function ($scope, $rootScope,$location,$filter, SecurityUserServiceFactory,
	    										CommonStorageFactory,NotificationServiceFactory,PubSub,Constants,Page,CommonServiceFactory) {
			
		    var $translate = $filter('translate');
		
			Page.setTitle($translate('usrmgt.security_user.pagetitle.VIEW_SECURITY_USER'));
			
			var securityUserId = 0;
	  
	        var baseUrl=$rootScope.baseUrl;
	        
	        $scope.initViewSecurityUserPage = function() {
	        	var queryString = $location.search();
	        	displayTopMenuButtons();
	        	subscribeToTopMenuButtonEvents();
	        	var queryString = $location.search();
	        	securityUserId=queryString["id"]
	        	loadViewedSecurityUser(securityUserId);
	        	$scope.securityuserDto={};
	        	$scope.securityuserDto.explicitlyDeniedPermissions=[];
	        	$scope.securityuserDto.explicitlyGrantedPermissions=[];
	        	$scope.securityuserDto.userRoles=[];
	        };
	        
	       	function displayTopMenuButtons(){
	       		var formKey='view_application_screen';
	       	    $scope.formKey=formKey;
	        	var topMenuButtonDisplay={
	        			formKey:formKey,
					    showMenu:true,
					    showNext:false,
					    showPrevious:false,
					    showPrint:false,
					    showCopy:false,
					    showEdit:CommonServiceFactory.checkIfPermitted("ROLE_A_UM_SU_USU_VWE"),
					    showSearch:false,
					    showSave:false,
					    showCancel:CommonServiceFactory.checkIfPermitted("ROLE_A_UM_SU_LSU_VWE ROLE_A_UM_SU_LSU_UDT"),
					    showAddNew:CommonServiceFactory.checkIfPermitted("ROLE_A_UM_SU_ASU_VWE"),
	        	};
	        	PubSub.publish(Constants.Events.displayheaderbuttons,topMenuButtonDisplay);
	        }
	       	
	       	function subscribeToTopMenuButtonEvents(){
	       		//first unsubscribe from previous subscribed events
	       		PubSub.unsubscribe(Constants.Events.edit);
	       		PubSub.unsubscribe(Constants.Events.cancel);
	       		PubSub.unsubscribe(Constants.Events.add_new);
	       		//then subscribe to new events
	        	var subTopMenuEditButton = PubSub.subscribe(Constants.Events.edit, listenerEditClicked);
	        	var subTopMenuEditButton = PubSub.subscribe(Constants.Events.add_new, listenerAddNewClicked);
	        	var subTopMenuCancelButton = PubSub.subscribe(Constants.Events.cancel, listenerCancelClicked);
	        }
	        
	        function listenerEditClicked(topic, data) {
			    var formKey=data.formKey;
			    if(formKey==$scope.formKey){
			    	$location.path('/editsecurityuser').search({id: securityUserId});
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
	        
	        
	        
	        function loadViewedSecurityUser(securityUserId){
	        	var ObjectRetrievalRequest={id:securityUserId};
	        	
	        	var response=SecurityUserServiceFactory.getSecurityUserById(ObjectRetrievalRequest,baseUrl);		         
                
		        response.success(function(data, status, headers, config) {
		      			
	      			var objectRetrievalResponse=data;
	      			$scope.securityuserDto=data.dto;
	      			
	            }).error(function(data, status, headers, config){
	            	NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_LOADING_RECORD'));
	            	console.error($translate('common.notification.message.ERROR_WHILE_LOADING_RECORD') + " " + data.message);
	            })    
	        }
	          
	  } 
	
	]);
});