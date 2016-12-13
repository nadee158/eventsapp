'use strict';

define(['app'], function (app) {

	app.factory('SecurityServiceFactory', ['$http', function($http) {
	
	    
	    var SecurityServiceFactory = {};
	
	    
	    SecurityServiceFactory.signIn = function (user, baseUrl) {
	    	var url = baseUrl + '/SecurityService/SignIn';
	        return $http.post(url, user);
	    };
	    
	    SecurityServiceFactory.signUp = function (signUpRequest, baseUrl) {
	    	var url = baseUrl + '/SecurityService/SignUp';
	        return $http.post(url, signUpRequest);
	    };
	    
	    SecurityServiceFactory.signOut = function (signOutRequest, baseUrl) {
	    	var url = baseUrl + '/SecurityService/SignOut';
	        return $http.post(url, signOutRequest);
	    };
	    
	    SecurityServiceFactory.resetPassword = function (resetPasswordRequest, baseUrl) {
	    	var url = baseUrl + '/SecurityService/ResetPassword';
	        return $http.post(url, resetPasswordRequest);
	    };
	    
	    SecurityServiceFactory.changePassword = function (changePasswordRequest, baseUrl) {
	    	var url = baseUrl + '/SecurityService/ChangePassword';
	        return $http.post(url, changePasswordRequest);
	    };
	    
	    SecurityServiceFactory.validateSession = function (baseUrl) {
	    	var url = baseUrl + '/SecurityService/ValidateSession';
	        return $http.post(url);
	    };
	    
	    return SecurityServiceFactory;
	}]);

});