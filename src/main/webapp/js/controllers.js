var app = angular.module("MyApp", []);

app.controller("TodgerController", function($scope, $http) {
  $http.defaults.headers.common["X-Custom-Header"] = "Angular.js";
  $http.get('http://localhost:8080/p1/rest/todger/list').
    success(function(data, status, headers, config) {
      $scope.todgers = data;
    });
});