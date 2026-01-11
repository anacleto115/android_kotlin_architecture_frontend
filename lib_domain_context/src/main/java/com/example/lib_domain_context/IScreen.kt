
package com.example.lib_domain_context

import com.example.lib_domain_context.Enumerables.Loading

public interface IScreen
{
    public fun Loading(action: Loading);
    public fun MoveFocus();
    public fun Change(data: HashMap<String, Any>);
}