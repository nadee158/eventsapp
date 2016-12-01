'use strict';

define(['app','nvd3', 'ui_bootstrap'], function (app, nvd3, ui_bootstrap) {

  app.controller('CategorySetupController', ['$scope', '$rootScope', '$log', '$sce', 
                                          'NotificationServiceFactory',
                                          'SecurityServiceFactory','Page', 
                                          'CategoryServiceFactory',
                                          'CommonServiceFactory',
                                          'ModalDialogServiceFactory',
                                  function($scope, $rootScope, $log, $sce, 
                                		  NotificationServiceFactory,
                                		  SecurityServiceFactory,Page, 
                                		  CategoryServiceFactory,
                                		  CommonServiceFactory,
                                		  ModalDialogServiceFactory) {
	  Page.setTitle("Event Setup");
	  
	  
	  $scope.initializeCategorySetupController = function() {
      	
      };
      
	  
	  
   
   
 }]);
  
  

});



