'use strict';

define(['app','nvd3', 'ui_bootstrap', 'moment'], function (app, nvd3, ui_bootstrap, moment) {

  app.controller('EditTeamController', ['$scope', '$rootScope', '$log', '$sce', '$filter', '$location',
                                          'NotificationServiceFactory',
                                          'Page', 
                                          'TeamServiceFactory',
                                          'CommonServiceFactory',
                                          'ModalDialogServiceFactory',
                                  function($scope, $rootScope, $log, $sce, $filter, $location,
                                		  NotificationServiceFactory,
                                		  Page, 
                                		  TeamServiceFactory,
                                		  CommonServiceFactory,
                                		  ModalDialogServiceFactory) {
	  Page.setTitle("Edit Team");
	  
	  var $translate = $filter('translate');
	  
	  var baseUrl=$rootScope.baseUrl;
	  
	  var teamId = 0;
	  
	  
	  $scope.initializeTeamPage = function() {
		  $scope.activeStatus ='Active';
		  var queryString = $location.search();
		  teamId=queryString["id"]
		  loadTeam(teamId);
      };
      
      $scope.setValueRecordStatus = function(recordStatus) {
      	setRecordStatus(recordStatus);
      };
      
      function setRecordStatus(recordStatus){
      	if(recordStatus=='A'){
  			$scope.activeStatus = 'Active';
  		}else{
  			$scope.activeStatus = 'Inactive';
  		}  
  	 }
      
      function loadTeam(teamId){
      	var ObjectRetrievalRequest={id:teamId};
      	
      	var response=TeamServiceFactory.getTeamById(ObjectRetrievalRequest,baseUrl);		         
          
	        response.success(function(data, status, headers, config) {
	      	
	        	  var objectRetrievalResponse=data;
    			  var dto=data.apiResponseResults.dto;
    			  setRecordStatus(dto.recordStatus);
	        	  $scope.teamUpdateRequest=dto;
    			
          }).error(function(data, status, headers, config){
          	NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_LOADING_RECORD'));
          	console.error($translate('common.notification.message.ERROR_WHILE_LOADING_RECORD') + " " + data.message);
          })    
      }
      
       
    $scope.updateTeam= function() {
    
    	angular.forEach($scope.teamSetupForm.$error.required, function(field) {
      	    field.$setDirty();
      	});
    	
      	if($scope.teamSetupForm.$valid) {
      		
      			ModalDialogServiceFactory.confirmBox(
              		  $translate('common.notification.message.CONFIRM_SUBMIT_TITLE'), 
              		  $translate('common.notification.message.CONFIRM_SUMIT_CONTENT'), 
              		  '', 
              		  $translate('common.button.text.SUBMIT'), 
              		  $translate('common.button.text.CANCEL'), 
              		  submitEvent, 
              		  $scope.teamUpdateRequest, 
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
    }
      

      
      
      function submitEvent(teamUpdateRequest){
    	
      	//Do something
      	var response=TeamServiceFactory.updateTeam(teamUpdateRequest,baseUrl);		         
          
	        response.success(function(data, status, headers, config) {
	        	if(data.apiResponseStatus){
	        		if(data.apiResponseStatus==200){
	        			$scope.eventCreationResponse=data.apiResponseResults;
		      			
		      			NotificationServiceFactory.info($translate('common.notification.message.SUCCESSFULLY_SAVED'));
		      			
		      			$location.path("/listteams");
		      		}else{
		      			NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_SAVING_RECORD') + ' ' + $translate(data.apiResponseResults.message));
		      			
		      		}	
	        	}
	      		
              
          }).error(function(data, status, headers, config){
          	 NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_SAVING_RECORD'));
          	 console.error($translate('common.notification.message.ERROR_WHILE_SAVING_RECORD') + " " + data);
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
    	  $scope.teamUpdateRequest=new Object();
    	  loadTeam(teamId);
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
    	  $location.path("/listteams");
      }
	  
   
   
 }]);
  
  

});



