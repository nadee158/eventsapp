'use strict';

define(['app'], function (app) {

	app.factory('CommonServiceFactory', ['ModalDialogServiceFactory', 'NotificationServiceFactory', 'Constants', 'CommonStorageFactory',
	                                     '$translate', '$rootScope', '$http','PubSub', 
	                            function (ModalDialogServiceFactory, NotificationServiceFactory, Constants, CommonStorageFactory,
	                                      $translate,$rootScope,$http,PubSub) {
		
           
	  var alertTitles = {};
   	    
      var updateItems = function() {
        	alertTitles={};
        	
        	$translate('common.notification.message.NOTIFY_401_TITLE').then(function(label) {
            	alertTitles['401_TITLE']=label;
            });
            $translate('common.notification.message.NOTIFY_401_CONTENT').then(function(label) {
            	alertTitles['401_CONTENT']=label;
            });
            
            $translate('common.notification.message.NOTIFY_403_TITLE').then(function(label) {
            	alertTitles['403_TITLE']=label;
            });
            $translate('common.notification.message.NOTIFY_403_CONTENT').then(function(label) {
            	alertTitles['403_CONTENT']=label;
            });
            
            $translate('common.notification.message.NOTIFY_404_TITLE').then(function(label) {
            	alertTitles['404_TITLE']=label;
            });
            $translate('common.notification.message.NOTIFY_404_CONTEN').then(function(label) {
            	alertTitles['404_CONTENT']=label;
            });
            
            $translate('common.notification.message.NOTIFY_500_TITLE').then(function(label) {
            	alertTitles['500_TITLE']=label;
            });
            $translate('common.notification.message.NOTIFY_500_CONTEN').then(function(label) {
            	alertTitles['500_CONTENT']=label;
            });
            
            $translate('common.button.text.OK').then(function(label) {
            	alertTitles['BUTTON_TEXT']=label;
            });
            
      };
        
	  updateItems();
	    
	  $rootScope.$on('$translateChangeSuccess', updateItems);
		
		
       return {   
    	parseQueryString : function(str) {
 		    var objURL = {};
 		    str.replace(
 		        new RegExp( "([^?=&]+)(=([^&]*))?", "g" ),
 		        function( $0, $1, $2, $3 ){
 		            objURL[ $1 ] = $3;
 		        }
 		    );
 		    return objURL;
        },
        handleResponseError : function(statusCode) {
        	NotificationServiceFactory.error(alertTitles[(statusCode + '_CONTENT')]);
        	if(statusCode==401){
        		PubSub.publish(Constants.Events.loggedout, {});
        	}
//        	ModalDialogServiceFactory.alert(
//        		  alertTitles[(statusCode + '_TITLE')], 
//        		  alertTitles[(statusCode + '_CONTENT')], 
//          		  '', 
//          		  alertTitles['BUTTON_TEXT'], 
//          		  null, 
//          		  null
//            );
        },
        checkIfPermitted : function(permissions) {
        	var permitted=false;
        	
        	var userStorageKey= Constants.Keys.userobject; 
  	    	var userDTOObject=CommonStorageFactory.retrieve(userStorageKey);
			//console.log('userDTOObject')
			//console.log(userDTOObject);
  	    	
  	    	if(userDTOObject){
  	    		if(userDTOObject.aulst){
  	    			var attributes = permissions.split(" ");
					for(var i in attributes){
						for(var j in userDTOObject.aulst){
							var userRole=userDTOObject.aulst[j];
							//console.log('useRole ' + userRole);
							//console.log('attributes[i] ' + attributes[i]);
							if(userRole == attributes[i]){
								permitted = true;
							}
						}
					}
  	    		}
  	    	}
  	    	
  	    	return permitted;
        },
        validateCaptcha: function (request,baseUrl) {
            var url = baseUrl + '/Captcha/validate';
            var promise = $http.post(url, request);
            console.log('Calling ' + url);
            return promise;
        }
      };
	}]);
	
	
	

	

});