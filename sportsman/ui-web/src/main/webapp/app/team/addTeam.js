'use strict';

define(['app','nvd3', 'ui_bootstrap', 'moment'], function (app, nvd3, ui_bootstrap, moment) {

  app.controller('AddTeamController', ['$scope', '$rootScope', '$log', '$sce', '$filter', '$location',
                                          'NotificationServiceFactory',
                                          'SecurityServiceFactory','Page', 
                                          'TeamServiceFactory',
                                          'CommonServiceFactory',
                                          'ModalDialogServiceFactory',
                                  function($scope, $rootScope, $log, $sce, $filter, $location,
                                		  NotificationServiceFactory,
                                		  SecurityServiceFactory,Page, 
                                		  TeamServiceFactory,
                                		  CommonServiceFactory,
                                		  ModalDialogServiceFactory) {
	  Page.setTitle("Teams");
	  
	  var $translate = $filter('translate');
	  
	  var baseUrl=$rootScope.baseUrl;
	  
	  
	  $scope.initializeTeamPage = function() {
		  $scope.activeStatus ='Active';
      };
      
       
    $scope.createTeam= function() {
    
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
              		  $scope.teamCreationRequest, 
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
      

      
      
      function submitEvent(teamCreationRequest){
    	
      	//Do something
      	var response=TeamServiceFactory.createTeam(teamCreationRequest,baseUrl);		         
          
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
    	  $scope.teamCreationRequest=new Object();
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



