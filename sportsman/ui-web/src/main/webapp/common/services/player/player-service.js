'use strict';

define(['app'], function (app) {

	app.factory('PlayerServiceFactory', ['$http', function($http) {
	
	    
	    var PlayerServiceFactory = {};
	
	    
	    PlayerServiceFactory.createPlayer = function (playerCreationRequest,file, baseUrl) {
	    	var url = baseUrl + '/playerservice/create';
	        return $http({
	            method: 'POST',
	            url: url,
	            headers: {'Content-Type': undefined },
	            transformRequest: function (data) {
	                var formData = new FormData();
	                formData.append('playerCreationRequest', new Blob([angular.toJson(data.playerCreationRequest)], {
	                    type: "application/json"
	                }));
	                formData.append("file", data.file);
	                return formData;
	            },
	            data: { playerCreationRequest: playerCreationRequest, file: file }
	        });
	    };
	    
	    PlayerServiceFactory.updatePlayer = function (PlayerUpdateRequest,file, baseUrl) {
	    	if(file){
	    		var url = baseUrl + '/playerservice/updatewithfile';
		        return $http({
		            method: 'POST',
		            url: url,
		            headers: {'Content-Type': undefined },
		            transformRequest: function (data) {
		                var formData = new FormData();
		                formData.append('playerUpdateRequest', new Blob([angular.toJson(data.playerUpdateRequest)], {
		                    type: "application/json"
		                }));
		                formData.append("file", data.file);
		                return formData;
		            },
		            data: { playerUpdateRequest: PlayerUpdateRequest, file: file }
		        });
	    	}else{
	    		var url = baseUrl + '/playerservice/update';
		        return $http.put(url, PlayerUpdateRequest);
	    	}
	    	
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
	    
	    PlayerServiceFactory.count = function (baseUrl) {
	    	var url = baseUrl + '/playerservice/count';
	    	return $http.get(url);
	    };
	    
	    return PlayerServiceFactory;
	}]);

});