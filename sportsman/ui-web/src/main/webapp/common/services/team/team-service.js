'use strict';

define(['app'], function (app) {

	app.factory('TeamServiceFactory', ['$http', function($http) {
	
	    
	    var TeamServiceFactory = {};
	
	    
	    TeamServiceFactory.createTeam = function (TeamCreationRequest, baseUrl) {
	    	var url = baseUrl + '/teamservice/create';
	        return $http.post(url, TeamCreationRequest);
	    };
	    
	    TeamServiceFactory.updateTeam = function (TeamUpdateRequest, baseUrl) {
	    	var url = baseUrl + '/teamservice/update';
	        return $http.put(url, TeamUpdateRequest);
	    };
	    
	    TeamServiceFactory.deleteTeam = function (objectDeletionRequest, baseUrl) {
	    	var url = baseUrl + '/teamservice/delete';
	        return $http.post(url, objectDeletionRequest);
	    };
	    
	    TeamServiceFactory.getActiveTeams = function (baseUrl) {
	    	var url = baseUrl + '/teamservice/getactivelist';
	        return $http.get(url);
	    };
	    
	    TeamServiceFactory.getTeamById = function (objectRetrievalRequest, baseUrl) {
	    	var url = baseUrl + '/teamservice/getbyid';
	    	return $http.post(url, objectRetrievalRequest);
	    };
	    
	    TeamServiceFactory.count = function (baseUrl) {
	    	var url = baseUrl + '/teamservice/count';
	    	return $http.get(url);
	    };
	    
	    return TeamServiceFactory;
	}]);

});