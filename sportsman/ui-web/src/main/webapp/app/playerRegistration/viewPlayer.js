'use strict';

define(['app', 'ui_bootstrap'], function (app, ui_bootstrap) {

  app.controller('ViewPlayerController', ['$scope', '$rootScope', '$log', '$sce', '$filter', '$compile', '$location',
                                          'NotificationServiceFactory',
                                          'SecurityServiceFactory','Page', 
                                          'PlayerServiceFactory',
                                          'CategoryServiceFactory',
                                          'EventServiceFactory',
                                          'CommonServiceFactory',
                                          'ModalDialogServiceFactory',
                                  function($scope, $rootScope, $log, $sce, $filter,$compile,$location,
                                		  NotificationServiceFactory,
                                		  SecurityServiceFactory,Page, 
                                		  PlayerServiceFactory,
                                		  CategoryServiceFactory,
                                		  EventServiceFactory,
                                		  CommonServiceFactory,
                                		  ModalDialogServiceFactory) {
	  Page.setTitle("View Player");
	  
	  var $translate = $filter('translate');
	  
	  var baseUrl=$rootScope.baseUrl;
	  
	  $scope.buttonVisible = true;
	  
	  $scope.playerUserUpdateRequest={};
	  
	  var playerId = 0;
	  
	  $scope.initializePRController = function() {
		  $scope.imageUrl=$rootScope.uiBaseUrl + '/assets/app/images/avatar_2x.png';
		  var queryString = $location.search();
      	  playerId=queryString["id"]
      	  loadEditedPlayer(playerId);
      };
      
      function loadEditedPlayer(playerId){
      	var ObjectRetrievalRequest={id:playerId};
      	
      	var response=PlayerServiceFactory.getPlayerById(ObjectRetrievalRequest,baseUrl);		         
          
	      response.success(function(data, status, headers, config) {
	      	
	        	  $scope.playerUserUpdateRequest=data.apiResponseResults.dto;
	        	  $scope.imageUrl=baseUrl + '/commonservice/findfile?fn=' + data.apiResponseResults.dto.profileImagePath;
    			
          }).error(function(data, status, headers, config){
          	NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_LOADING_RECORD'));
          	console.error($translate('common.notification.message.ERROR_WHILE_LOADING_RECORD') + " " + data.message);
          })    
      }
      
      
      $scope.exitPlayerForm=function(){
    	  ModalDialogServiceFactory.confirmBox(
          		  'Confirm Exit', 
          		  'Are you sure you want to leave the page?', 
          		  '', 
          		  'Ok', 
          		  $translate('common.button.text.CANCEL'), 
          		  exitForm, 
          		  null, 
          		  null, 
          		  null
            );
      }
      
      function exitForm(){
    	  $location.path("/listplayers");
      }
      
      
      
      

    
       
        
      
      
	  
	  
   
   
 }]);
  
  

});



