'use strict';

define(['app'], function (app) {
	app.controller('UpdateUserProfileController', ['$scope', '$rootScope','$location','$filter','SecurityUserServiceFactory',
	                                            'CommonStorageFactory','NotificationServiceFactory','PubSub','Constants','Page','ModalDialogServiceFactory','DocumentManagementServiceFactory','CommonServiceFactory',
	    function ($scope, $rootScope,$location,$filter,SecurityUserServiceFactory, 
	    										CommonStorageFactory,NotificationServiceFactory,PubSub,Constants,Page,ModalDialogServiceFactory,DocumentManagementServiceFactory,CommonServiceFactory) {
		
		 var $translate = $filter('translate');
			
			Page.setTitle($translate('usrmgt.security_user.pagetitle.UPDATE_USER_PROFILE'));
			
		    $scope.refkey = "Reference Key";
		    $scope.downloadurl = "Download URL";
			
	        var baseUrl=$rootScope.baseUrl;
	        var securityUserId=1;
	        var personId=0;
	        $scope.prefixes=[];
	        $scope.maritalStatusList=[];
	        var index=0;
	        var imageReferenceKey=null;
	        $scope.defaultImage=false;
	        $scope.uploadImage=false;
	        
	        $scope.initializeUpdateUserProfilePage = function() {
	        	
	        	$scope.securityUserProfileUpdateRequest={};
//	        	$scope.securityUserProfileUpdateRequest.prefix={};
//	        	$scope.securityUserProfileUpdateRequest.maritalStatus={};
	        	$scope.securityUserProfileUpdateRequest.securityuserId=securityUserId;
	        	$scope.securityUserProfileUpdateRequest.personId;
	        	$scope.securityUserProfileUpdateRequest.fullName;
	        	$scope.securityUserProfileUpdateRequest.nic;
	        	$scope.securityUserProfileUpdateRequest.mobileNumber;
	        	$scope.securityUserProfileUpdateRequest.landNumber;
	        	$scope.securityUserProfileUpdateRequest.email;
	        	$scope.securityUserProfileUpdateRequest.address;
	        	$scope.securityUserProfileUpdateRequest.dateOfBirth;
	        	$scope.securityUserProfileUpdateRequest.profileImagePath;
	        	$scope.securityUserProfileUpdateRequest.prefixCode;
	        	$scope.securityUserProfileUpdateRequest.prefixName;
	        	$scope.securityUserProfileUpdateRequest.maritalStatusCode;
	        	$scope.securityUserProfileUpdateRequest.maritalStatusName;
	        	
	        	displayTopMenuButtons();
	        	subscribeToTopMenuButtonEvents();
	        	getPrefixes();
	        	geMaritalStatus();
	        	getSecurityUserById(securityUserId);
	        	
	        
	        };
	        
	    	function displayTopMenuButtons(){
	       		var formKey='update_user_profile';
	       	    $scope.formKey=formKey;
	        	var topMenuButtonDisplay={
	        			formKey:formKey,
					    showMenu:true,
					    showNext:false,
					    showPrevious:false,
					    showPrint:false,
					    showCopy:false,
					    showEdit:false,
					    showSearch:false,
					    showSave:CommonServiceFactory.checkIfPermitted("ROLE_A_UM_SU_UPR_UDT"),
					    showCancel:CommonServiceFactory.checkIfPermitted("ROLE_A_UM_SU_LSU_VWE ROLE_A_UM_SU_LSU_UDT"),
					    showAddNew:CommonServiceFactory.checkIfPermitted("ROLE_A_UM_SU_ASU_VWE"),
	        	};
	        	PubSub.publish(Constants.Events.displayheaderbuttons,topMenuButtonDisplay);
	        }
	    	
	       	function subscribeToTopMenuButtonEvents(){
	       		//first unsubscribe from previous subscribed events
	       		PubSub.unsubscribe(Constants.Events.saved);
	       		PubSub.unsubscribe(Constants.Events.cancel);
	       		PubSub.unsubscribe(Constants.Events.add_new);
	       		//then subscribe to new events
	        	var subTopMenuSaveButton = PubSub.subscribe(Constants.Events.saved, listenerSaveClicked);
	        	var subTopMenuCancelButton = PubSub.subscribe(Constants.Events.cancel, listenerCancelClicked);
	        	var subTopMenuEditButton = PubSub.subscribe(Constants.Events.add_new, listenerAddNewClicked);
	        }
	       	
	        function listenerSaveClicked(topic, data) {
			    var formKey=data.formKey;
			    if(formKey==$scope.formKey){
			    	editApplication();
			    }
			}
	        
	        function listenerAddNewClicked(topic, data) {
	    	    var formKey=data.formKey;
	    	    if(formKey==$scope.formKey){
	    	    	$location.path("/addsecurityuser");
	    	    }
	    	  }
	        
	        function listenerCancelClicked(topic, data) {
			    var formKey=data.formKey;
			    if(formKey==$scope.formKey){
			    	$location.path('/listsecurityusers');
			    }
			}
	        
	        
	        function getPrefixes(){
	        	var response=SecurityUserServiceFactory.getPrefixes(baseUrl);	        	 
	        	response.success(function(data, status, headers, config) {		      			
		      		$scope.prefixes=data.dtoList;
		        }).error(function(data, status, headers, config){
		            	NotificationServiceFactory.error(data.message);
		            	console.error('Error while creating SecurityUser ' + data.message);
		        })
	        }
	        
	        function geMaritalStatus(){
	        	var response= SecurityUserServiceFactory.getMaritalStatus(baseUrl);
	        	response.success(function(data, status, headers, config) {		      			
	        		$scope.maritalStatusList=data.dtoList;        
		        }).error(function(data, status, headers, config){
	            	NotificationServiceFactory.error(data.message);
	            	console.error('Error while creating SecurityUser ' + data.message);
	        	})
	        }
	        
	        
	        function getSecurityUserById(securityUserId){
	        	var objectRetrievalRequest={id:securityUserId};
	        	var response=SecurityUserServiceFactory.getSecurityUserById(objectRetrievalRequest, baseUrl);	        	 
	        	response.success(function(data, status, headers, config) {	
		      		$scope.securityUserProfileUpdateRequest1=data.dto;
		      		personId=data.dto.person.id;
		      		$scope.securityUserProfileUpdateRequest1.person.dateOfBirth =new Date(data.dto.person.dateOfBirth);
		      		imageReferenceKey=data.dto.person.profileImagePath;		      		
		      		if(!(imageReferenceKey=='')){
		      			$scope.uploadImage=true;
		      			retrieveFile();
		      		}else{
		      			$scope.defaultImage=true;
		      		} 		
		        }).error(function(data, status, headers, config){
		            	NotificationServiceFactory.error(data.message);
		            	console.error('Error while creating Application ' + data.message);
		        })
	        }
	        	        
	        $scope.uploadFile = function(){
	            var file = $scope.myFile;
	            console.log('file is ' );
	            console.dir(file);
	            DocumentManagementServiceFactory.uploadFileToUrl(file,baseUrl).success(function(data){
	            	$scope.refkey = data.referenceKey;
	            	$scope.createdDocumentInfo = data;
	            	imageReferenceKey=data.referenceKey;
	            	retrieveFile();
	            	$scope.defaultImage=false;
	            	$scope.uploadImage=true;
	        	});
	        };
	        
	        function retrieveFile(){	        	
	        	DocumentManagementServiceFactory.retrieveFile(imageReferenceKey, baseUrl).success(function(data){
	        		$scope.downloadurl = data.downloadURL;
	        		$scope.retrievedDocumentInfo = data;
	        	});
	        	
	        }
	        
	        
		function editApplication(){
							
			angular.forEach($scope.updateUserProfileForm.$error.required, function(field) {
        	    field.$setDirty();
        	});
			
			console.log($scope.securityUserProfileUpdateRequest1.person.dateOfBirth);
			var dateOfBirth=$filter('date')($scope.securityUserProfileUpdateRequest1.person.dateOfBirth, 'yyyy-MM-dd');			

		 	if($scope.updateUserProfileForm.$valid) {
		 		$scope.securityUserProfileUpdateRequest.personId=personId;
		 		$scope.securityUserProfileUpdateRequest.fullName=$scope.securityUserProfileUpdateRequest1.person.fullName;
	        	$scope.securityUserProfileUpdateRequest.nic=$scope.securityUserProfileUpdateRequest1.person.nic;
	        	$scope.securityUserProfileUpdateRequest.mobileNumber=$scope.securityUserProfileUpdateRequest1.person.mobileNumber;
	        	$scope.securityUserProfileUpdateRequest.landNumber=$scope.securityUserProfileUpdateRequest1.person.landNumber;
	        	$scope.securityUserProfileUpdateRequest.email=$scope.securityUserProfileUpdateRequest1.person.email;
	        	$scope.securityUserProfileUpdateRequest.address=$scope.securityUserProfileUpdateRequest1.person.address;
	        	$scope.securityUserProfileUpdateRequest.dateOfBirth=dateOfBirth;
	        	$scope.securityUserProfileUpdateRequest.profileImagePath=imageReferenceKey; 
	        	
	    		angular.forEach($scope.prefixes,function(prefix,index){
					if(prefix.code==$scope.securityUserProfileUpdateRequest1.person.prefix.code){
						$scope.securityUserProfileUpdateRequest.prefixCode=prefix.code;
			        	$scope.securityUserProfileUpdateRequest.prefixName=prefix.name;
					}
	    		});
	    			 		
				angular.forEach($scope.maritalStatusList,function(maritalStatus,index){
					if(maritalStatus.code==$scope.securityUserProfileUpdateRequest1.person.maritalStatus.code){
						$scope.securityUserProfileUpdateRequest.maritalStatusCode=maritalStatus.code;
			        	$scope.securityUserProfileUpdateRequest.maritalStatusName=maritalStatus.name;
					}
			});

	        	
        		ModalDialogServiceFactory.confirmBox(
              		  $translate('common.notification.message.CONFIRM_SUBMIT_TITLE'), 
              		  $translate('common.notification.message.CONFIRM_SUMIT_CONTENT'), 
              		  '', 
              		  $translate('common.button.text.SUBMIT'), 
              		  $translate('common.button.text.CANCEL'), 
              		  submitApplication, 
              		  $scope.securityUserProfileUpdateRequest, 
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
		
		
		function submitApplication(securityUserProfileUpdateRequest){
			
			
			var response=SecurityUserServiceFactory.updateSecurityUserProfile($scope.securityUserProfileUpdateRequest,baseUrl);	
			
			  response.success(function(data, status, headers, config) {
		        	
		        	if(data.status==409){
		      			NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_SAVING_RECORD') + ' ' + $translate(data.message));
		      		}else{
		      			$scope.ApplicationCreation=data;
		      			
		      			NotificationServiceFactory.info($translate('common.notification.message.SUCCESSFULLY_UPDATED'));
		      			
		      			$location.path("/listsecurityusers");
		      		}	
		      			
	            }).error(function(data, status, headers, config){
	            	NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_UPDATING_RECORD'));
	            	console.error($translate('common.notification.message.ERROR_WHILE_UPDATING_RECORD') + " " + data.message);
	            }) 
		}

	 }]);
	
	// Start of Retreive & Upload Image
	
	  app.directive('fileModel', ['$parse', function ($parse) {
	      return {
	          restrict: 'A',
	          link: function(scope, element, attrs) {
	              var model = $parse(attrs.fileModel);
	              var modelSetter = model.assign;
	              
	              element.bind('change', function(){
	                  scope.$apply(function(){
	                      modelSetter(scope, element[0].files[0]);
	                  });
	              });
	          }
	      };
	  }]);
	  	
});