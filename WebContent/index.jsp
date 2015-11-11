<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.7/angular.min.js"></script>
<!-- <script>
var app = angular.module('myApp', []);

function MyController($scope, $http) {
$scope.getDataFromServer = function() {
	
        $http.get("AngularAction").success(
                function(data, status, headers, config) {
                        $scope.person = data;
                        
                }).error(function(data, status, headers, config) {
                                // called asynchronously if an error occurs
                                // or server returns response with an error status.
        });
};
};

</script> -->

<script>
function testService($http) {
	this.get = function() {
		return $http.get('/Outpass/AngularAction')
		.then(function(res) {
			return res.data.personData;
			
		});
	}
};

function TestCtrl(testService){
	var self = this;
	
	self.getMessage = function() {
		testService.get()
		.then(function(personData) {
			self.firstName = personData.firstName;
			self.lastName = personData.lastName;
		});
	}
};

var app = angular.module('myApp', []);
app.service('testService', testService);
app.controller('TestCtrl', TestCtrl);
</script>
</head>
<body>

<div data-ng-app="myApp">
        <div data-ng-controller="TestCtrl as test">
                <button data-ng-click="test.getMessage()">
                Fetch data from server
                </button>
                <p>First Name : {{test.firstName}}</p>
                <p>Last Name : {{test.lastName}}</p>
               
        </div>

</div>
</body>
</html>