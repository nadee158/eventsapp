'use strict';

define(['app'], function (app) {

app.factory('shareArrayService', function($rootScope){
    var myValue = new Array();
    return {
        getValue: function () {
            return myValue;
        },
        setValue: function(value) {
          myValue.push(value);
        },
        removeValue: function(value){
          var filteredValue = new Array();
          angular.forEach(myValue,function(obj){
            console.dir(obj.name);
            if(obj.name == value.name){
              console.log("found machan");
            }else{
              filteredValue.push(obj);
            }
          }); 
          myValue = [];
          myValue = filteredValue;
        }
    };
});

});