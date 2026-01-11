
package com.example.lib_domain_context

public interface IFactory<T>
{
    public fun Get(data: HashMap<String, Any>): T?;
}