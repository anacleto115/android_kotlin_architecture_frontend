
package com.example.lib_domain_context

public class FactoryCaller
{
    companion object
    {
        public var IFactoryCaller: IFactory<ICaller>? = null;

        public fun Get(data: HashMap<String, Any>): ICaller?
        {
            if (IFactoryCaller == null)
                return null;

            return IFactoryCaller!!.Get(data);
        }
    }
}