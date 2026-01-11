
package com.example.lib_presentation_context.Core

import com.example.lib_domain_context.IConfiguration
import com.example.lib_domain_context.IParser

public open class Helper<T>
{
    protected var IParser: IParser<T>? = null;
    protected var IConfiguration: IConfiguration? = null;

    public open fun Load(data: HashMap<String, Any>): HashMap<String, Any>
    {
        data["Client"] = "Default";
        return data;
    }
}