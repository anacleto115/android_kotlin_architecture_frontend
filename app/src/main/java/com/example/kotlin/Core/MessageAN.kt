
package com.example.kotlin.Core

import com.example.lib_domain_context.IMessage
import com.example.lib_domain_context.MessagesHelper

public class MessageAN : IMessage
{
    public override fun Show(message: Any): Any { return true; }

    public override fun Show(message: Any, type: MessagesHelper.Message): Any { return true; }
}