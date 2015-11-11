function serverAccess($http, SERVER) {
    this.post = function(data, path) {
        return $http.post(SERVER.url + path);
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
            name: $scope.name,
            email: $scope.email,
            feedback: $scope.feedback
        }, SERVER.usrFeedbackPath);
    };

    self.PartnerSignup = function() {
        self.postData({
            name: $scope.name,
            email: $scope.email,
            employment: $scope.employment
        }, SERVER.ptrSignUpPath);
    };

    self.PartnerFeedBack = function() {
        self.postData({
            name: $scope.name,
            email: $scope.email,
            feedback: $scope.feedback
        }, SERVER.ptrFeedbackPath);
    };
}



angular.module('App', [])
    .service('serverAccess', serverAccess)
    .controller('ServerCtrl', ServerCtrl)
    .constant('SERVER', {
        //enter server address
        url: 'http://localhost',
        //paths
        usrSignUpPath: '',
        usrFeedbackPath: '',
        ptrSignUpPath: '',
        ptrFeedbackPath: ''

    });