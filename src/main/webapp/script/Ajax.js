var MyApp = MyApp||{};
MyApp.asynrequest=(function(){
    var _ajaxRequest =function(method,url,fn,data){
        var _xhrObject = _XhrProver();
        url =encodeURI(url);
        console.log(url)
        _xhrObject.open(method,url,false);
        _xhrObject.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        _xhrObject.send(data);
        _xhrObject.onreadystatechange=_handlerReadyStateChagne(_xhrObject,fn);
    };
    
    function _handlerReadyStateChagne(xhrObj,fn){
        if(xhrObj.readyState==4){
            if(xhrObj.status==200){
                fn(xhrObj.responseText);
            }else{
            	alert('error')
            }
        }
    };
    
    function _XhrProver(){
        var XmlHttp;
        if (window.ActiveXObject)
        {
            var arr=["MSXML2.XMLHttp.6.0","MSXML2.XMLHttp.5.0",
                      "MSXML2.XMLHttp.4.0","MSXML2.XMLHttp.3.0","MSXML2.XMLHttp","Microsoft.XMLHttp"];
            for(var i=0;i<arr.length;i++)
            {
                try
                {
                    XmlHttp=new ActiveXObject(arr[i]);
                    return XmlHttp;
                }
                catch(error)
                {
                	log.error(error)
                }
            }
        }
        else
        {
            try
            {
                XmlHttp=new XMLHttpRequest();
                return XmlHttp;
            }
            catch(otherError)
            {
            	log.error(error)
            }
        } 
    };
    
    return _ajaxRequest;
})();
