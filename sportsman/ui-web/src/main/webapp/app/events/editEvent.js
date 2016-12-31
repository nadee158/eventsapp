'use strict';

define(['app','nvd3', 'ui_bootstrap', 'moment'], function (app, nvd3, ui_bootstrap, moment) {

  app.controller('EventEditController', ['$scope', '$rootScope', '$log', '$sce', '$filter', '$location',
                                          'NotificationServiceFactory',
                                          'SecurityServiceFactory','Page', 
                                          'EventServiceFactory',
                                          'CommonServiceFactory',
                                          'ModalDialogServiceFactory',
                                  function($scope, $rootScope, $log, $sce, $filter, $location,
                                		  NotificationServiceFactory,
                                		  SecurityServiceFactory,Page, 
                                		  EventServiceFactory,
                                		  CommonServiceFactory,
                                		  ModalDialogServiceFactory) {
	  Page.setTitle("Event Setup");
	  
	  var $translate = $filter('translate');
	  
	  var baseUrl=$rootScope.baseUrl;
	  
	  var eventId = 0;
	  
	  
	  $scope.initializeEventEditController = function() {
		  $scope.activeStatus ='Active';
		  var queryString = $location.search();
		  eventId=queryString["id"]
		  loadEvent(eventId);
      };
      
      function loadEvent(eventId){
        	var ObjectRetrievalRequest={id:eventId};
        	
        	var response=EventServiceFactory.getEventById(ObjectRetrievalRequest,baseUrl);		         
            
  	        response.success(function(data, status, headers, config) {
  	      	
  	        	  var objectRetrievalResponse=data;
      			  var dto=data.apiResponseResults.dto;
      			  
      			   var date = moment(dto.eventDate).format('MM/DD/YYYY hh:mm:ss a');
      			   $scope.eventTime=date;
      			   setRecordStatus(dto.recordStatus);
  	        	
  	        	  $scope.eventUpdateRequest=dto;
      			
            }).error(function(data, status, headers, config){
            	NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_LOADING_RECORD'));
            	console.error($translate('common.notification.message.ERROR_WHILE_LOADING_RECORD') + " " + data.message);
            })    
        }
      
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
    	setRecordStatus(recordStatus);
    };
    
    function setRecordStatus(recordStatus){
    	if(recordStatus=='A'){
			$scope.activeStatus = 'Active';
		}else{
			$scope.activeStatus = 'Inactive';
		}  
	 }
    
    $scope.updateEvent= function() {
    
    	angular.forEach($scope.eventSetupForm.$error.required, function(field) {
      	    field.$setDirty();
      	    
      	});
    	
    	console.log($scope.eventSetupForm.$error);
    	console.log($scope.eventSetupForm.$valid);
    	
      	if($scope.eventSetupForm.$valid) {
      		
      		ModalDialogServiceFactory.confirmBox(
            		  $translate('common.notification.message.CONFIRM_SUBMIT_TITLE'), 
            		  $translate('common.notification.message.CONFIRM_SUMIT_CONTENT'), 
            		  '', 
            		  $translate('common.button.text.SUBMIT'), 
            		  $translate('common.button.text.CANCEL'), 
            		  submitEvent, 
            		  $scope.eventUpdateRequest, 
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
      

      
      
      function submitEvent(eventUpdateRequest){
    	var timeDate=moment($scope.eventTime).format('hh:mm:ss a');
    	var dateDate=moment(eventUpdateRequest.eventDate).format('MM/DD/YYYY');
    	
    	eventUpdateRequest.eventDate = dateDate + " " + timeDate;
    	console.log(eventUpdateRequest.eventDate);
      	//Do something
      	var response=EventServiceFactory.updateEvent(eventUpdateRequest,baseUrl);		         
          
	        response.success(function(data, status, headers, config) {
	        	if(data.apiResponseStatus){
	        		if(data.apiResponseStatus==200){
	        			$scope.eventCreationResponse=data.apiResponseResults;
		      			
		      			NotificationServiceFactory.info($translate('common.notification.message.SUCCESSFULLY_SAVED'));
		      			
		      			$location.path("/listevents");
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
    	  $scope.eventUpdateRequest=new Object();
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
    	  $location.path("/home");
      }
	  
   
   
 }]);
  
  

});



