'use strict';

define(['app'], function (app) {
	
	app.controller('HeaderController', ['$scope', '$rootScope','$location','PubSub','Constants',
	                                    'CommonStorageFactory','SecurityServiceFactory','NotificationServiceFactory','$translate',
	    function ($scope, $rootScope,$location, PubSub, Constants,
	    								 CommonStorageFactory,SecurityServiceFactory,NotificationServiceFactory,$translate) {
		var userDTOObject;
		
		$scope.setLang = function(langKey) {
		    // You can change the language during runtime
		    $translate.use(langKey);
		};
		
		$scope.fn_load = function () {	
			  PubSub.unsubscribe(Constants.Events.loggedin);
			  var sub = PubSub.subscribe(Constants.Events.loggedin, listener); 
			  PubSub.unsubscribe(Constants.Events.loggedout);
			  var sublo = PubSub.subscribe(Constants.Events.loggedout, listenerlogout);  	
			  PubSub.unsubscribe(Constants.Events.displayheaderbuttons);
			  var subShowButton = PubSub.subscribe(Constants.Events.displayheaderbuttons, listenerDisplayHeaderButtons);  
			  checkAndLoadUser();
		};
		
		function listenerDisplayHeaderButtons(topic, data) {
		    $scope.formKey=data.formKey;
		    $scope.showMenu=data.showMenu;
		    $scope.showNext=data.showNext;
		    $scope.showPrevious=data.showPrevious;
		    $scope.showPrint=data.showPrint;
		    $scope.showCopy=data.showCopy;
		    $scope.showEdit=data.showEdit;
		    $scope.showSearch=data.showSearch;
		    $scope.showSave=data.showSave;
		    $scope.showCancel=data.showCancel;
		    $scope.showAddNew=data.showAddNew;
		}
		
		//publish save event
		$scope.publishSaveEvent = function () {		
			PubSub.publish(Constants.Events.saved, {
				formKey: $scope.formKey
			});
		};
		
		//publish next event
		$scope.publishNextEvent = function () {		
			PubSub.publish(Constants.Events.next, {
				formKey: $scope.formKey
			});
		};
		
		//publish previous event
		$scope.publishPreviousEvent = function () {		
			PubSub.publish(Constants.Events.previous, {
				formKey: $scope.formKey
			});
		};
		
		//publish print event
		$scope.publishPrintEvent = function () {		
			PubSub.publish(Constants.Events.print, {
				formKey: $scope.formKey
			});
		};
		
		//publish copy event
		$scope.publishCopyEvent = function () {		
			PubSub.publish(Constants.Events.copy, {
				formKey: $scope.formKey
			});
		};
		
		//publish edit event
		$scope.publishEditEvent = function () {		
			PubSub.publish(Constants.Events.edit, {
				formKey: $scope.formKey
			});
		};
		
		//publish cancel event
		$scope.publishCancelEvent = function () {		
			PubSub.publish(Constants.Events.cancel, {
				formKey: $scope.formKey
			});
		};
		
		//publish cancel event
		$scope.publishAddNewItemEvent = function () {		
			PubSub.publish(Constants.Events.add_new, {
				formKey: $scope.formKey
			});
		};
		
		
		
  	    $scope.logout = function () {
	        
  	    	var userDTOObject=CommonStorageFactory.retrieve(userStorageKey);
  	    	
  	    	var baseUrl=$rootScope.baseUrl;
  	    	
  	    	var SignOutRequest={"userName":userDTOObject.uN,"securityUserId":userDTOObject.suuid};
  	    	 
	  		var response=SecurityServiceFactory.signOut(SignOutRequest,baseUrl);
	        
	        
	        response.success(function(data, status, headers, config) {
	        	if(data.statusCode=200){
		          
		            PubSub.publish(Constants.Events.loggedout, {
		            });
		            
	        	}else{
	        		NotificationServiceFactory.error(data.message);
	        	}        	
        	
	        });
  		 
         
     };
		
		function checkAndLoadUser(){
			if(!(userDTOObject)){
				var userDTOObject=CommonStorageFactory.retrieve(Constants.Keys.userobject);
				if(userDTOObject){
					PubSub.publish(Constants.Events.loggedin, {
	      				userObject: userDTOObject
	      			});
				}else{
					PubSub.publish(Constants.Events.loggedout, {
		            });
				}
			}			
		}
			
		function listener(topic, data) {
		    userDTOObject=data.userObject;
		    $scope.userDTO=userDTOObject;
		    modifyUiElementsOnLogIn();
		}

		function listenerlogout(topic, data) {	
			  //alert('Hiiiiiiiiiiiiiiiiiiiii');
			// Just clear the local storage
  	    	  var userStorageKey= Constants.Keys.userobject; 
			  var storageKey = Constants.Keys.authtoken;
              CommonStorageFactory.clear(storageKey); 
              CommonStorageFactory.clear(userStorageKey); 
              $scope.authenticated = false;
		      $scope.userDTO=null;
		      $location.path("/login");
		      modifyUiElementsOnLogOut();
		}
		
		
		
		
		function modifyUiElementsOnLogOut(){
			console.log('modifyUiElementsOnLogOut');
			$('#sidebar-toggle').click();
			$('#sidebar-toggle').hide();
			$('#mainheadermenu').hide();
			$('#sidebar').hide();
			$("body").addClass('sidebar-collapse').trigger('collapsed.pushMenu');
			//$('#col-exp-Btn').hide();
			$('#usermodules').hide();
			
		}
		
		function modifyUiElementsOnLogIn(){
			$('#sidebar-toggle').click();
			$('#sidebar-toggle').show();
			$('#mainheadermenu').show();
			$('#sidebar').show();
			//$('#col-exp-Btn').show();
			$('body').removeClass('sidebar-collapse');
			$('#usermodules').show();
		}
		
		$scope.displySearch = function($event) {
			$event.stopImmediatePropagation(); 
			$('#dataTable_filter').show();
			$('#closeWindow').show();	
		}
	  }
	]);
});




