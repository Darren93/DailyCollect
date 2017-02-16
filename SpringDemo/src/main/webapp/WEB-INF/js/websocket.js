  var stompClient = null;    
    function setConnected(connected) {        
        document.getElementById('connect').disabled = connected;        
        document.getElementById('disconnect').disabled = !connected;        
        document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';        
        document.getElementById('response').innerHTML = '';    
    }    
    // 开启socket连接
    function connect() {        
        var socket = new SockJS('/SpringDemo/websocket');        
        stompClient = Stomp.over(socket);        
        stompClient.connect({}, function (frame) {            
             setConnected(true);            
        });    
    }    
    // 断开socket连接
    function disconnect() {        
        if (stompClient != null) {            
            stompClient.disconnect();        
        }        
        setConnected(false);        
        console.log("Disconnected");    
    }    
    // 向‘/send/change-notice’服务端发送消息
    function sendName() {        
        var value = document.getElementById('name').value;            
        stompClient.send("/send/change-notice", {}, value);    
    }    
    connect();