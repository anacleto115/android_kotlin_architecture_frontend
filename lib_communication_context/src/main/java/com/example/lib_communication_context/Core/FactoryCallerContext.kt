
package com.example.lib_communication_context.Core

import com.example.lib_domain_context.ICaller
import com.example.lib_domain_context.IFactory

public class FactoryCallerContext : IFactory<ICaller>
{
    public override fun Get(data: HashMap<String, Any>): ICaller
    {
        return CallerServices();
    }
}
