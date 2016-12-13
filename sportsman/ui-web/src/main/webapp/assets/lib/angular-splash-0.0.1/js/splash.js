//https://github.com/popdevelop/angular-splash-demo
//No version given hence we are versioning it as 0.0.1
angular.module('ui.splash', ['ui.bootstrap', 'ngAnimate'])
.service('$splash', [
  '$uibModal',
  '$rootScope',
  function($uibModal, $rootScope) {
    return {
      open: function (attrs, opts) {
        var scope = $rootScope.$new();
        angular.extend(scope, attrs);
        opts = angular.extend(opts || {}, {
          backdrop: false,
          scope: scope,
          templateUrl: 'splash/content.html',
          windowTemplateUrl: 'splash/index.html'
        });
        return $uibModal.open(opts);
      }
    };
  }
]);
