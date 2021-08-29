package com.AN1D.an1d.Utils;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import com.AN1D.an1d.config.Constants;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class OrderIdUtil {

    public static String createOrderId(String last_order_id) {
        int order_suffix = 1;
        int prev_order_suffix = Integer.parseInt(last_order_id.substring(last_order_id.length() - 4));
        String date = DateUtils.getFormattedDateInYYYYMMDD();
        Timestamp max_time_stamp = Timestamp.valueOf(date+" 23:59:59.999");

        StringBuilder strbuild = new StringBuilder();
        strbuild.append(Constants.ORDER_REFRREL_PREFIX+"-Y");
        strbuild.append(date.substring(0, 5));
        String month_to_date [] = date.substring(6, date.length()).split("-");

        for(String val : month_to_date){
            if(val.length() == 1){
                strbuild.append("0"+val);
            }else{
                strbuild.append(val);
            }
        }
        strbuild.append("-");


        String last_order_month_to_date = last_order_id.substring(12, 16);
        String current_order_month_to_date = strbuild.toString().substring(12, 16);

        Timestamp current_timestamp = DateUtils.getCurretDateAsTimeStamp();

        if(current_timestamp.before(max_time_stamp) && last_order_month_to_date.equals(current_order_month_to_date)){
            order_suffix = prev_order_suffix+1;

            strbuild.append(decimalFormat(order_suffix));
        }else{
            strbuild.append(decimalFormat(order_suffix));
        }

        return strbuild.toString();
    }

    private static String decimalFormat(int val){
        DecimalFormat dformat = new DecimalFormat("0000");
        return dformat.format(val);
    }

}
