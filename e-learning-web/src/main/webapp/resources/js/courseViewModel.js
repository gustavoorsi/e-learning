function courseViewModel() {
	var self = this;

	self.courseList = ko.observableArray();
	
	console.log( "courseViewModel()" );
	
	console.log( $("#restAccessToken").val() );
	
	var token = $("#restAccessToken").val();
	
	$.ajaxSetup({
		  headers : {
		    'Authorization' : 'bearer ' + $("#restAccessToken").val()
		  }
		});
	
	
	$.getJSON("/api/service1/courses", function(data) {

		$.each(data._embedded.courses, function(key, value) {
			console.log(value);
			self.courseList.push(value);
		});

	});

}


$(document).ready(function() {
	var viewModel = new courseViewModel();
	ko.applyBindings(viewModel);
});