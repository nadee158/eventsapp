'use strict';

define(['app'], function (app) {

	app.factory('ReportServiceFactory', ['$http', function($http) {
	
	    
	    var ReportServiceFactory = {};
	
	    
	    ReportServiceFactory.generateReport = function (createReportRequest, baseUrl) {
	    	var url = baseUrl + '/ReportSerive/CreateReport';
        return $http.post(url, createReportRequest);
	    };
	    
	    ReportServiceFactory.loadReport = function (url) {
        return $http.get(url,{responseType:'arraybuffer'});
	    };
	
	    ReportServiceFactory.loadReportList = function (baseUrl) {
	      var url = baseUrl + '/ReportSerive/ReportList';
        return $http.get(url);
	    };
	    
	    ReportServiceFactory.reportParams = function (baseUrl, fileName) {
        var url = baseUrl + '/ReportSerive/ReportParams';
        return $http.get(url, {params:{jasperName:fileName}});
      };
      
      ReportServiceFactory.generateCustomReport = function (baseUrl, fileName, paramList) {
        var url = baseUrl + '/ReportSerive/CreateCustomReport';
        return $http.get(url, {params:{reportParams:paramList, fileName:fileName}});
      };
	
	    return ReportServiceFactory;
	}]);

});