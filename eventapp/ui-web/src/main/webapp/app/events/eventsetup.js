'use strict';

define(['app','nvd3', 'ui_bootstrap', 'moment'], function (app, nvd3, ui_bootstrap, moment) {

  app.controller('EventSetupController', ['$scope', '$rootScope', '$log', '$sce', '$filter',
                                          'NotificationServiceFactory',
                                          'SecurityServiceFactory','Page', 
                                          'EventServiceFactory',
                                          'CommonServiceFactory',
                                          'ModalDialogServiceFactory',
                                  function($scope, $rootScope, $log, $sce, $filter,
                                		  NotificationServiceFactory,
                                		  SecurityServiceFactory,Page, 
                                		  EventServiceFactory,
                                		  CommonServiceFactory,
                                		  ModalDialogServiceFactory) {
	  Page.setTitle("Event Setup");
	  
	  var $translate = $filter('translate');
	  
	  var baseUrl=$rootScope.baseUrl;
	  
	  
	  $scope.initializeEventSetupController = function() {
		  $scope.activeStatus ='Active';
      };
      
      $scope.myDate = new Date();

      $scope.minDate = new Date(
          $scope.myDate.getFullYear(),
          $scope.myDate.getMonth(),
          $scope.myDate.getDate());

      $scope.maxDate = new Date(
          $scope.myDate.getFullYear() + 10,
          $scope.myDate.getMonth(),
          $scope.myDate.getDate());
      
    $scope.setValueRecordStatus = function(recordStatus) {
    	if(recordStatus=='A'){
    		$scope.activeStatus = 'Active';
    	}else{
    		$scope.activeStatus = 'Inactive';
    	}
    };
    
       
    $scope.createEvent= function() {
    
    	angular.forEach($scope.eventSetupForm.$error.required, function(field) {
      	    field.$setDirty();
      	    
      	});
    	
      	if($scope.eventSetupForm.$valid) {
      		
      		ModalDialogServiceFactory.confirmBox(
            		  $translate('common.notification.message.CONFIRM_SUBMIT_TITLE'), 
            		  $translate('common.notification.message.CONFIRM_SUMIT_CONTENT'), 
            		  '', 
            		  $translate('common.button.text.SUBMIT'), 
            		  $translate('common.button.text.CANCEL'), 
            		  submitEvent, 
            		  $scope.eventCreationRequest, 
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
      

      
      
      function submitEvent(eventCreationRequest){
    	var timeDate=moment($scope.eventTime).format('hh:mm:ss a');
    	var dateDate=moment(eventCreationRequest.eventDate).format('MM/DD/YYYY');
    	
    	eventCreationRequest.eventDate = dateDate + " " + timeDate;
    	console.log(eventCreationRequest.eventDate);
      	//Do something
      	var response=EventServiceFactory.createEvent(eventCreationRequest,baseUrl);		         
          
	        response.success(function(data, status, headers, config) {
	        	if(data.apiResponseStatus){
	        		if(data.apiResponseStatus==200){
	        			$scope.eventCreationResponse=data.apiResponseResults;
		      			
		      			NotificationServiceFactory.info($translate('common.notification.message.SUCCESSFULLY_SAVED'));
		      			
		      			$location.path("/home");
		      		}else{
		      			NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_SAVING_RECORD') + ' ' + $translate(data.apiResponseResults.message));
		      			
		      		}	
	        	}
	      		
              
          }).error(function(data, status, headers, config){
          	 NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_SAVING_RECORD'));
          	 console.error($translate('common.notification.message.ERROR_WHILE_SAVING_RECORD') + " " + data);
          }) 
      }
	  
	  
   
   
 }]);
  
  

});



