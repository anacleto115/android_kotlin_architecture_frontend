
package com.example.kotlin

import android.Manifest
import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin.Core.MessageAN
import com.example.kotlin.Screens.person_typesForm
import com.example.lib_communication_context.Core.FactoryCallerContext
import com.example.lib_domain_context.Enumerables
import com.example.lib_domain_context.FactoryCaller
import com.example.lib_domain_context.MessagesHelper
import com.example.lib_utilities.Utilities.CacheHelper
import com.example.lib_utilities.Utilities.Callback
import java.io.ByteArrayOutputStream

// Remember check all modules version
public class MainActivity : AppCompatActivity()
{
    private var isPhone = true;

    companion object
    {
        public var activity: Activity? = null;
        public var callback: Callback<ByteArray>? = null;
        private var fragmentManager: FragmentManager? = null;
        private var oldFragment: Fragment? = null;
        public var context: Context? = null;

        public fun ShowFragment(fragment: Fragment?)
        {
            if (oldFragment != null)
            {
                fragmentManager!!.beginTransaction()
                    .remove(oldFragment!!)
                    .commit();
            }
            fragmentManager!!.beginTransaction()
                .add(R.id.container, fragment!!)
                .commit();
            oldFragment = fragment;
        }
    }

    public override fun onCreate(savedInstanceState: Bundle?)
    {
        activity = this;

        MainActivity.fragmentManager = supportFragmentManager;
        context = this.applicationContext;
        MessagesHelper.Set(MessageAN());
        CacheHelper.Add("Architecture", Enumerables.Architecture.Services);
        FactoryCaller.IFactoryCaller = FactoryCallerContext();
        com.example.lib_language.Bundle.Load("Es");

        var manager = context!!.getSystemService(TELEPHONY_SERVICE) as TelephonyManager;
        if (manager.phoneType == TelephonyManager.PHONE_TYPE_NONE)
            isPhone = false;

        Permissions();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ShowFragment(person_typesForm());
    }

    private fun Permissions()
    {
        var context = this.applicationContext;
        /*if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) ==  PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==  PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.INTERNET) ==  PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.WAKE_LOCK) ==  PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_NETWORK_STATE) ==  PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_WIFI_STATE) ==  PackageManager.PERMISSION_GRANTED)
            return;*/
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, resultData);
        try
        {
            if (resultCode != RESULT_OK)
                return;
            if (resultData == null)
                return;
            var uri = resultData.data;
            var byteArrayOutputStream = ByteArrayOutputStream();
            var inputStream = contentResolver.openInputStream(uri!!);
            var buf = ByteArray(1024);
            var n: Int;
            while (inputStream!!.read(buf).also { n = it } != -1)
                byteArrayOutputStream.write(buf, 0, n);
            var bytes = byteArrayOutputStream.toByteArray();
            if (callback == null)
                return;
            callback!!.Execute(bytes);
            callback = null;
        }
        catch (ex: Exception)
        {
            var error = ex.toString();
            callback = null;
        }
    }
}