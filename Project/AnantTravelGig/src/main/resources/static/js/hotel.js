$(document).ready(function(){
	
	
	$("#searchBtn").click(function(){
		var searchText = $("#searchLocation").val();
		$('#tblHotel tr').not(':first').remove();
		$.get("http://localhost:9872/searchHotel/"+searchText, {
			}, function(responseText) {
				$.each(responseText, function(key1, value1) {
				$("#tblHotel").append("<tr><td>"+value1.hotelName+"</td><td><img class='hotelImage' src='"+value1.imageURL+"' width='300' heigth='300'/></td><td>"+value1.averagePrice+"</td><td>"+value1.starRating+"</td></tr>")
			});
								 
		 });		
	});
	
	
$("#filterBtn").click(function(){
	var price = parseInt($("#priceValue").text());
	var flag = 0;
	$('#tblHotel tr').not(':first').each(function() {
            $(this).show();
            var hotelPrice = parseInt($(this).children("td").eq(2).text());
            var starRating = parseInt($(this).children("td").eq(3).text());
            var hotelAmenities = ($(this).children("td").eq(4).text());    
            $('.star_rating').each(function () {
                    if (this.checked) {
                   var rating = $(this).val();
                   if(rating==starRating){
                        flag = 1;     
                   } 
                   }
            });
            if(flag==0)
            {
                $(this).hide();
            }
            else if(hotelPrice>price){
                $(this).hide();    
            }else{
             let row = $(this);
              let amenityList = new Set();
              var searchText = "Radission";
              $.get("http://localhost:9872/searchHotel/"+searchText, {
            }, function(responseText) {
                $.each(responseText, function(key1, value1) {
                
                for(var i in value1.amenities){
                 amenityList = ([value1.amenities[i].name]);
                alert(amenityList);
                if($("#amenity_parking")[0].checked && !(amenityList == "Parking")) {
                row.hide();
              };
              
              if ($("#amenity_checkin_checkout")[0].checked && !(amenityList == "Check In Check Out")) {
                row.hide();
              };
              
              if ($("#amenity_breakfast")[0].checked && !(amenityList == "Breakfast")) {
                row.hide();
              };
              
              if($("#amenity_bar_lounge")[0].checked && !(amenityList == "Bar" ||  "Lounge")) {
                  row.hide();
              };
              
              if ($("#amenity_fitness_center")[0].checked && !(amenityList == "Fitness Center")) {
                  row.hide();
              };
                }
                });
                });
              
           }   
              
        });
      });
	
$("#tblHotel").on('click','.hotelImage',function() {
        var hotelName = $(this).parent().parent().children("td").eq(0).text();
        $("#myModal").modal();
        $("#modal_hotelName").val(hotelName);    
        $("#modal_noGuests").val($("#noGuests").val());
        $("#modal_checkInDate").val($("#checkInDate").val());
        $("#modal_checkOutDate").val($("#checkOutDate").val());
        $("#modal_noRooms").val($("#noRooms").val());
        
        var searchText = hotelName;
       $.get("http://localhost:9872/searchHotel/"+searchText, {
            }, function(responseText) {
                $.each(responseText, function(key1, value1) {
                $.each((value1.hotelRooms), function(key2, value2) {
                   $("#select_roomTypes").append("<option>"+value2.type.name+"</option>");
         });
           $("#modal_hotelId").val(value1.hotelId);
       });    
     });
                                 
}); 
	
$("#myModal").on('click','.btn-searchHotelRooms',function() {
		$("#bookingHotelRoomModal").modal();
				
	});	
	
	
	
$(".btn-searchHotelRooms").click(function(){
         
         
          //var hotelName = $(this).parent().parent().parent().children("td").eq(0).text();
         $("#bookingHotelRoomModal").modal();
         $("#booking_hotelName").val($("#modal_hotelName").val());    
        $("#booking_noGuests").val($("#modal_noGuests").val());
        $("#booking_checkInDate").val($("#modal_checkInDate").val());
        $("#booking_checkOutDate").val($("#modal_checkOutDate").val());
        $("#booking_roomType").val($("#select_roomTypes").val());
        $("#booking_noRooms").val($("#modal_noRooms").val());
        
        
        
        
         var hotelName = $(this).parent().parent().parent().children("td").eq(0).text();
          var searchText = "Radission";
       $.get("http://localhost:9872/searchHotel/"+searchText, {
            }, function(responseText) {
                $.each(responseText, function(key1, value1) {
                $("#booking_discount").append(value1.discount);
                $.each(value1.hotelRooms, function(key2, value2){
                alert(value2.price);
                if ($("#booking_roomType").val()==value2.type.name){
                $("#booking_price").append(value2.price*($("#booking_noRooms").val())-value1.discount);
                $("#booking_hotelRoomId").val(value2.hotelRoomId);
                }
                });
                });
                
         
         
        });
        
        });
        
$(".btn-confirm-booking").click(function(){
         alert("opening");
         $("#guestModal").modal();
         
                  
     });
        
        
$(".btn-saveGuestDetails").click(function(){
         
        var firstName = $("#modal_firstName").val();
        var lastName = $("#modal_lastName").val();
		var guestAge = parseInt($("#modal_guestAge").val());
		var gender = $("#modal_gender").val();
		var guest = { "firstName": firstName, "lastName": lastName, "age": guestAge, "gender": gender  }
		
		
		var hotelId =parseInt($("#modal_hotelId").val());
		var hotelRoomId = parseInt($("#booking_hotelRoomId").val());
		var noRooms = parseInt($("#booking_noRooms").val());
		var checkInDate = $("#booking_checkInDate").val();
		var checkOutDate = $("#booking_checkOutDate").val();
		var bookedOnDate = new Date();
		var status = "upcoming";
		var price = parseInt($("#booking_price").text());
		var discount = parseInt($("#booking_discount").text());
		var customerMobile =  $("#booking_customerMobile").val() ;
		var roomType = $("#booking_roomType").val()  ;
		
		
		var email = "anantsession34@gmail.com";
		
		var booking = {"hotelId": hotelId, "hotelRoomId": hotelRoomId, "noRooms": noRooms  , "checkInDate": checkInDate,  "checkOutDate": checkOutDate, "bookedOnDate": bookedOnDate, 
		             "status":status, "price":price, "discount":discount, "customerMobile":customerMobile, "roomType": roomType, "guests": [guest] ,"email":email}
		           
		 $.ajax({
			type: "POST",
			contentType: "application/json",
			url: "http://localhost:9872/saveBooking",
			data: JSON.stringify(booking),
			dataType: 'json',
			success: function(result) {
				alert(result);
			},
			error: function(e) {

			}
		});     
		
		var recipient = "anantsingh1311@gmail.com";
		var msgBody = "You have booked successfully";
		var subject = "Booking confirmation";
		var attachment = "NA";
		
		var emaildetails = {"recipient":recipient, "msgBody":msgBody, "subject":subject, "attachment":attachment};
		
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "http://localhost:9872/sendEmail",
			data: JSON.stringify(emaildetails),
			dataType: 'String',
			success: function(result) {
				alert(result);
			},
			error: function(e) {

			}
		});              
                
                
     });
        

	


