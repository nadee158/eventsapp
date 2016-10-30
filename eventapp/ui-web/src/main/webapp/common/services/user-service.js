var app = angular.module('app');

app.factory('UserServiceFactory', ['$http', function($http) {

    
    var UserServiceFactory = {};


    UserServiceFactory.listUsers = function (baseUrl) {
    	var url = '/UserService/ListUsers';
    	url=baseUrl + url;
        return $http.post(url);
    };
    


    return UserServiceFactory;
}]);