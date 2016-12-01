'use strict';

define(['app'], function (app) {

	app.factory('PlayerServiceFactory', ['$http', function($http) {
	
	    
	    var PlayerServiceFactory = {};
	
	    
	    PlayerServiceFactory.createPlayer = function (PlayerCreationRequest, baseUrl) {
	    	var url = baseUrl + '/playerservice/create';
	        return $http.post(url, PlayerCreationRequest);
	    };
	    
	    PlayerServiceFactory.updatePlayer = function (PlayerUpdateRequest, baseUrl) {
	    	var url = baseUrl + '/playerservice/update';
	        return $http.put(url, PlayerUpdateRequest);
	    };
	    
	    PlayerServiceFactory.deletePlayer = function (objectDeletionRequest, baseUrl) {
	    	var url = baseUrl + '/playerservice/delete';
	        return $http.post(url, objectDeletionRequest);
	    };
	    
	    PlayerServiceFactory.getActivePlayers = function (baseUrl) {
	    	var url = baseUrl + '/playerservice/getactivelist';
	        return $http.get(url);
	    };
	    
	    PlayerServiceFactory.getPlayerById = function (objectRetrievalRequest, baseUrl) {
	    	var url = baseUrl + '/playerservice/getbyid';
	    	return $http.post(url, objectRetrievalRequest);
	    };
	    
	    
	    return PlayerServiceFactory;
	}]);

});