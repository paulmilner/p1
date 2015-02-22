var app = angular.module("MyApp", []);

app.controller("WorkerController", function($scope, $http) {
  $http.defaults.headers.common["X-Custom-Header"] = "Angular.js";
  $http.get('http://localhost:8080/p1/rest/worker/list').
    success(function(data, status, headers, config) {
      $scope.workers = data;
    });
});