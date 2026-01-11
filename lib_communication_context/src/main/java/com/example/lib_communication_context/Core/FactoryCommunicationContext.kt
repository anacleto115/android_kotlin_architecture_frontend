
package com.example.lib_communication_context.Core

import com.example.lib_communication_context.Implementations.PersonTypesCommunication
import com.example.lib_domain_context.Enumerables
import com.example.lib_domain_context.ICommunication
import com.example.lib_domain_context.IFactory

public class FactoryCommunicationContext : IFactory<ICommunication>
{
    public override fun Get(data: HashMap<String, Any>): ICommunication?
    {
        var type = data["Type"] as Enumerables.Types?;
        when (type)
        {
            Enumerables.Types.PersonTypes -> return PersonTypesCommunication(data);
            else -> return null;
        }
    }
}