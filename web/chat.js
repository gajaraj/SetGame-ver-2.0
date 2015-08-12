var source;
$(function() {
    
    alert("Here is a new page"+location.search.substring(6));
    parameters=location.search.substring(6);
    alert("substring"+parameters);
    document.getElementById("no").textContent = parameters;
    alert("Important step"+document.getElementById("no").textContent+"k");
    
    source = new EventSource("api/chatroom/"+$('#no').text());
    $(source).on($("#no").text(),function(){
          var chat = JSON.parse(event.data);
          var $messages = $("#messages");    
          $messages.text(chat.name + ": " + chat.msg + "\n" 
                 + $messages.text());
    });
    
    
//    source.onmessage = function(event) {
//        
//        var chat = JSON.parse(event.data);
//        var $messages = $("#messages");
//        
//        $messages.text(chat.name + ": " + chat.msg + "\n" 
//                + $messages.text());
//    };
    
   $("#sendBtn").on("click", function() {
       $.get("newMessage", { 
           name: $("#name").val(),
           msg: $("#msg").val(),
           no:$("#no").text()
       }).done(function() {
          $("#msg").val(""); 
       });
   }) 
});


