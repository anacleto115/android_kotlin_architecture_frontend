
package com.example.lib_presentation_context.Core

import com.example.lib_communication_context.Core.FactoryCommunicationContext
import com.example.lib_communication_core.Core.FactoryCommunication
import com.example.lib_domain_context.ICommunication
import com.example.lib_domain_context.IHelper
import com.example.lib_domain_context.MessagesHelper
import com.example.lib_utilities.Utilities.Delegate

public open class HelperCommunication<T> : Helper<T>(), IHelper
{
    protected var ICommunication: ICommunication? = null;

    public override fun Load(data: HashMap<String, Any>): HashMap<String, Any>
    {
        var data = data;
        data = super.Load(data);
        FactoryCommunication.IFactoryCommunication =
            if (FactoryCommunication.IFactoryCommunication == null) FactoryCommunicationContext() else FactoryCommunication.IFactoryCommunication;
        if (data.containsKey("ICommunication")) ICommunication =
            data["ICommunication"] as ICommunication?;
        return data;
    }

    public override fun Select(data: HashMap<String, Any>): HashMap<String, Any>
    {
        return FuncList(FuncValidate(data), object : Delegate {
            public override fun Execute(data: HashMap<String, Any>): HashMap<String, Any>
            {
                return ICommunication!!.Select(data);
            }
        });
    }

    public override fun Insert(data: HashMap<String, Any>): HashMap<String, Any>
    {
        return FuncExec(FuncValidate(data), object : Delegate {
            public override fun Execute(data: HashMap<String, Any>): HashMap<String, Any>
            {
                return ICommunication!!.Insert(data);
            }
        });
    }

    public override fun Update(data: HashMap<String, Any>): HashMap<String, Any>
    {
        return FuncExec(FuncValidate(data), object : Delegate {
            public override fun Execute(data: HashMap<String, Any>): HashMap<String, Any>
            {
                return ICommunication!!.Update(data);
            }
        });
    }

    public override fun Delete(data: HashMap<String, Any>): HashMap<String, Any>
    {
        return FuncExec(FuncValidate(data), object : Delegate {
            public override fun Execute(data: HashMap<String, Any>): HashMap<String, Any>
            {
                return ICommunication!!.Delete(data);
            }
        });
    }

    protected fun FuncValidate(_data: HashMap<String, Any>): HashMap<String, Any>
    {
        var data = _data;
        var response = HashMap<String, Any>();
        try
        {
            data = Load(data);
            return data;
        }
        catch (ex: Exception)
        {
            response = HashMap();
            response["Error"] = ex.toString();
            return response;
        }
    }

    protected fun FuncExec(data: HashMap<String, Any>, method: Delegate): HashMap<String, Any>
    {
        var response = HashMap<String, Any>()
        try
        {
            if (IParser != null &&
                data.containsKey("Entity") &&
                !IParser!!.Validate(data["Entity"] as T))
            {
                response["Error"] = "lbMissingInfo";
                MessagesHelper.Show(ErrorHelper.GetMessage(response["Error"].toString()));
                return response;
            }
            if (IParser != null &&
                data.containsKey("Entity"))
                data["Entity"] = IParser!!.ToDictionary(data["Entity"] as T);
            response = method.Execute(data);
            if (response.containsKey("Error"))
            {
                MessagesHelper.Show(ErrorHelper.GetMessage(response["Error"].toString()));
                return response;
            }
            if (IParser != null &&
                response.containsKey("Entity"))
                response["Entity"] = IParser!!.ToEntity(response["Entity"] as HashMap<String, Any>) as Any;
            return response;
        }
        catch (ex: Exception)
        {
            response = HashMap();
            response["Error"] = ex.toString();
            return response;
        }
    }

    protected fun FuncList(data: HashMap<String, Any>, method: Delegate): HashMap<String, Any>
    {
        var response = HashMap<String, Any>();
        try
        {
            response = method.Execute(data!!);
            if (response.containsKey("Error"))
            {
                MessagesHelper.Show(ErrorHelper.GetMessage(response["Error"].toString()));
                return response;
            }
            if (IParser != null &&
                response.containsKey("Entities"))
            {
                var list: MutableList<T> = ArrayList();
                var temp = response["Entities"] as HashMap<String, Any>?;
                for ((key, value) in temp!!)
                {
                    var entData = value as HashMap<String, Any>;
                    var ent: T = IParser!!.ToEntity(entData);
                    list.add(ent)
                }
                response["Entities"] = list;
            }
            return response;
        }
        catch (ex: Exception)
        {
            response = HashMap();
            response["Error"] = ex.toString();
            return response;
        }
    }
}