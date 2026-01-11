
package com.example.lib_domain_context

import com.example.lib_domain_context.MessagesHelper.Message

public interface IMessage
{
    public fun Show(message: Any): Any;
    public fun Show(message: Any, type: Message): Any;
}