//Tab-1	
$("#tabs-1").click(function(){
        var searchText = "12345";

	$.ajax({
			type: "GET",
			url: "http://localhost:9872/findBookings/"+searchText,
			success: function(result) {
				alert(result);
				$.each(result, function(key1, value1){
				
				var cancel = "<td><a href='#'class='cancel'>Cancel</a></td>";
				 $("#tblBooking").append("<tr><td>"+value1.bookingId+"</td><td>"+value1.checkInDate+"</td><td>"+value1.checkOutDate+"</td><td>"+value1.status+"</td>"+cancel+"</tr>");
				 });
			},
			error: function(e) {

			}
		});   
		     
                       
                });
                
//Tab-2      
$("#tabs-2").click(function(){
        var searchText = "12345";
//        $('#tblBooking tr').not(':first').remove();
        $.ajax({
			type: "GET",
			//contentType: "application/json",
			url: "http://localhost:9872/findCompletedBookings/"+searchText,
			//data: JSON.stringify(booking),
			//dataType: 'json',
			success: function(result) {
				alert(result);
				$.each(result, function(key1, value1){
				var review = "<td><a href='#rev'class='review' >Review</a></td>";
				 $("#tblCompleted").append("<tr><td>"+value1.bookingId+"</td><td>"+value1.checkInDate+"</td><td>"+value1.checkOutDate+"</td><td>"+value1.status+"</td>"+review+"</tr>");
				 });
			},
			error: function(e) {

			}
		});   
                
                });
                
