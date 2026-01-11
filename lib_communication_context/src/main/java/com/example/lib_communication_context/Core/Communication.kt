
package com.example.lib_communication_context.Core

import com.example.lib_domain_context.FactoryCaller
import com.example.lib_domain_context.ICaller
import com.example.lib_domain_context.ICommunication

public open class Communication : ICommunication
{
    companion object
    {
        public var Protocol = "http://";
        public var Base = "192.168.0.33";
        public var CreateBy = "Communication";
    }
    public var client: ICaller? = null;
    public var Name = "";
    public var Service = "";
    public var End = "";

    public open fun Load(data: HashMap<String, Any>): HashMap<String, Any>
    {
        client = if (client == null) FactoryCaller.Get(data) else client;
        return data;
    }

    protected fun BuildUrl(data: HashMap<String, Any>, Method: String): HashMap<String, Any>
    {
        data["Url"] = Protocol + Base + "/" + Service + "/" + Name + "/" + Method + End;
        data["UrlToken"] = Protocol + Base + "/" + Service + "/Token/Authenticate" + End;
        if (!data.containsKey("create_by"))
            data["create_by"] = CreateBy;
        return data;
    }

    public override fun Select(data: HashMap<String, Any>): HashMap<String, Any>
    {
        var data = data;
        data = Load(data);
        data = BuildUrl(data, "Select");
        return client!!.Execute(data);
    }

    public override fun Insert(data: HashMap<String, Any>): HashMap<String, Any>
    {
        var data = data;
        data = Load(data);
        data = BuildUrl(data, "Insert");
        return client!!.Execute(data);
    }

    public override fun Update(data: HashMap<String, Any>): HashMap<String, Any>
    {
        var data = data;
        data = Load(data);
        data = BuildUrl(data, "Update");
        return client!!.Execute(data);
    }

    public override fun Delete(data: HashMap<String, Any>): HashMap<String, Any>
    {
        var data = data;
        data = Load(data);
        data = BuildUrl(data, "Delete");
        return client!!.Execute(data);
    }
}