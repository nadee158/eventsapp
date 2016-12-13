'use strict';

define(['app'], function (app) {
	app.controller('AddUserRolesToSecurityUserController', ['$scope','$compile','$rootScope','$location','$filter','DTOptionsBuilder','DTColumnBuilder',
	                                                        'CommonStorageFactory','NotificationServiceFactory','PubSub','Constants','Page',
	                                                        'ModalDialogServiceFactory','UserRoleServiceFactory','SecurityUserServiceFactory','CommonServiceFactory',
	                                                        function($scope,$compile,$rootScope,$location,$filter,DTOptionsBuilder,DTColumnBuilder,
	                                                                CommonStorageFactory,NotificationServiceFactory,PubSub,Constants,Page,
	                                                                ModalDialogServiceFactory,UserRoleServiceFactory,SecurityUserServiceFactory,CommonServiceFactory) {
		
		  var $translate = $filter('translate');
			
		  Page.setTitle($translate('usrmgt.security_user.pagetitle.ADDUSERROLETOSECURITYUSER_SECURITY_USER'));
		  
		  $scope.existingUserRoleList=[];
		  $scope.existingUserRoleListShow=false;

	      var baseUrl=$rootScope.baseUrl;
	      
	      var securityUserId = 0;
	      
	      var storageKey = Constants.Keys.authtoken;
		  var authToken = CommonStorageFactory.retrieve(storageKey);
		  var copyPermissionList=[];
		  
		  
		   var self=this;
	        self.userRoleList=[];
	       var copyUserRoleList=[];
	        var indexOriginal=0;
	        var index=0;
	        
	        
	        $scope.selectedUserRoles=false;
	        var baseUrl=$rootScope.baseUrl;
		  
		  $scope.initializeUserRoleforSecurityUserPage = function() {
			  var queryString = $location.search();
			    securityUserId=queryString["id"]
			    $scope.securityUserPermissionUpdateRequest={};
    		    $scope.securityUserPermissionUpdateRequest.id=securityUserId;
    		    $scope.securityUserPermissionUpdateRequest.userRoles=[];
		      	displayTopMenuButtons();
		      	subscribeToTopMenuButtonEvents();
		      	displayUserRolesBySecurityId(securityUserId);
		      };
		      
		      function displayTopMenuButtons(){
		     		var formKey='add_userroleforsecurityuser_screen';
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
							    showSave:CommonServiceFactory.checkIfPermitted("ROLE_A_UM_SU_USU_UDT"),
							    showCancel:CommonServiceFactory.checkIfPermitted("ROLE_A_UM_SU_LSU_VWE ROLE_A_UM_SU_LSU_UDT"),
							    showAddNew:false,
			       };
		      	PubSub.publish(Constants.Events.displayheaderbuttons,topMenuButtonDisplay);
		      }
		      
		      function subscribeToTopMenuButtonEvents(){
		       		//first unsubscribe from previous subscribed events
		       		PubSub.unsubscribe(Constants.Events.saved);
		       		PubSub.unsubscribe(Constants.Events.cancel);
		       		//then subscribe to new events
		        	var subTopMenuSaveButton = PubSub.subscribe(Constants.Events.saved, listenerSaveClicked);
		        	var subTopMenuCancelButton = PubSub.subscribe(Constants.Events.cancel, listenerCancelClicked);
		        }
		      
		     	 function listenerSaveClicked(topic, data) {
//					    var formKey=data.formKey;
//					    if(formKey==$scope.formKey){
					    	addUserRoleForSecurityUser();
//					    }
					}
		     	 
		         function listenerCancelClicked(topic, data) {
					    var formKey=data.formKey;
					    if(formKey==$scope.formKey){
					    	$location.path('/listsecurityusers');
					    }
					}
		      
		      
		      var vm=this;
		      
		      vm.dtInstance = {};

		      vm.dtOptions = DTOptionsBuilder.newOptions()
		       .withOption('ajax', {
		           url: baseUrl + '/UserRoleService/GetUserRoles',
		           type: 'POST',
		           data: function ( d ) {
		        	   // The returned object has 'email' as property, but the server entity has 'emailAddress'
		               // We need to override what we ask to the server here otherwise search will not work
		               //data.columns[1].data = "emailAddress";

		               // Any values you set on the data object will be passed along as parameters to the server
		               //data.access_token = AuthenticationService.getAccessToken();
		               return JSON.stringify(d);
		           },
		           headers: { 'Content-Type': 'application/json', 'X-AUTH-TOKEN': authToken }
		       })       
		       // or here
		       .withDataProp('data')// This is the name of the value in the returned recordset which contains the actual data
		       .withOption('processing', true)
		       .withOption('serverSide', true)
		       .withOption('saveState', true)
		       .withOption('order', [0, 'asc'])
		       .withPaginationType('full_numbers')
		       .withOption('createdRow', function(row, data, dataIndex) {
		              $compile(angular.element(row).contents())($scope);
		       })
		       .withOption('responsive', true).withOption('bAutoWidth', false)
		       .withDisplayLength(10);
		  
		      vm.dtColumns = [
		          DTColumnBuilder.newColumn('userRoleName').withTitle($translate('usrmgt.user_role.label.USER_ROLE_NAME')).withOption('width', '15%'),//0
		          DTColumnBuilder.newColumn('permissions').withTitle($translate('usrmgt.user_role.label.permissions')).withOption('searchable', false).notSortable()//1
		          .renderWith(function(data, type, full, meta) {
		        	  if(data){
		        		  var text='<md-chips>';
		            	  
		            	  for(var i = 0; i < data.length; i++) {
		            		  text=text + '<md-chip>' + data[i].permissionName + '</md-chip>';
		    			  }
		            	  
		            	  text=text + '</md-chips>';
		                  return  text;
		        	  }
		          }),          
		          
		          DTColumnBuilder.newColumn('id').withTitle("UserRole Id").notVisible().notSortable().withOption('searchable', false),//2
		          DTColumnBuilder.newColumn(null).withTitle($translate('common.label.text.ACTIONS')).withOption('searchable', false).notSortable()//3
		          .renderWith(function(data, type, full, meta) {
		              return '<button class="btn btn-info" ng-click="addUserRole('+data.id+',\'' + data.userRoleName +'\')">' +
		                  ' <i class="fa fa-plus"></i>' +
		                  '</button>';
		          })
		      ];
		      
		      function displayUserRolesBySecurityId(securityUserId){

		    	  var userRoleListRetrievalRequest={securityUserId:securityUserId};
		    	  var response=UserRoleServiceFactory.getUserRolesBySecurityUserId(userRoleListRetrievalRequest,baseUrl);
		    	  response.success(function(data, status, headers, config) {	
		    		  
		    		  if(data.dtoList){
		    			  angular.forEach(data.dtoList, function(userRole, index) {
		    				  $scope.securityUserPermissionUpdateRequest.userRoles.push(userRole.id);
				   		  });
		    			  $scope.existingUserRoleList=data.dtoList;
		      			  $scope.existingUserRoleListShow=true;
		      			}else{
		      				$scope.existingUserRoleListShow=false;
		      				NotificationServiceFactory.info($translate('usrmgt.user_role.validation.message.NO_EXISTING_USER_ROLES'));
		      			}
		    				    		  
			        }).error(function(data, status, headers, config){
			            	NotificationServiceFactory.error(data.message);
			            	console.error('Error while getting User Roles ' + data.message);
			        })
		      }
		      		      
		        $scope.addUserRole=function(id,userRoleName) {	
		        	
		        	if(!(checkIfAlreadyAvailableFromExistingList(id))){
		        		
		        		if(self.userRoleList.length==0){
			        		self.userRoleList[indexOriginal]={
		        				'id' : id,
	    			            'userRoleName' : userRoleName
		        			};
			        		copyUserRoleList[indexOriginal]={
	        					'id' : id,
	    			            'userRoleName' : userRoleName	
	        			};
			        		console.log(self.userRoleList.length);
		        			indexOriginal++;
			        	}else{
			        		if(!(checkIfAlreadyAvailable(id))) {
			        			self.userRoleList[indexOriginal]={
				        				'id' : id,
			    			            'userRoleName' : userRoleName
				        			};
					        		copyUserRoleList[indexOriginal]={
			        					'id' : id,
			    			            'userRoleName' : userRoleName	
			        			};
					        		console.log(self.userRoleList.length);
				        			indexOriginal++;
			       			}else{
			       				NotificationServiceFactory.info($translate('usrmgt.user_role.validation.message.USER_ROLE_CODE'));
			       			}
			        	}
			        	  $scope.selectedUserRoles=true;
		        	}else{
		        		NotificationServiceFactory.info($translate('usrmgt.user_role.validation.message.USER_ROLE_CODE'));
		        	}
		        	
		        };
		        
		        function checkIfAlreadyAvailableFromExistingList(id){
		        	var isAlreadyAvailable1=false;
		        	angular.forEach($scope.existingUserRoleList, function(existingUserRole, index) {
		   				if(id==existingUserRole.id) {
		   					isAlreadyAvailable1=true;
		       			}
		   			});
		        	return isAlreadyAvailable1;
		        }
		        
		        function checkIfAlreadyAvailable(id){
		        	var isAlreadyAvailable=false
		        	angular.forEach(copyUserRoleList, function(copyUserRoleList, index) {
		   				if(id==copyUserRoleList.id) {
		   					isAlreadyAvailable=true;
		       			}
		   			});
		        	return isAlreadyAvailable;
		        }
		        
		        $scope.removeElement=function(indexOfRemoveElelement){
		        	copyUserRoleList.splice(indexOfRemoveElelement, 1);
		        	indexOriginal=indexOriginal-1;
		        	if(copyUserRoleList.length==0){
		        		$scope.selectedUserRoles=false;
		        	}
		        }
		        
		        $scope.hideExistingUserrole=function(){
		        	if($scope.existingUserRoleList.length==0){
		        		$scope.existingUserRoleListShow=false;
		        		NotificationServiceFactory.info($translate('usrmgt.user_role.validation.message.NO_EXISTING_USER_ROLES'));
		        	}
		        }
		        
		        function addUserRoleForSecurityUser(){
		        	$scope.securityUserPermissionUpdateRequest.id=securityUserId;
		        	$scope.securityUserPermissionUpdateRequest.userRoles=[];
		        			        	
		        	angular.forEach($scope.existingUserRoleList, function(userRole, index) {
	    				  $scope.securityUserPermissionUpdateRequest.userRoles.push(userRole.id);
			   		});
		        	angular.forEach($scope.listUserRoles.userRoleList, function(userRole, index) {
	    				  $scope.securityUserPermissionUpdateRequest.userRoles.push(userRole.id);
			   		});
		        
		        	ModalDialogServiceFactory.confirmBox(
		              		  $translate('common.notification.message.CONFIRM_SUBMIT_TITLE'), 
		              		  $translate('common.notification.message.CONFIRM_SUMIT_CONTENT'), 
		              		  '', 
		              		  $translate('common.button.text.SUBMIT'), 
		              		  $translate('common.button.text.CANCEL'), 
		              		  submitUserRoleForSecurityUser, 
		              		  $scope.securityUserPermissionUpdateRequest, 
		              		  null, 
		              		  null
		                );
		        }
		        
		        function submitUserRoleForSecurityUser(securityUserPermissionUpdateRequest){
		        			        	
		        	var response=SecurityUserServiceFactory.updateUserRolesOfSecurityUser($scope.securityUserPermissionUpdateRequest, baseUrl);
		        	
		        	response.success(function(data, status, headers, config) {
			      		if(data.status==409){
			      			NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_UPDATING_RECORD') + ' ' + $translate(data.message));
			      		}else{			      			
			      			NotificationServiceFactory.info($translate('common.notification.message.SUCCESSFULLY_UPDATED'));
			      			
			      			$location.path("/listsecurityusers");
			      		}	
		                
		            }).error(function(data, status, headers, config){
		            	NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_UPDATING_RECORD'));
		            	console.error($translate('common.notification.message.ERROR_WHILE_UPDATING_RECORD') + " " + data.message);
		            }) 
		        	
		        }
     
	  } 
	
	]);
});