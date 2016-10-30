'use strict';

define(['app'], function (app) {

app.factory('shareService', function($rootScope){
    var myValue = 'data';
    return {
        getValue: function () {
            return myValue;
        },
        setValue: function(value) {
          myValue = value;
        }
    };
});

});