'use strict';

define(['app'], function (app) {

	  app.factory('CookieStorageService', ['$cookieStore', function ($cookieStore) {
		  
	    return {
	      	get: function (name) {
	          return $cookieStore.get(name);
	        },

	      
	        set: function (name, value) {
	          $cookieStore.put(name, value);
	        },

	        put: function (name, value) {
	          $cookieStore.put(name, value);
	        }
	      
	    }
	  }]);

});


