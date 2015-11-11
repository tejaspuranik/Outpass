<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.7/angular.min.js"></script>
<script>
function testService($http) {
	this.upperCase = function(data) {
		return $http.post('/Outpass/register',data)
		
	}
};

function TestCtrl(testService){
	var self = this;
	
	self.postData = function(message) {
		  testService.upperCase({message: message})
		    .success(function(body) {
		      self.sendEmail = body.message;
		    })
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
                <input ng-model="test.sendEmail" name="email" placeholder="Enter a email address">
                <button ng-click="test.postData(test.sendEmail)">uppercase</button>
				
        </div>

</div>
</body>
</html>