'use strict';

define(['app'], function (app) {
	app.controller('ViewUserRoleController', ['$scope', '$rootScope','$location','$filter', 'UserRoleServiceFactory',
	                                            'CommonStorageFactory','NotificationServiceFactory','PubSub','Constants','Page','CommonServiceFactory',
	    function ($scope, $rootScope,$location,$filter, UserRoleServiceFactory,
	    										CommonStorageFactory,NotificationServiceFactory,PubSub,Constants,Page,CommonServiceFactory) {
			
		    var $translate = $filter('translate');
		
			Page.setTitle($translate('usrmgt.user_role.pagetitle.VIEW_USER_ROLE'));
			
			var userRoleId = 0;
	  
	        var baseUrl=$rootScope.baseUrl;
	        
	        $scope.initializeUserRolePage = function() {
	        	displayTopMenuButtons();
	        	subscribeToTopMenuButtonEvents();
	        	var queryString = $location.search();
	        	userRoleId=queryString["id"]
	        	loadViewedUserRole(userRoleId);
	        };
	        
	       	function displayTopMenuButtons(){
	       		var formKey='view_userrole_screen';
	       	    $scope.formKey=formKey;
	        	var topMenuButtonDisplay={
	        			formKey:formKey,
					    showMenu:true,
					    showNext:false,
					    showPrevious:false,
					    showPrint:false,
					    showCopy:false,
					    showEdit:CommonServiceFactory.checkIfPermitted("ROLE_A_UM_UR_UUR_VWE"),
					    showSearch:false,
					    showSave:false,
					    showCancel:CommonServiceFactory.checkIfPermitted("ROLE_A_UM_UR_LUR_VWE ROLE_A_UM_UR_LUR_UDT"),
					    showAddNew:CommonServiceFactory.checkIfPermitted("ROLE_A_UM_UR_AUR_VWE"),
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
			    	$location.path('/edituserrole').search({id: applicationId});
			    }
			}
	        
	        function listenerAddNewClicked(topic, data) {
	    	    var formKey=data.formKey;
	    	    if(formKey==$scope.formKey){
	    	    	$location.path("/adduserrole");
	    	    }
	    	  }
	        
	        function listenerCancelClicked(topic, data) {
			    var formKey=data.formKey;
			    if(formKey==$scope.formKey){
			    	$location.path('/listuserroles');
			    }
			}
	        
	        
	        
	        function loadViewedUserRole(userRoleId){
	        	var ObjectRetrievalRequest={id:userRoleId};
	        	
	        	var response=UserRoleServiceFactory.getUserRoleById(ObjectRetrievalRequest,baseUrl);		         
                
		        response.success(function(data, status, headers, config) {
		      			
	      			var objectRetrievalResponse=data;
	      			$scope.userRoleDto=data.dto;
	      			
	            }).error(function(data, status, headers, config){
	            	NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_LOADING_RECORD'));
	            	console.error($translate('common.notification.message.ERROR_WHILE_LOADING_RECORD') + " " + data.message);
	            })    
	        }
	          
	  } 
	
	]);
});