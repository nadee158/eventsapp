'use strict';

define(['app'], function (app) {
	app.controller('EditPlayerController', ['$scope', '$rootScope','$location','$filter', 
	                                            'PlayerServiceFactory',
	                                            'CommonStorageFactory',
	                                            'CategoryServiceFactory',
	                                            'EventServiceFactory',
	                                            'NotificationServiceFactory',
	                                            'PubSub','Constants','Page','ModalDialogServiceFactory','CommonServiceFactory',
	    function ($scope, $rootScope,$location,$filter, 
	    										PlayerServiceFactory,
	    										CommonStorageFactory,
	    										CategoryServiceFactory,
	                                   		    EventServiceFactory,
	    										NotificationServiceFactory,
	    										PubSub,Constants,Page,ModalDialogServiceFactory,CommonServiceFactory) {
			
		    var $translate = $filter('translate');
		
			Page.setTitle('Edit Player');
			
			var playerId = 0;
	  
	        var baseUrl=$rootScope.baseUrl;
	        
	        $scope.buttonVisible = true;
	        
	        $scope.initializeEditPlayerPage = function() {
	        	$scope.imageUrl=$rootScope.uiBaseUrl + '/assets/app/images/avatar_2x.png';
	        	$scope.playerUserUpdateRequest={};
	        	 loadEvents();
	        	var queryString = $location.search();
	        	playerId=queryString["id"]
	        	loadEditedPlayer(playerId);
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
	        
	        $scope.getEventTypes=function(eventId){
	          	var eventTypeListRequest={id:eventId};
	          	var response=CategoryServiceFactory.getCategoryByEventId(eventTypeListRequest,baseUrl);	        	 
	          	response.success(function(data, status, headers, config) {	
	    	      		$scope.categories=data.apiResponseResults.dtoList;		                
	    	        }).error(function(data, status, headers, config){
	    	            	NotificationServiceFactory.error(data.message);
	    	            	console.error('Error while creating Application ' + data.message);
	    	        })
	          }
	        
	        
	        
	        
	        $scope.updatePlayer= function() {
	        	
	        	angular.forEach($scope.editPlayerForm.$error.required, function(field) {
	        	    field.$setDirty();
	        	});
	        	
	        	if($scope.editPlayerForm.$valid) {
	        		
	        		ModalDialogServiceFactory.confirmBox(
	              		  $translate('common.notification.message.CONFIRM_SUBMIT_TITLE'), 
	              		  $translate('common.notification.message.CONFIRM_SUMIT_CONTENT'), 
	              		  '', 
	              		  $translate('common.button.text.SUBMIT'), 
	              		  $translate('common.button.text.CANCEL'), 
	              		  submitPlayer, 
	              		  $scope.playerUserUpdateRequest, 
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
	        };
	        
	        
	        function submitPlayer(playerUserUpdateRequest){
	          	
	        	var response=PlayerServiceFactory.updatePlayer(playerUserUpdateRequest,$scope.file,baseUrl);		         
	            
	  	        response.success(function(data, status, headers, config) {
	  	        	if(data.apiResponseStatus){
	  	        		if(data.apiResponseStatus==200){
	  	        			$scope.playerCreationResponse=data.apiResponseResults;
	  		      			
	  		      			NotificationServiceFactory.info($translate('common.notification.message.SUCCESSFULLY_SAVED'));
	  		      			
	  		      			$location.path("/listplayers");
	  		      		}else{
	  		      			NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_SAVING_RECORD') + ' ' + $translate(data.apiResponseResults.message));
	  		      			
	  		      		}	
	  	        	}
	  	      		
	                
	            }).error(function(data, status, headers, config){
	            	 NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_SAVING_RECORD'));
	            	 console.error($translate('common.notification.message.ERROR_WHILE_SAVING_RECORD') + " " + data);
	            }) 
	        }
	        
	        function loadEditedPlayer(playerId){
	          	var ObjectRetrievalRequest={id:playerId};
	          	
	          	var response=PlayerServiceFactory.getPlayerById(ObjectRetrievalRequest,baseUrl);		         
	              
	    	      response.success(function(data, status, headers, config) {
	    	      	
	    	        	  $scope.playerUserUpdateRequest=data.apiResponseResults.dto;
	    	        	  $scope.imageUrl=baseUrl + '/commonservice/findfile?fn=' + data.apiResponseResults.dto.profileImagePath;
	    	        	  $scope.getEventTypes(data.apiResponseResults.dto.eventId);
	        			
	              }).error(function(data, status, headers, config){
	              	NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_LOADING_RECORD'));
	              	console.error($translate('common.notification.message.ERROR_WHILE_LOADING_RECORD') + " " + data.message);
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
		      	  $scope.playerUserUpdateRequest=new Object();
		      	  loadEditedPlayer(playerId);
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
		        	$location.path("/listplayers");
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
	          
	  } 
	
	]);
});