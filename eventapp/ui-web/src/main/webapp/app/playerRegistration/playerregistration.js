'use strict';

define(['app', 'ui_bootstrap'], function (app, ui_bootstrap) {

  app.controller('PlayerRegistrationController', ['$scope', '$rootScope', '$log', '$sce', '$filter', '$compile', 
                                          'NotificationServiceFactory',
                                          'SecurityServiceFactory','Page', 
                                          'PlayerServiceFactory',
                                          'CategoryServiceFactory',
                                          'EventServiceFactory',
                                          'CommonServiceFactory',
                                          'ModalDialogServiceFactory',
                                  function($scope, $rootScope, $log, $sce, $filter,$compile,
                                		  NotificationServiceFactory,
                                		  SecurityServiceFactory,Page, 
                                		  PlayerServiceFactory,
                                		  CategoryServiceFactory,
                                		  EventServiceFactory,
                                		  CommonServiceFactory,
                                		  ModalDialogServiceFactory) {
	  Page.setTitle("Player Registration");
	  
	  var $translate = $filter('translate');
	  
	  var baseUrl=$rootScope.baseUrl;
	  
	  $scope.buttonVisible = true;
	  
	  $scope.events=[];
	  $scope.categories=[];
	  
	  $scope.initializePRController = function() {
		  $scope.imageUrl=$rootScope.uiBaseUrl + '/assets/app/images/avatar_2x.png';
		  loadEvents();
      };
      
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
      
      var myDropzone = new Dropzone("#fileUploadBtn", { 
    	  url: "#",
    	  maxFilesize:0.5,
    	  uploadMultiple:false,
    	  addRemoveLinks:true,
    	  previewsContainer:'#imagePreview',
    	  thumbnailWidth:200,
          thumbnailHeight:200,
          maxFiles:1,
          acceptedFiles:'image/jpeg,image/png,image/gif,image/jpg,image/bmp',
          autoProcessQueue: false,
          parallelUploads:1,
          init: function() {
        	    
        	    myDropzone = this;
        	    
        	    // Using a closure.
                var _this = this;
          	  
                var myEl = angular.element(document.querySelector('#fileRemoveBtn'));
    	        myEl.bind("click", function(){
    	        	 _this.removeAllFiles();
    	        	  $scope.$apply(function(){
    	        		  $scope.buttonVisible = true;
    	        	 });
    	        });
          	    
        	    
        	    this.on("addedfile", function(file) {
        	    	$scope.$apply(function(){
  	        		  $scope.buttonVisible = false;
  	        	    });
        	    	$scope.file=file;
        	    	console.log('addedfile' + $scope.buttonVisible)
        	    	$('#imageSuccessMessage').html("Added!");
        	    });  //will remove background after file added
        	    
        	    
        	    this.on("maxfilesexceeded", function(file){
        	    	console.log('maxfilesexceeded')
                    this.removeFile(file);
                    $('#imageErrorMessage').html("No more files please!");
                    $scope.$apply(function(){
  	        		  $scope.buttonVisible = true;
  	        	    });
                });
                this.on('error', function(file, response) {
                	  console.log('error')
                      var errorMessage = "Error";
                      this.removeFile(file);
                      $('#imageErrorMessage').html(response);
                      $scope.$apply(function(){
    	        		  $scope.buttonVisible = true;
    	        	 });
                });
                this.on('success', function(file, response) {
                    $('#imageSuccessMessage').html(response);
                    $scope.file=file;
                    $scope.$apply(function(){
  	        		  $scope.buttonVisible = false;
  	        	    });
                    console.log('success' + $scope.buttonVisible)
                });
          }
      });
      

      $scope.registerPlayer= function() {
    	    
      	    angular.forEach($scope.playerRegistrationForm.$error.required, function(field) {
        	    field.$setDirty();
        	    
        	});
      	
        	if($scope.playerRegistrationForm.$valid) {
        		
        		ModalDialogServiceFactory.confirmBox(
              		  $translate('common.notification.message.CONFIRM_SUBMIT_TITLE'), 
              		  $translate('common.notification.message.CONFIRM_SUMIT_CONTENT'), 
              		  '', 
              		  $translate('common.button.text.SUBMIT'), 
              		  $translate('common.button.text.CANCEL'), 
              		  submitPlayerRegistrationRequest, 
              		  $scope.playerCreationRequest, 
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
      
      function submitPlayerRegistrationRequest(playerCreationRequest){
      	
        	var response=PlayerServiceFactory.createPlayer(playerCreationRequest,$scope.file,baseUrl);		         
            
  	        response.success(function(data, status, headers, config) {
  	        	if(data.apiResponseStatus){
  	        		if(data.apiResponseStatus==200){
  	        			$scope.playerCreationResponse=data.apiResponseResults;
  		      			
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



