'use strict';

define(['app'], function (app) {
	app.controller('ViewUserRoleController', ['$scope', '$rootScope','$location','$filter', 'UserRoleServiceFactory',
	                                            'CommonStorageFactory','NotificationServiceFactory','PubSub','Constants','Page',
	                                            'CommonServiceFactory', 'ModalDialogServiceFactory',
	    function ($scope, $rootScope,$location,$filter, UserRoleServiceFactory,
	    										CommonStorageFactory,NotificationServiceFactory,PubSub,Constants,Page,
	    										CommonServiceFactory,ModalDialogServiceFactory) {
			
		    var $translate = $filter('translate');
		
			Page.setTitle($translate('usrmgt.user_role.pagetitle.VIEW_USER_ROLE'));
			
			var userRoleId = 0;
	  
	        var baseUrl=$rootScope.baseUrl;
	        
	        $scope.initializeUserRolePage = function() {
	        	var queryString = $location.search();
	        	userRoleId=queryString["id"]
	        	loadViewedUserRole(userRoleId);
	        };
	        
	        
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