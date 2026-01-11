
package com.example.lib_presentation_context.Core

import com.example.lib_language.Bundle

public class ErrorHelper
{
    companion object
    {
        public fun GetMessage(key: String): String
        {
            var response: String = Bundle.Get("RsMessages", key)!!;
            if (response == null || response == "")
                return Bundle.Get("RsMessages", "lbTryAgain")!!;
            return response;
        }
    }
}