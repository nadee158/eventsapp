var app = angular.module('app');

app.factory('FeedbackServiceFactory', ['$http', function($http) {

    
    var FeedbackServiceFactory = {};

    
    FeedbackServiceFactory.submitFeedBack = function (feedBack, baseUrl) {
    	var url = baseUrl + '/FeedbackService/SubmitFeedBack';
        return $http.post(url, feedBack);
    };
    


    return FeedbackServiceFactory;
}]);