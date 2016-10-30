'use strict';

define(['app'], function (app) {

	app.directive("mdChipsDisableInput", ["$timeout",
         function($timeout) {
             return {
                 link: function($scope, $element, $attrs) {
                     $timeout(function() {
                         var input = $element.find("input");
                         input.attr("disabled", "");
                     }, 100);
                 }
             }
         }
     ]);

});