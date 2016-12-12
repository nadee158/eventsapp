'use strict';

define(['app','nvd3', 'ui_bootstrap'], function (app, nvd3, ui_bootstrap) {

  app.controller('CategorySetupController', ['$scope', '$rootScope', '$log', '$sce', '$filter',
                                          'NotificationServiceFactory',
                                          'SecurityServiceFactory','Page', 
                                          'EventServiceFactory',
                                          'CategoryServiceFactory',
                                          'CommonServiceFactory',
                                          'ModalDialogServiceFactory',
                                  function($scope, $rootScope, $log, $sce, $filter,
                                		  NotificationServiceFactory,
                                		  SecurityServiceFactory,Page, 
                                		  EventServiceFactory,
                                		  CategoryServiceFactory,
                                		  CommonServiceFactory,
                                		  ModalDialogServiceFactory) {
	  Page.setTitle("Event Setup");
	  
     var $translate = $filter('translate');
	  
	  var baseUrl=$rootScope.baseUrl;
	  
	  $scope.itemDTOList=new Array();
	  
	  $scope.initializeCategorySetupController = function() {
		  loadEvents();
		  loadGenders();
		  loadItemDropDown();
		  itemDTOList=[];
		  $scope.itemDTOList=new Array();
      };
      
      
      
      function CategorySetupItemDTO(itemName,text1,text2){
    	  this.itemName=itemName;
    	  this.text1=text1;
    	  this.text2=text2;
      }
      
      
      $scope.addItem = function() {
    	  var selectBox=$scope.selectBox;
    	  var text1=$scope.text1;
    	  var text2=$scope.text2;
    	  if(selectBox && text1 && text2){
    		  $scope.itemDTOList.push(new CategorySetupItemDTO(selectBox,text1,text2));
    	  }else{
    		  NotificationServiceFactory.error('Enter all required fields'); 
    	  }
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



