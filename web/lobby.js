
$(function(){
    var value=1;
     $('#btn').click(function(){
        alert("him");
        $("#s").append("<option id='no"+value+"',value='"+value+"'>"+value+"</option>");
                      
        alert($('#no1').val());
        alert($('#no1').text());
        alert($('#no2').val());
        alert($('#no3').val());
        value=parseInt(value)+1;
        
    });
  
    
     $('#btnn').on("click", function(evt) {
         var checkText=$("#s").find("option:selected").text();
         var k="no"+checkText;
         
       $.getJSON("api/chatroom/"+$('#'+k).val());
           alert("api/chatroom/"+$('#'+k).val());
       //console.log(evt.target.id);
           alert(evt.target.id);
           var rv = evt.target.id;
           url ="chat.html?no="+k;
           
           location.href = url;
   });
  
});

