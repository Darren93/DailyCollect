var noticeSocket = function () {    
  var s = new SockJS('/SpringDemo/websocket');    
  var stompClient = Stomp.over(s);    
  stompClient.connect({}, function () {         
    console.log('notice socket connected!');
    stompClient.subscribe('/get/notice', function (data) {            
      document.getElementById("message").innerHTML=data.body;        
    });    
 });
};

noticeSocket();