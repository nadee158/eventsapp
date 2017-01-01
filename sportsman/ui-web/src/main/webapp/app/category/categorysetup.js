'use strict';

define(['app','nvd3', 'ui_bootstrap'], function (app, nvd3, ui_bootstrap) {

  app.controller('CategorySetupController', ['$scope', '$rootScope', '$log', '$sce', '$filter', '$location',
                                          'NotificationServiceFactory',
                                          'SecurityServiceFactory','Page', 
                                          'EventServiceFactory',
                                          'GradeBeltServiceFactory',
                                          'AgeGroupServiceFactory',
                                          'CategoryServiceFactory',
                                          'CommonServiceFactory',
                                          'ModalDialogServiceFactory',
                                  function($scope, $rootScope, $log, $sce, $filter, $location,
                                		  NotificationServiceFactory,
                                		  SecurityServiceFactory,Page, 
                                		  EventServiceFactory,
                                		  GradeBeltServiceFactory,
                                		  AgeGroupServiceFactory,
                                		  CategoryServiceFactory,
                                		  CommonServiceFactory,
                                		  ModalDialogServiceFactory) {
	Page.setTitle("Category Setup");
	  
    var $translate = $filter('translate');
	  
	var baseUrl=$rootScope.baseUrl;
	  
	  
	  
	  $scope.initializeCategorySetupController = function() {
		  loadEvents();
		  loadAgeGroups();
		  loadGenders();
		  loadItemDropDown();
		  loadGradeOrBelts();
		  resetForm();
		 
      };
      
      
      function loadGradeOrBelts(){
    	  var response=GradeBeltServiceFactory.getActiveGradeBelts(baseUrl);	
    	  response.success(function(data, status, headers, config) {	
      		if(data.apiResponseResults.dtoList){
      			$scope.gradeOrBelts=data.apiResponseResults.dtoList;	
      		} 
	        }).error(function(data, status, headers, config){
	            	NotificationServiceFactory.error(data.message);
	            	console.error('Error while getting events ' + data.message);
	        })
      }
      
      
      $scope.createCategory= function() {
    	    
      	   angular.forEach($scope.categorySetupForm.$error.required, function(field) {
        	    field.$setDirty();
           });
      	
        	if($scope.categorySetupForm.$valid) {
        		
        		ModalDialogServiceFactory.confirmBox(
              		  $translate('common.notification.message.CONFIRM_SUBMIT_TITLE'), 
              		  $translate('common.notification.message.CONFIRM_SUMIT_CONTENT'), 
              		  '', 
              		  $translate('common.button.text.SUBMIT'), 
              		  $translate('common.button.text.CANCEL'), 
              		  submitCategory, 
              		  $scope.categoryCreationRequest, 
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
      
      function submitCategory(categoryCreationRequest){
        	//Do something
        	var response=CategoryServiceFactory.createCategory(categoryCreationRequest,baseUrl);		         
            
  	        response.success(function(data, status, headers, config) {
  	        	if(data.apiResponseStatus){
  	        		if(data.apiResponseStatus==200){
  	        			$scope.categoryCreationResponse=data.apiResponseResults;
  		      			
  		      			NotificationServiceFactory.info($translate('common.notification.message.SUCCESSFULLY_SAVED'));
  		      			
  		      			$location.path("/listcategories");
  		      		}else{
  		      			NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_SAVING_RECORD') + ' ' + $translate(data.apiResponseResults.message));
  		      			
  		      		}	
  	        	}
  	      		
                
            }).error(function(data, status, headers, config){
            	 NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_SAVING_RECORD'));
            	 console.error($translate('common.notification.message.ERROR_WHILE_SAVING_RECORD') + " " + data);
            }) 
        }
        
      
      
      function CategorySetupItemDTO(itemName,text1,text2){
    	  this.itemName=itemName;
    	  this.text1=text1;
    	  this.text2=text2;
      }
      
      $scope.resetCategoryForm = function() {
    	  ModalDialogServiceFactory.confirmBox(
          		  'Confirm Form Reset', 
          		  'Are you sure you want to clear the form content?', 
          		  '', 
          		  'Ok', 
          		  $translate('common.button.text.CANCEL'), 
          		  resetForm, 
          		  null, 
          		  null, 
          		  null
            );
      };
      
      function resetForm(){
    	  $scope.categoryCreationRequest=new Object();
    	  $scope.categoryCreationRequest.categorySetupItems=new Array();
    	  clearItemForm();
      }
      
      $scope.exitCategoryForm = function() {
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
      };
      
      function exitForm(){
    	  $location.path("/listcategories");
      }
      
      $scope.addItem = function() {
    	  var selectBox=$scope.selectBox;
    	  var text1=$scope.text1;
    	  var text2=$scope.text2;
    	  if(selectBox && text1 && text2){
    		  $scope.categoryCreationRequest.categorySetupItems.push(new CategorySetupItemDTO($.trim(selectBox),$.trim(text1),$.trim(text2)));
    		  clearItemForm();
    	  }else{
    		  NotificationServiceFactory.error('Enter all required fields'); 
    	  }
      }
      
      function clearItemForm(){
    	  $scope.selectBox='';
    	  $scope.text1='';
    	  $scope.text2='';
      }
      
      function loadItemDropDown(){
    	  $scope.itemDropDowns=["test1","test2","test3"];
      }
      
      function loadEvents(){
    	  var response=EventServiceFactory.getActiveEvents(baseUrl);	
    	  response.success(function(data, status, headers, config) {	
      		if(data.apiResponseResults.dtoList){
      			$scope.events=data.apiResponseResults.dtoList;	
      		} 
	        }).error(function(data, status, headers, config){
	            	NotificationServiceFactory.error(data.message);
	            	console.error('Error while getting events ' + data.message);
	        })
      }
      
      function loadAgeGroups(){
    	  var response=AgeGroupServiceFactory.getActiveAgeGroups(baseUrl);	
    	  response.success(function(data, status, headers, config) {	
      		if(data.apiResponseResults.dtoList){
      			$scope.ageGroups=data.apiResponseResults.dtoList;	
      		} 
	        }).error(function(data, status, headers, config){
	            	NotificationServiceFactory.error(data.message);
	            	console.error('Error while getting events ' + data.message);
	        })
      }
      
      function loadGenders(){
    	  var response=CommonServiceFactory.listGenders(baseUrl);	
    	  response.success(function(data, status, headers, config) {	
      		if(data.apiResponseResults.dtoList){
      			$scope.genders=data.apiResponseResults.dtoList;	
      		} 
	        }).error(function(data, status, headers, config){
	            	NotificationServiceFactory.error(data.message);
	            	console.error('Error while getting events ' + data.message);
	        })
      }
	  
   
   
 }]);
  
  

});



