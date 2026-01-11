
package com.example.lib_domain_context

public class MessagesHelper
{
    public enum class Message
    {
        MESSAGE, QUESTION
    }

    companion object
    {
        private var IMessage: IMessage? = null;

        public fun Set(iMessage: IMessage?) { IMessage = iMessage; }

        public fun Show(message: Any): Any
        {
            if (IMessage == null)
                return false;
            else
                return IMessage!!.Show(message, Message.MESSAGE);
        }

        public fun Show(message: Any, type: Message): Any
        {
            if (IMessage == null)
                return false
            else
                return IMessage!!.Show(message, type);
        }
    }
}
