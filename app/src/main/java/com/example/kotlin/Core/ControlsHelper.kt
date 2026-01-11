
package com.example.kotlin.Core

import android.R
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import androidx.fragment.app.Fragment
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.PopupWindow
import com.example.kotlin.MainActivity
import com.example.lib_utilities.Utilities.Callback
import com.example.lib_utilities.Utilities.Convert
import java.util.Calendar
import java.util.Date

public class ControlsHelper
{
    public interface ISetPopupWindow
    {
        public fun SetPopupWindow(v: PopupWindow?);
    }

    companion object
    {
        public fun DatePickerPopup(context: Context?, ctBorn: Button, callback: Callback<Date?>)
        {
            ctBorn.setOnClickListener { view: View? ->
                var setListener = OnDateSetListener { datePicker: DatePicker?, year: Int, month: Int, day: Int ->
                    try
                    {
                        var string_date = year.toString() + "-" + (month + 1) + "-" + day + " HH:mm:ss"
                        var date = Convert.StringToDate(string_date);
                        if (date == null)
                            return@OnDateSetListener;
                        callback.Execute(date);
                    }
                    catch (ex: Exception)
                    {

                    }
                };

                var calendar = Calendar.getInstance();
                var dialog = DatePickerDialog(context!!,
                        R.style.Theme_Holo_Dialog,
                        setListener,
                        calendar[Calendar.YEAR],
                        calendar[Calendar.MONTH],
                        calendar[Calendar.DAY_OF_MONTH]);
                dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        }

        public fun FilePickerPopup(context: Context?, ctFile: Button, callback: Callback<ByteArray>)
        {
            ctFile.setOnClickListener { view: View? ->
                var intent = Intent(Intent.ACTION_GET_CONTENT);
                intent.type = "*/*";
                intent = Intent.createChooser(intent, "");
                MainActivity.callback = callback;
                MainActivity.activity!!.startActivityForResult(intent, 1);
            }
        }

        public fun FragmentPopup(context: Context, form: Fragment, ctButton: Button)
        {
            ctButton.setOnClickListener { control: View? ->
                var inflater =
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater;
                var view = form.onCreateView(inflater, null, null);
                var popupWindow = PopupWindow(view, 800, 800, true);
                popupWindow.isFocusable = false;
                popupWindow.isTouchable = true;
                popupWindow.isOutsideTouchable = true;
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                (form as ISetPopupWindow).SetPopupWindow(popupWindow);
            }
        }
    }
}