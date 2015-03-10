CoursesViewModel = function() {
	
	var self = this;
    self.courses = ko.observableArray();
    
    $.getJSON("http://localhost:8081/courses").
    then(function(courses) {
    	
    								
    								$.each( courses._embedded.courses, function(){
    																	alert( this.courseTopic );
    																	
//    																	self.courses.push(
//    																						{
//    																							courseTopic : this.courseTopic
//    																						}
//    																					);
    								} )
    		
    	
    });
    
};

$(document).ready(function() {
	ko.applyBindings(new CoursesViewModel() );
});
