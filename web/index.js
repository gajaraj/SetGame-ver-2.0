var source=null;
$(function(){
    $('#startBtn').on("click",function(){
        alert("starting");
        source=new EventSource("api/counter?start="+$('#startNum').val());
        alert("rting");
        source.onmessage=eventHandler;
        
    });
    
    
    
});

function eventHandler(event){

    console.log("event="+JSON.stringify(event));
    $('#counter').text(event.data);
}