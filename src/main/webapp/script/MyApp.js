var MyApp = MyApp||{};
(function(){
    MyApp.apply  =function(obj,config,defaults){
        if(!obj || typeof config !="object"){
            throw new Erro("Ext.apply error,  obj or config .....");
        }
        if(defaults){
            Ext.apply(obj,defaults);
        }

        var k;
        for(k in config){
            obj[k] = config[k];
        }

        return obj;
    }
})();