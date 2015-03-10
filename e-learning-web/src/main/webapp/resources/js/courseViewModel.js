function courseViewModel() {
	var self = this;

	self.courseList = ko.observableArray();
	
	console.log( "courseViewModel()" );

	$.getJSON("http://localhost:8081/courses", function(data) {

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