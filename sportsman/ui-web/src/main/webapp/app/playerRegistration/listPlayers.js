'use strict';

define(['app'], function (app) {
  
  app.controller('ListPlayersController', ['$scope','$compile','$rootScope','$location','$filter', 'DTOptionsBuilder','DTColumnBuilder', 
                                               'CommonStorageFactory','NotificationServiceFactory','PubSub','Constants','Page',
                                               'ModalDialogServiceFactory','PlayerServiceFactory','CommonServiceFactory',
                                              function($scope,$compile,$rootScope,$location,$filter, DTOptionsBuilder,DTColumnBuilder,
                                                CommonStorageFactory,NotificationServiceFactory,PubSub,Constants,Page,
                                                ModalDialogServiceFactory,PlayerServiceFactory,CommonServiceFactory) {
	  
	  var $translate = $filter('translate');
		
	  Page.setTitle('List Players');
		
      var baseUrl=$rootScope.baseUrl;
    
      var storageKey = Constants.Keys.authtoken;
	  var authToken = CommonStorageFactory.retrieve(storageKey);
	  $scope.customData = new Object();
	  $scope.serachByList=[];
	  
	  $scope.initializeListPlayresPage = function() {
		  
		  $scope.customData = new Object();
		  $scope.serachByList=[];
		  $scope.serachByList.push(new serchByObject('playerName','Player Name'));
		  $scope.serachByList.push(new serchByObject('playerNumber','Player Number'));
		  $scope.serachByList.push(new serchByObject('nic','IC/Passport'));
		  $scope.serachByList.push(new serchByObject('contactNumber','Contact Number'));
      };
      
      $scope.resetSearch = function() {
    	  $scope.searchBy='';
    	  $scope.searchText='';
    	  $scope.customData = new Object();
  	      vm.dtInstance.reloadData();
      }
      
      function serchByObject(id,value){
    	  this.id=id;
    	  this.value=value;
      }
      
      
     
      
      var vm=this;
      
      vm.dtInstance = {};

      vm.dtOptions = DTOptionsBuilder.newOptions()
       .withOption('ajax', {
           url: baseUrl + '/playerservice/getall',
           type: 'POST',
           data: function ( data ) {
        	   // The returned object has 'email' as property, but the server entity has 'emailAddress'
               // We need to override what we ask to the server here otherwise search will not work
               data.columns[0].data = "person.fullName";
               data.columns[2].data = "person.nic";
               data.columns[3].data = "person.mobileNumber";
               
        	   
               // Any values you set on the data object will be passed along as parameters to the server
        	   data.customData = $scope.customData;

               // Any values you set on the data object will be passed along as parameters to the server
               //data.access_token = AuthenticationService.getAccessToken();
               return JSON.stringify(data);
           },
           headers: { 'Content-Type': 'application/json', 'X-AUTH-TOKEN': authToken }
       })       
       // or here
       .withDataProp('data')// This is the name of the value in the returned recordset which contains the actual data
       .withOption('processing', true)
       .withOption('serverSide', true)
       .withOption('saveState', true)
       .withOption('order', [4, 'asc'])
       .withOption('bFilter', false)
       .withPaginationType('full_numbers')
       .withOption('createdRow', function(row, data, dataIndex) {
              $compile(angular.element(row).contents())($scope);
       })
       .withOption('responsive', true).withOption('bAutoWidth', false).withOption('scrollX', '100%')
       .withDisplayLength(10);
  
      vm.dtColumns = [
          DTColumnBuilder.newColumn('fullName').withTitle($translate('usrmgt.security_user.label.FULL_NAME')),//0
          DTColumnBuilder.newColumn('playerNumber').withTitle('Player Number'),//1
          DTColumnBuilder.newColumn('nic').withTitle('IC/Passport'),//2
          DTColumnBuilder.newColumn('contactNumber').withTitle($translate('usrmgt.security_user.label.MOBILE_NUMBER'))
          .withOption('width', '5%'),//3
          DTColumnBuilder.newColumn('id').withTitle("Player Id").notVisible().notSortable().withOption('searchable', false),//4
          DTColumnBuilder.newColumn(null).withTitle($translate('common.label.text.ACTIONS')).withOption('searchable', false).notSortable().withOption('width','20%')//4
          .renderWith(function(data, type, full, meta) {
              return '<button class="btn btn-info" ng-click="view(' + data.id + ')">' +
                  '   <i class="fa fa-eye"></i>' +
                  '</button>&nbsp;' +
              	  '<button class="btn btn-warning" ng-click="edit(' + data.id + ')">' +
                  '   <i class="fa fa-edit"></i>' +
                  '</button>&nbsp;' +
                  '<button class="btn btn-danger" ng-click="delete(' + data.id + ',' + data.versionNumber + ')">' +
                  '   <i class="fa fa-trash-o"></i>' +
                  '</button>';
          })
      ];
      
      $scope.searchPlayer = function() {
          var searchByVal=$.trim($scope.searchBy);
          var searchByText=$.trim($scope.searchText);
          var customDataMap = new Object(); // or var map = {};
      	if(searchByVal=='playerName'){
      		customDataMap["playerName"] = searchByText;
      	}else if(searchByVal=='playerNumber'){
      		customDataMap["playerNumber"] = searchByText;
      	}else if(searchByVal=='nic'){
      		customDataMap["nic"] = searchByText;
      	}else if(searchByVal=='contactNumber'){
      		customDataMap["contactNumber"] = searchByText;
      	}  
     	    $scope.customData = customDataMap;
     	     vm.dtInstance.reloadData();
        };
      
      
      $scope.edit = function(id) {
          $location.path('/editplayer').search({id: id});
      };
      
      $scope.view = function(id) {
          $location.path('/viewplayer').search({id: id});
      };
      
      
      $scope.delete = function(id,versionNumber) {
    	  
          console.log('Deleting' + id + ' - ' + versionNumber);
          
          var objectDeletionRequest={
    			  id:id,
    			  versionNumber:0
    	  };
          
          ModalDialogServiceFactory.confirmBox(
        		  $translate('common.notification.message.CONFIRM_DELETE_TITLE'), 
        		  $translate('common.notification.message.CONFIRM_DELETE_CONTENT'), 
        		  '', 
        		  $translate('common.button.text.DELETE'), 
        		  $translate('common.button.text.CANCEL'), 
        		  deleteRecord, 
        		  objectDeletionRequest, 
        		  null, 
        		  null
           );
          // Delete some data and call server to make changes...
          // Then reload the data so that DT is refreshed
      };
      
      function deleteRecord(objectDeletionRequest) {
    	  
    	  var response=PlayerServiceFactory.deletePlayer(objectDeletionRequest,baseUrl);		         
          
	        response.success(function(data, status, headers, config) {
    			
    		NotificationServiceFactory.info($translate('common.notification.message.NOTIFY_RECORD_DELETED'));
    		
    		redrawDatatable();
              
          }).error(function(data, status, headers, config){
        	  
          	NotificationServiceFactory.error($translate('common.notification.message.ERROR_WHILE_SAVING_RECORD'));
          	console.error($translate('common.notification.message.ERROR_WHILE_SAVING_RECORD') + " " + data.message);
          	
          	redrawDatatable();
          	
          })    
      }
      
     function redrawDatatable(){
    	 vm.dtInstance.reloadData();
     }
      
           
     function cancelDeleteRecord(recordId) {
          ModalDialogServiceFactory.alert(
        		  $translate('common.notification.message.NOTIFY_RECORD_NOT_DELETED'), 
        		  $translate('common.notification.message.NOTIFY_RECORD_NOT_DELETED_CONTENT'), 
        		  '', 
        		  $translate('common.button.text.OK'), 
        		  null, 
        		  null
          );
      }
      
        
    
  }]);
  

  
});