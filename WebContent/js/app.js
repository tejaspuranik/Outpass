function serverAccess($http, SERVER) {
    this.post = function(data, path) {
        var postData = JSON.stringify({
            data: [data]
        });
        return $http.post(SERVER.url + path, postData);
    }
}

function ServerCtrl(serverAccess, SERVER, $scope) {
    var self = this;
    self.hasSubmitted = false;

    self.postData = function(data, path) {
        serverAccess.post(data, path)
            .then(function() {
                self.hasSubmitted = true;
            });
    };

    self.UserSignup = function() {
        self.hasSubmitted = true;
        self.postData({
            email: $scope.email
        }, SERVER.usrSignUpPath);
    };

    self.UserFeedback = function() {
        self.postData({
            fullname: $scope.fullname,
            email: $scope.email,
            message: $scope.message,
            origin: 'user'
        }, SERVER.usrFeedbackPath);
    };

    self.PartnerSignup = function() {
        self.postData({
            name: $scope.name,
            email: $scope.email,
            employment: $scope.employment
        }, SERVER.ptrSignUpPath);
    };

    self.PartnerFeedback = function() {
        self.postData({
            fullname: $scope.fullname,
            email: $scope.email,
            message: $scope.message,
            origin: 'partner'
        }, SERVER.ptrFeedbackPath);
    };
}



angular.module('App', [])
    .service('serverAccess', serverAccess)
    .controller('ServerCtrl', ServerCtrl)
    .constant('SERVER', {
        //enter server address
        url: 'http://localhost:8080/Outpass/',
        //paths
        usrSignUpPath: 'register',
        usrFeedbackPath: 'feedback',
        ptrSignUpPath: '',
        ptrFeedbackPath: ''

    });