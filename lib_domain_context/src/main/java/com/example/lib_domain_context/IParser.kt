
package com.example.lib_domain_context

public interface IParser<T>
{
    public fun CreateEntity(ItemArray: Any): T;
    public fun ToEntity(data: HashMap<String, Any>): T;
    public fun ToDictionary(entity: T): HashMap<String, Any>;
    public fun Validate(entity: T): Boolean;
}