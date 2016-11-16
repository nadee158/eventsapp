'use strict';

define(['app'], function (app) {
  app.factory('TokenAuthInterceptorFactory', ['$q','$cookies','$log','CommonStorageFactory','Constants', 
                                              function($q,$cookies,$log, CommonStorageFactory,Constants) {
  
      var storageKey = Constants.Keys.authtoken;
      
      var TokenAuthInterceptorFactory = {};
      
      TokenAuthInterceptorFactory.request = function (config) {
    	 var token = $cookies.get('XSRF-TOKEN');

         if (token) {
            config.headers['X-XSRF-TOKEN'] = token;
            $log.info("X-XSRF-TOKEN: " + token);
         }  
    	  
        var authToken = CommonStorageFactory.retrieve(storageKey);
        //console.log("authToken--"+authToken);
        if (authToken) {
          config.headers[Constants.Headers.xauthtoken] = authToken;
        }
        return config;
      };
      
      TokenAuthInterceptorFactory.responseError = function (error) {
        if (error.status === 401 || error.status === 403) {
          CommonStorageFactory.clear(storageKey);
        }
        return $q.reject(error);
      };
  
      return TokenAuthInterceptorFactory;
  }]);
});