'use strict';

define(['app'], function (app) {
	app.controller('EditPlayerController', ['$scope', '$rootScope','$location','$filter', 
	                                            'PlayerServiceFactory',
	                                            'CommonStorageFactory','NotificationServiceFactory','PubSub','Constants','Page','ModalDialogServiceFactory','CommonServiceFactory',
	    function ($scope, $rootScope,$location,$filter, 
	    										PlayerServiceFactory,
	    										CommonStorageFactory,NotificationServiceFactory,PubSub,Constants,Page,ModalDialogServiceFactory,CommonServiceFactory) {
			
		    var $translate = $filter('translate');
		
			Page.setTitle('Edit Player');
			
			var playerId = 0;
	  
	        var baseUrl=$rootScope.baseUrl;
	        
	        $scope.initializeEditPlayerPage = function() {
	        	$scope.playerUserUpdateRequest={};
	        	var queryString = $location.search();
	        	playerId=queryString["id"]
	        	loadEditedPlayer(playerId);
	        };
	        
	        
	        $scope.listenerSaveClicked = function() {
			    	editPlayer();
			}
	        
	        
	        
	        function editPlayer() {
	        	
	        	angular.forEach($scope.editPlayerForm.$error.required, function(field) {
	        	    field.$setDirty();
	        	});
	        	
	        	if($scope.editPlayerForm.$valid) {
	        		
	        		ModalDialogServiceFactory.confirmBox(
	              		  $translate('common.notification.message.CONFIRM_SUBMIT_TITLE'), 
	              		  $translate('common.notification.message.CONFIRM_SUMIT_CONTENT'), 
	              		  '', 
	              		  $translate('common.button.text.SUBMIT'), 
	              		  $translate('common.button.text.CANCEL'), 
	              		  submitPlayer, 
	              		  $scope.playerUserUpdateRequest, 
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
	        
	        function submitPlayer(playerUserUpdateRequest){
	        	//Do something
	        	var response=PlayerServiceFactory.updatePlayer(playerUserUpdateRequest,baseUrl);		         
                
		        response.success(function(data, status, headers, config) {
		        	
		        	if(data.status==409){
		      			NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_SAVING_RECORD') + ' ' + $translate(data.message));
		      		}else{
		      			$scope.PlayerCreation=data;
		      			
		      			NotificationServiceFactory.info($translate('common.notification.message.SUCCESSFULLY_UPDATED'));
		      			
		      			$location.path("/listsecurityusers");
		      		}	
		      			
	            }).error(function(data, status, headers, config){
	            	NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_UPDATING_RECORD'));
	            	console.error($translate('common.notification.message.ERROR_WHILE_UPDATING_RECORD') + " " + data.message);
	            }) 
	        }
	        
	        function loadEditedPlayer(playerId){
	        	var ObjectRetrievalRequest={id:playerId};
	        	
	        	var response=PlayerServiceFactory.getPlayerById(ObjectRetrievalRequest,baseUrl);		         
                
		        response.success(function(data, status, headers, config) {
		      	
		        	  var objectRetrievalResponse=data.apiResponseResults;
	      			   var playerDto=data.dto;
		        	
		        	  $scope.playerUserUpdateRequest.id=playerDto.id;

		        	  if(playerDto.person){
		        		 
		        		  $scope.playerUserUpdateRequest.playerNumber=playerDto.person.playerNumber;
		        		  $scope.playerUserUpdateRequest.fullName=playerDto.person.fullName;
		        		  $scope.playerUserUpdateRequest.email=playerDto.person.email;
		        		  $scope.playerUserUpdateRequest.nic=playerDto.person.nic;
		        		  $scope.playerUserUpdateRequest.contactNumber=playerDto.contactNumber;
		        		  $scope.playerUserUpdateRequest.address=playerDto.person.address;
		        	  }
		        	  
		        	  $scope.playerUserUpdateRequest.versionNumber=playerDto.version;
		        	  $scope.playerUserUpdateRequest.recordStatus=playerDto.recordStatus;
	      			
	      			
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
		      	  $scope.playerUserUpdateRequest=new Object();
		      	  loadEditedPlayer(playerId);
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