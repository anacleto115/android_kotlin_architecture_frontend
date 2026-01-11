
package com.example.lib_communication_core.Core

import com.example.lib_domain_context.ICommunication
import com.example.lib_domain_context.IFactory

public class FactoryCommunication
{
    companion object
    {
        var IFactoryCommunication: IFactory<ICommunication>? = null;

        public fun Get(data: HashMap<String, Any>): ICommunication?
        {
            if (IFactoryCommunication == null)
                return null;

            return IFactoryCommunication!!.Get(data);
        }
    }
}