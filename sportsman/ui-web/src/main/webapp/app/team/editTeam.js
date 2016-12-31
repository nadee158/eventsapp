'use strict';

define(['app','nvd3', 'ui_bootstrap', 'moment'], function (app, nvd3, ui_bootstrap, moment) {

  app.controller('EditAgeGroupController', ['$scope', '$rootScope', '$log', '$sce', '$filter', '$location',
                                          'NotificationServiceFactory',
                                          'Page', 
                                          'AgeGroupServiceFactory',
                                          'CommonServiceFactory',
                                          'ModalDialogServiceFactory',
                                  function($scope, $rootScope, $log, $sce, $filter, $location,
                                		  NotificationServiceFactory,
                                		  Page, 
                                		  AgeGroupServiceFactory,
                                		  CommonServiceFactory,
                                		  ModalDialogServiceFactory) {
	  Page.setTitle("Edit Age Group");
	  
	  var $translate = $filter('translate');
	  
	  var baseUrl=$rootScope.baseUrl;
	  
	  var ageGroupId = 0;
	  
	  
	  $scope.initializeAgeGroupPage = function() {
		  $scope.activeStatus ='Active';
		  var queryString = $location.search();
		  ageGroupId=queryString["id"]
		  loadAgeGroup(ageGroupId);
      };
      
      function loadAgeGroup(ageGroupId){
      	var ObjectRetrievalRequest={id:ageGroupId};
      	
      	var response=AgeGroupServiceFactory.getAgeGroupById(ObjectRetrievalRequest,baseUrl);		         
          
	        response.success(function(data, status, headers, config) {
	      	
	        	  var objectRetrievalResponse=data;
    			  var dto=data.apiResponseResults.dto;
	        	
	        	  $scope.ageGroupUpdateRequest=dto;
    			
          }).error(function(data, status, headers, config){
          	NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_LOADING_RECORD'));
          	console.error($translate('common.notification.message.ERROR_WHILE_LOADING_RECORD') + " " + data.message);
          })    
      }
      
       
    $scope.updateAgeGroup= function() {
    
    	angular.forEach($scope.ageGroupSetupForm.$error.required, function(field) {
      	    field.$setDirty();
      	});
    	
      	if($scope.ageGroupSetupForm.$valid) {
      		
      		if($scope.ageGroupUpdateRequest.toAge<=$scope.ageGroupUpdateRequest.fromAge){
      			ModalDialogServiceFactory.alert(
              		  $translate('common.notification.message.NOTIFY_FORM_VALIDATION_ERRORS'), 
              		  'To age must be less than from age!', 
              		  '', 
              		  $translate('common.button.text.OK'), 
              		  null, 
              		  null
                );
      		}else{
      			ModalDialogServiceFactory.confirmBox(
              		  $translate('common.notification.message.CONFIRM_SUBMIT_TITLE'), 
              		  $translate('common.notification.message.CONFIRM_SUMIT_CONTENT'), 
              		  '', 
              		  $translate('common.button.text.SUBMIT'), 
              		  $translate('common.button.text.CANCEL'), 
              		  submitEvent, 
              		  $scope.ageGroupUpdateRequest, 
              		  null, 
              		  null
                );
      		}
      		
      		
      	    
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
      

      
      
      function submitEvent(ageGroupUpdateRequest){
    	
      	//Do something
      	var response=AgeGroupServiceFactory.updateAgeGroup(ageGroupUpdateRequest,baseUrl);		         
          
	        response.success(function(data, status, headers, config) {
	        	if(data.apiResponseStatus){
	        		if(data.apiResponseStatus==200){
	        			$scope.eventCreationResponse=data.apiResponseResults;
		      			
		      			NotificationServiceFactory.info($translate('common.notification.message.SUCCESSFULLY_SAVED'));
		      			
		      			$location.path("/listagegroups");
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
    	  $scope.ageGroupUpdateRequest=new Object();
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



