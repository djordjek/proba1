var wafepaApp = angular.module('wafepaApp.activityController', []);

wafepaApp.controller('ActivityController', function($scope, 
		$location, $routeParams, activityRestService) {
	
	
	$scope.showUp = false;
	
	$scope.showButtons = function() {
		$scope.showUp = true;
	};
	
	$scope.hideAll = function() {
		$scope.showUp = false;
	};
	
	
	$scope.page = 0;
	
	$scope.getActivities = function() {
		var request_params = {};
		if ($scope.search) {
			request_params['name'] = $scope.search;
		}
		
		request_params['page'] = $scope.page;
		
		activityRestService.getActivities(request_params)
				.success(function(data, status, headers) {
					$scope.activities = data;
					$scope.totalPages = headers('total-pages');
					$scope.successMessage = 'Everything OK';
				})
				.error(function() {
					$scope.errorMessage = 'Oops, something went wrong.';
				});
	};
	
	$scope.deleteActivity = function(id, index) {
		activityRestService.deleteActivity(id)
				.success(function() {
					$scope.activities.splice(index, 1);
					$scope.showUp = false;
				})
				.error(function() {
					
				});
	};
	
	$scope.initActivity = function() {
		$scope.activity = {};
		
		if ($routeParams && $routeParams.id) {
			activityRestService.getActivity($routeParams.id)
					.success(function(data) {
						$scope.activity = data;
						$scope.activity.updated = new Date(data.updated);
						$scope.disableUpdated = true;
					})
					.error(function() {
						
					});
		}
	};
	
	$scope.saveActivity = function() {
		activityRestService.saveActivity($scope.activity)
				.success(function() {
					$location.path('/activities');
				})
				.error(function() {
					
				});
	};
	
	$scope.changePage = function(page) {
		$scope.page = page;
		$scope.getActivities();
	};
});