//Tab-3                
$("#tabs-3").click(function(){
        var searchText = "12345";
        $.ajax({
			type: "GET",
			url: "http://localhost:9872/findCancelledBookings/"+searchText,
			success: function(result) {
				alert(result);
				$.each(result, function(key1, value1){
				 $("#tblCancelled").append("<tr><td>"+value1.bookingId+"</td><td>"+value1.checkInDate+"</td><td>"+value1.checkOutDate+"</td><td>"+value1.status+"</td></tr>");
				 });
			},
			error: function(e) {

			}
		});   
                
                });
                
                
///Save Button
$("#save").click(function() {
		
		var userName = $("#userName").val();
		var userPassword = $("#userPassword").val();
		var email = $("#userEmail").val();
		var user = { "userName": userName, "userPassword": userPassword, "email": email }
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "http://localhost:9872/saveSignup",
			data: JSON.stringify(user),
			dataType: 'json',
			success: function(result) {
				alert(result);
			},
			error: function(e) {

			}
		});
	});
	
$("#tblCompleted").on('click',".review",function() {
                 bookingId = $(this).parent().parent().children("td").eq("0").text();
                 alert(bookingId);
                 
                 $("#questionModal").modal();
                 
                                                   
                 return false;
                 });
                 
                 
                 $(".btn-saveRating").click(function(){
                 alert(bookingId);
                 
                 var rating_stay = parseInt(($("#modal_stay").val()));
                 alert(rating_stay);
                 var rating_food = parseInt(($("#modal_food").val()));
                 var rating_cleanliness = parseInt(($("#modal_cleanliness").val()));
                 
                 var sum = (rating_stay) + (rating_food) + (rating_cleanliness);
                 
                 alert(sum);
                 var rating = parseInt(sum)/3;
                 
                 alert(rating);
                 var review = $("#modal_review").val();
                 var email = "anantsession34@gmail.com";
                 var hotelId = 1;
                 alert(review);
                 
                 var rating_review = {"bookingId":bookingId,"hotelId":hotelId,"email":email,"ratings":rating,"reviews":review};
                 
                 $.ajax({
			type: "POST",
			contentType: "application/json",
			url: "http://localhost:9872/saveRating",
			data: JSON.stringify(rating_review),
			dataType: 'json',
			success: function(result) {
				alert(result);
			},
			error: function(e) {

			}
		});      
                 
                 });
	
	
	
	
$("#tblBooking").on('click',".cancel",function() {
               bookingId = $(this).parent().parent().children("td").eq("0").text();
                alert(bookingId);
                
                $.ajax({
			type: "GET",
			url: "http://localhost:9872/cancelBooking/"+bookingId,
			success: function(result) {
				alert(result);
			},
			error: function(e) {

			}
		});          
                
                return false;
                });
                
$(".btn-saveRating").click(function(){
                 
                 //alert("hi");
                 alert(bookingId);
                 
                 var rating_stay = parseInt(($("#modal_stay").val()));
                 alert(rating_stay);
                 var rating_food = parseInt(($("#modal_food").val()));
                 var rating_cleanliness = parseInt(($("#modal_cleanliness").val()));
                 
                 var sum = (rating_stay) + (rating_food) + (rating_cleanliness);
                 
                 alert(sum);
                 var rating = parseInt(sum)/3;
                 
                 alert(rating);
                 var review = $("#modal_review").val();
                 var email = "anantsession34@gmail.com";
                 var hotelId = 1;
                 alert(review);
                 
                 var rating_review = {"bookingId":bookingId,"hotelId":hotelId,"email":email,"ratings":rating,"reviews":review};
                 
                 $.ajax({
			type: "POST",
			contentType: "application/json",
			url: "http://localhost:9872/saveRating",
			data: JSON.stringify(rating_review),
			dataType: 'json',
			success: function(result) {
				alert(result);
			},
			error: function(e) {

			}
		});      
                 
                 });
	
	
	






















































});	