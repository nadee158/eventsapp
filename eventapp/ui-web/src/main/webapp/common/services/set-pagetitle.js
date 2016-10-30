'use strict';

define(['app'], function (app) {

app.factory('Page', function($rootScope){
    return {
        setTitle: function(title){
            $rootScope.title = title;
        }
    }
});

});