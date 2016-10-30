'use strict';

define(['app','ngStorage'], function (app, ngStorage) {

    app.factory('CommonStorageFactory', function ($localStorage) {
      
      return {   
        store : function(storageKey, storageItem) {
        	return  $localStorage[storageKey] = storageItem;
        },
        retrieve : function(storageKey) {
        	  return $localStorage[storageKey];
        },
        clear : function(storageKey) {         
        	  return delete $localStorage[storageKey];
        },
        put: function (storageKey, storageItem) {
        	return  $localStorage[storageKey] = storageItem;
	    },
	    get: function (storageKey) {
	    	 return $localStorage[storageKey];
	    }
      };
      
    });

});