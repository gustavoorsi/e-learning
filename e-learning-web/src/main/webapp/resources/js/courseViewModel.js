function courseViewModel() {
	var self = this;

	self.courseList = ko.observableArray();
	
	console.log( "courseViewModel()" );
	
	console.log( $("#restAccessToken").val() );
	
	var token = $("#restAccessToken").val();
	
	$.ajax({
		  url: "http://localhost:8081/api/v1/microservice1Endpoint/courses",
		  dataType: 'json',
		  data: {
			  access_token: token
		  },
		  success: function(data, status) {
		    return console.log("The returned data", data);
		  },
		  beforeSend: function(xhr, settings) { xhr.setRequestHeader('Authorization','Bearer ' + token); } 
		});
	
	
	
//	$.ajaxSetup({
//		  headers : {
//		    'Authorization' : 'bearer ' + $("#restAccessToken").val()
//		  }
//		});
//	
//	
//	$.getJSON("http://localhost:8081/api/v1/microservice1Endpoint/courses", function(data) {
//
//		$.each(data._embedded.courses, function(key, value) {
//			console.log(value);
//			self.courseList.push(value);
//		});
//
//	});

}


$(document).ready(function() {
	var viewModel = new courseViewModel();
	ko.applyBindings(viewModel);
});