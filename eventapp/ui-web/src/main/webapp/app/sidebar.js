'use strict';

define(['app'], function (app) {
	
	app.controller('SideBarController', ['$scope', '$rootScope','$location','PubSub','Constants',
	                                    'CommonStorageFactory','CommonServiceFactory',
	    function ($scope, $rootScope,$location, PubSub, Constants,
	    								 CommonStorageFactory,CommonServiceFactory) {
	
		
		$scope.hasUserAnyRole = function (permissions) {	
			 return CommonServiceFactory.checkIfPermitted(permissions);
		};
		
	  }
	]);
});
