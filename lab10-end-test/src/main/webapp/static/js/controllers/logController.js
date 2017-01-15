var wafepaApp = angular.module('wafepaApp.logController', []);

wafepaApp.controller('LogController', function($scope, $routeParams, $http, $location, activityRestService, logRestService) {
	
	$scope.message = false;
	$scope.haslogs = false;
	
	var array1 = [];
	var array2 = [];
	$scope.deleteLogg = function(id, index, haslogs){
		
		if(haslogs){
			array1.push(id);
			array2.push(index);
		}
		else{
			array2.splice(index-1,1);
			array1.splice(index-1,1);
		}
	}
	

		$scope.deleteSelectedLog = function() {

		for (var i = 0; i < array1.length; i++) {
			$scope.deleteLog1(array1[i],array2[i],i);
		}

	};
	
	$scope.fromPatern = '^[0-5][0-9]min$';
	
	$scope.getLogs = function() {
		
		array1 = [];
		array2 = [];
		
		var request_params = {};
		
		if ($scope.logDate && $scope.from && $scope.to) {
			if($scope.from < $scope.to){
				request_params['from'] = $scope.from;
				request_params['to'] = $scope.to;
			}
			
			request_params['logDate'] = $scope.logDate;
			
		}
		
		logRestService.getLogs(request_params)
				.success(function(data) {
					$scope.logs = data;
					$scope.successMessage = 'Everything OK';
				})
				.error(function() {
					$scope.errorMessage = 'Oops, something went wrong.';
				});
	};
	
	$scope.initLog = function() {
		$scope.log = {};
		var request_params = {};
		
		if ($routeParams && $routeParams.id) {
			logRestService.getLog($routeParams.id)
					.success(function(data) {
						$scope.log = data;
						$scope.activityId = data.activity.id;
					})
					.error(function() {
						
					});
		}
		
		
		activityRestService.getActivities(request_params)
				.success(function(data) {
					$scope.activities = data;
					if ($routeParams && $routeParams.id) {
						var i = 0;
						var activitiesNumber = data.length;
						for (i = 0; i < activitiesNumber; i++) {
							if(data[i].id == $scope.activityId){
								$scope.log.activity = $scope.activities[i];// select appropriate id from drop down
								break;
							}
						}
					}else{
						$scope.log.activity = $scope.activities[0]; // save method
					}
				})
				.error(function() {
					
				});
	};
	
	$scope.saveLog = function() {
		logRestService.saveLog($scope.log)
			.success(function() {
				$location.path('/logs');
			})
			.error(function() {
				
			});

	};
	
	$scope.deleteLog1 = function(id, index,i) {
		logRestService.deleteLog(id)
				.success(function() {
					
					for (var i = 0; i < array1.length; i++){
						$scope.logs.splice(index, 1);
					}
					$scope.message = array1.length + "logs deleted";
				})
				.error(function() {
					
				});
	};
	
	
	
	$scope.deleteLog = function(id, index) {
		logRestService.deleteLog(id)
				.success(function() {
					$scope.logs.splice(index, 1);
				})
				.error(function() {
					
				});
	};
});