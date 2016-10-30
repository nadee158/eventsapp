'use strict';

define(['app','angular-datatables'], function (app, datatables) {
  
  app.controller('ServerSideProcessingCtrl', ['$scope', '$rootScope', 'CommonStorageFactory', 'DTOptionsBuilder','DTColumnBuilder', 
                                              function($scope, $rootScope, CommonStorageFactory,DTOptionsBuilder,DTColumnBuilder) {
    
	  var storageKey = Constants.Keys.authtoken;
    var authToken = CommonStorageFactory.retrieve(storageKey);
      
    var vm = this;
      vm.dtOptions = DTOptionsBuilder.newOptions()
          .withOption('ajax', {
           // Either you specify the AjaxDataProp here
           // dataSrc: 'data',
           url: 'http://localhost:8080/templateappservices/UserService/ListUsers',
           type: 'POST',
           data: function ( d ) {
               return JSON.stringify(d);
           },
           headers: { 'Content-Type': 'application/json', 'X-AUTH-TOKEN': authToken }
       })
       // or here
       .withDataProp('data')
          .withOption('processing', true)
          .withOption('serverSide', true)
          .withPaginationType('full_numbers');
      vm.dtColumns = [
          DTColumnBuilder.newColumn('id').withTitle('ID'),
          DTColumnBuilder.newColumn('firstName').withTitle('First name'),
          DTColumnBuilder.newColumn('lastName').withTitle('Last name').notVisible()
      ];
    
  }]);
  
  app.controller('ListUsersController', ['$scope', '$rootScope',  
                                              function($scope, $rootScope) {
    
  }]);
  
});

/*
var app = angular.module('app');

app.controller('ServerSideProcessingCtrl', ['$scope', '$rootScope', 'CommonStorageFactory', 'DTOptionsBuilder', 'DTColumnBuilder',
    function ($scope, $rootScope, CommonStorageFactory, DTOptionsBuilder, DTColumnBuilder) {
	
	var storageKey = 'auth_token';
	var authToken = CommonStorageFactory.retrieve(storageKey);
		
	var vm = this;
    vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption('ajax', {
         // Either you specify the AjaxDataProp here
         // dataSrc: 'data',
         url: 'http://localhost:8080/templateappservices/UserService/ListUsers',
         type: 'POST',
         data: function ( d ) {
             return JSON.stringify(d);
         },
         headers: { 'Content-Type': 'application/json', 'X-AUTH-TOKEN': authToken }
     })
     // or here
     .withDataProp('data')
        .withOption('processing', true)
        .withOption('serverSide', true)
        .withPaginationType('full_numbers');
    vm.dtColumns = [
        DTColumnBuilder.newColumn('id').withTitle('ID'),
        DTColumnBuilder.newColumn('firstName').withTitle('First name'),
        DTColumnBuilder.newColumn('lastName').withTitle('Last name').notVisible()
    ];
	}
]);

app.controller('ListUsersController', ['$scope', '$rootScope', 'UserServiceFactory',
    function ($scope, $rootScope, UserServiceFactory) {
  
        var baseUrl=$rootScope.baseUrl;
        
        $scope.listUsers = function() {
          var response=UserServiceFactory.listUsers(baseUrl);
                      
          response.success(function(data, status, header, config) {
                console.log(header());
                console.log(config);
                $scope.ListUsersReponse = data;
                console.log(data);
                
            }).error(function(data, status, header, config){
                console.log(header());
                console.log(config);
                
                $scope.ListUsersReponse = data;
                console.log(data);
            })
        }
  }
]);
*/