var wafepaApp = angular.module('wafepaApp.services', []);

wafepaApp.service('activityRestService', function($http) {
	
	this.getActivity = function(id) {
		return $http.get('api/activities/' + id);
	};
	
	this.getActivities = function(request_params) {
		return $http.get('api/activities', { params : request_params });
	};
	
	this.deleteActivity = function(id) {
		return $http.delete('api/activities/' + id);
	};
	
	this.saveActivity = function(activity) {
		if (activity.id) {
			return $http.put('api/activities/' + activity.id, activity);
		} else {
			return $http.post('api/activities', activity);
		}
	};
});

wafepaApp.service('logRestService', function($http) {
	
	this.getLog = function(id) {
		return $http.get('api/logs/' + id);
	};
	
	this.getLogs = function(request_params) {
		return $http.get('api/logs', { params : request_params });
	};
	
	this.deleteLog = function(id) {
		return $http.delete('api/logs/' + id);
	};
	
	this.saveLog = function(log) {
		if (log.id) {
			return $http.put('api/logs/' + log.id, log);
		} else {
			return $http.post('api/logs', log);
		}
	};
});