<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page isELIgnored="false" %> 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>  
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>Bookings</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="./js/hotel.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />
<script>
$(function() {
$("#tabs").tabs({
activate:function(event, ui){

if(ui.index==1){
alert("tab2 is selected");
}
}
});
});
</script>
</head>
<body>
<div id="tabs">
<ul>
<li><a href="#tabs-1">Upcoming Bookings</a></li>
<li><a href="#tabs-2">Completed Bookings</a></li>
<li><a href="#tabs-3">Cancelled Bookings</a></li>
</ul>
<div id="tabs-1">
<p>Upcoming Booking(s)</p>
<div id="Up_Bookings">
		<table border="1" id="tblBooking">
		<tr><th>BookingId</th><th>Check-in-Date</th><th>Check-out-Date</th><th>Status</th><th>Cancel</th></tr>
		</table>
	</div>
</div>
<div id="tabs-2">
<p>Completed Booking(s)</p>
<div id="Comp_Booking">
		<table border="1" id="tblCompleted">
		<tr><th>BookingId</th><th>Check-in-Date</th><th>Check-out-Date</th><th>Status</th><th>Review</th></tr>
		</table>
	</div>
</div>
<div id="tabs-3">
<p>Cancelled Booking(s)</p>
<div id="Cancelled_bookings">
		<table border="1" id="tblCancelled">
		<tr><th>BookingId</th><th>Check-in-Date</th><th>Check-out-Date</th><th>Status</th></tr>
		</table>
	</div>
</div>
</div>
</div>
</div>
<div class="modal" id="questionModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Rating</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">        
        <div class="col">
        	<input class="form-control" type="hidden" id="modal_hotelId"/>
        	Please rate your Stay: <input  class="form-control" type="number" id="modal_stay"/>
        	Please rate your food: <input  class="form-control" type="number" id="modal_food"/>
        	Please rate your cleanliness: <input class="form-control" type="number" id="modal_cleanliness"/>
        	
        	Please Review:  <input class="form-control" type="text" id="modal_review"/>
        	
        	
        	<input style="margin-top:25px" class="btn btn-saveRating form-control btn-primary" type="button" id="" value="SAVE"/>       	
        </div>
        
      </div>
      
      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>
</body>
</html>