package com.think_coding.json_arraylist_convert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    Json_sharedpreference json_sharedpreference;

    private json_string_class[] json_saver = new json_string_class[10];

    private String test_btn_name = "a2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        json_sharedpreference = new Json_sharedpreference(this,test_btn_name);

        String a = "[{\"btn\" : \"first btn\", \"x\":100, \"y\":200, \"code\": \"code1\" }]";





        /********* 1. 버튼 만들어서 string builder로 json형태로 만들어서 저장하기 ***********/
        json_sharedpreference.json_saver[0].btn_name =  new String("first");
        json_sharedpreference.json_saver[0].x_location = 100;
        json_sharedpreference.json_saver[0].y_location  = 200;
        json_sharedpreference.json_saver[0].coding_contents =  new String("first");


        json_sharedpreference.json_saver[1].btn_name =  new String("two");
        json_sharedpreference.json_saver[1].x_location = 300;
        json_sharedpreference.json_saver[1].y_location  = 400;
        json_sharedpreference.json_saver[1].coding_contents =  new String("first");


        json_sharedpreference.for_saving_stringbuilder.append("[");

        json_sharedpreference.for_saving_stringbuilder.append(
                json_sharedpreference.json_adder(
                        json_sharedpreference.json_saver[0].btn_name,
                        json_sharedpreference.json_saver[0].x_location,
                        json_sharedpreference.json_saver[0].y_location,
                        json_sharedpreference.json_saver[0].coding_contents));

        json_sharedpreference.for_saving_stringbuilder.append(",");

        json_sharedpreference.for_saving_stringbuilder.append(
                json_sharedpreference.json_adder(
                        json_sharedpreference.json_saver[1].btn_name,
                        json_sharedpreference.json_saver[1].x_location,
                        json_sharedpreference.json_saver[1].y_location,
                        json_sharedpreference.json_saver[1].coding_contents));

        json_sharedpreference.for_saving_stringbuilder.append("]");

        json_sharedpreference.save_json_to_sharedpreference(json_sharedpreference.for_saving_stringbuilder); //저장하기
//        json_sharedpreference.test_getstring();
        json_sharedpreference.json_saver = json_sharedpreference.convert_json_to_data_class(); //불러오기

        Toast.makeText(this,
                json_sharedpreference.json_saver[0].btn_name + ":"+
                        json_sharedpreference.json_saver[0].x_location +","+
                        json_sharedpreference.json_saver[1].btn_name + ":"+
                        json_sharedpreference.json_saver[1].x_location, Toast.LENGTH_LONG
        ).show();



    }

    //스트링 json 형태로 변환 시켜 주기
    public String json_adder(String btn_name, float x_location, float y_location, String coding_contents){
        String return_string = "";
        return_string += "{\"btn\":\"" + btn_name + "\",\"x\":" + x_location +",\"y\":" + y_location + ",\"code\":\""+ coding_contents + "\"}";

//        return_string += "{\"btn\" : \"first btn\", \"x\":100, \"y\":200, \"code\": \"code1\" }";
        return return_string;
    }

    //저장된 string을 클래스 데이터 형태로 가져오기
    public json_string_class[] convert_json_to_data_class(StringBuilder json_string){
        try{
            //리턴할 클래스 초기화
            json_string_class[] return_class = new json_string_class[10];
            for (int i = 0; i < 10; i++) {
                return_class[i] = new json_string_class();
            }


            //json에서 데이터 추출
            String result = new String();
            JSONArray ja = new JSONArray(json_string.toString());
            for (int i = 0; i < ja.length(); i++){
                JSONObject order = ja.getJSONObject(i);
//                result += "btn: " + order.getString("btn") + ", x_location: " + order.getString("x") +
//                        ", y_location: " + order.getInt("y") + ", code: " + order.getString("code") + "\n";
                return_class[i].btn_name = order.getString("btn");
                return_class[i].x_location = (float) order.getInt("x");
                return_class[i].y_location = (float) order.getInt("y");
                return_class[i].coding_contents = order.getString("code");
                Log.i(i + " return class:", return_class[i].btn_name);
            }
            Log.i("result:", result);

            return return_class;

        }
        catch (JSONException e){
            Log.e("result err", e+"");
            return null;
        }
    }


    public class json_string_class{
        public String btn_name;
        public float x_location;
        public float y_location;
        public String coding_contents;

//        public json_string_class(String from_btn_name, float from_x_location, float from_y_location, String from_coding_contents){
//            btn_name = from_btn_name;
//            x_location = from_x_location;
//            y_location = from_y_location;
//            coding_contents = from_coding_contents;
//        }
    }




}
