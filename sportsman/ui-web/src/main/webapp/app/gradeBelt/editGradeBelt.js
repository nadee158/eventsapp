'use strict';

define(['app','nvd3', 'ui_bootstrap', 'moment'], function (app, nvd3, ui_bootstrap, moment) {

  app.controller('EditGradeBeltController', ['$scope', '$rootScope', '$log', '$sce', '$filter', '$location',
                                          'NotificationServiceFactory',
                                          'Page', 
                                          'GradeBeltServiceFactory',
                                          'CommonServiceFactory',
                                          'ModalDialogServiceFactory',
                                  function($scope, $rootScope, $log, $sce, $filter, $location,
                                		  NotificationServiceFactory,
                                		  Page, 
                                		  GradeBeltServiceFactory,
                                		  CommonServiceFactory,
                                		  ModalDialogServiceFactory) {
	  Page.setTitle("Edit Age Group");
	  
	  var $translate = $filter('translate');
	  
	  var baseUrl=$rootScope.baseUrl;
	  
	  var gradeBeltId = 0;
	  
	  
	  $scope.initializeGradeBeltPage = function() {
		  $scope.activeStatus ='Active';
		  var queryString = $location.search();
		  gradeBeltId=queryString["id"]
		  loadGradeBelt(gradeBeltId);
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
      
      function loadGradeBelt(gradeBeltId){
      	var ObjectRetrievalRequest={id:gradeBeltId};
      	
      	var response=GradeBeltServiceFactory.getGradeBeltById(ObjectRetrievalRequest,baseUrl);		         
          
	        response.success(function(data, status, headers, config) {
	      	
	        	  var objectRetrievalResponse=data;
    			  var dto=data.apiResponseResults.dto;
    			  setRecordStatus(dto.recordStatus);
	        	  $scope.gradeBeltUpdateRequest=dto;
    			
          }).error(function(data, status, headers, config){
          	NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_LOADING_RECORD'));
          	console.error($translate('common.notification.message.ERROR_WHILE_LOADING_RECORD') + " " + data.message);
          })    
      }
      
       
    $scope.updateGradeBelt= function() {
    
    	angular.forEach($scope.gradeBeltSetupForm.$error.required, function(field) {
      	    field.$setDirty();
      	});
    	
      	if($scope.gradeBeltSetupForm.$valid) {
      		
      		if($scope.gradeBeltUpdateRequest.toAge<=$scope.gradeBeltUpdateRequest.fromAge){
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
              		  $scope.gradeBeltUpdateRequest, 
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
      

      
      
      function submitEvent(gradeBeltUpdateRequest){
    	
      	//Do something
      	var response=GradeBeltServiceFactory.updateGradeBelt(gradeBeltUpdateRequest,baseUrl);		         
          
	        response.success(function(data, status, headers, config) {
	        	if(data.apiResponseStatus){
	        		if(data.apiResponseStatus==200){
	        			$scope.eventCreationResponse=data.apiResponseResults;
		      			
		      			NotificationServiceFactory.info($translate('common.notification.message.SUCCESSFULLY_SAVED'));
		      			
		      			$location.path("/listgradebelts");
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
    	  $scope.gradeBeltUpdateRequest=new Object();
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



