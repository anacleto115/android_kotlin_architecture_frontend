
package com.example.lib_communication_context.Implementations

import com.example.lib_communication_context.Core.Communication
import com.example.lib_communication_core.Interfaces.IPersonTypesCommunication

public class PersonTypesCommunication(data: HashMap<String, Any>) : Communication(), IPersonTypesCommunication
{
    public override fun Load(data: HashMap<String, Any>): HashMap<String, Any>
    {
        var data: HashMap<String, Any>? = data;
        data = super.Load(data!!);
        Service = "srw_persons";
        Name = "PersonTypes";
        return data;
    }
}