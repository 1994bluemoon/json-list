package com.example.dminh.json_list;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    ArrayList<LstTest> lstTests;
    ArrayList<LstTest> tests;

    @BindView(R.id.tv_sample)
    TextView tvSample;


    @BindView(R.id.lv_test)
    ListView lvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        lstTests = new ArrayList<LstTest>();

        lstTests.add(new LstTest("Câu 1: Hình vẽ sau là đồ thị của hàm số \\( y = \\frac{ax + b}{cx + d} \\).Mệnh đề nào sau đây đúng?","","A. \\(bd \\lt \\), \\(ab \\gt 0\\).", "B. \\(bd \\gt 0 \\), \\(ab \\gt 0\\).", "C. \\(ad \\gt 0\\), \\(ab \\lt 0\\).", "D. \\(ab \\lt 0\\), \\(ad \\lt 0\\).", "A"));
        lstTests.add(new LstTest("Câu 2: Hình vẽ sau là đồ thị của hàm số \\( y = \\frac{ax + b}{cx + d} \\).Mệnh đề nào sau đây đúng?","","A. \\(bd \\lt \\), \\(ab \\gt 0\\).", "B. \\(bd \\gt 0 \\), \\(ab \\gt 0\\).", "C. \\(ad \\gt 0\\), \\(ab \\lt 0\\).", "D. \\(ab \\lt 0\\), \\(ad \\lt 0\\).", "B"));
        lstTests.add(new LstTest("Câu 3: Hình vẽ sau là đồ thị của hàm số \\( y = \\frac{ax + b}{cx + d} \\).Mệnh đề nào sau đây đúng?","","A. \\(bd \\lt \\), \\(ab \\gt 0\\).", "B. \\(bd \\gt 0 \\), \\(ab \\gt 0\\).", "C. \\(ad \\gt 0\\), \\(ab \\lt 0\\).", "D. \\(ab \\lt 0\\), \\(ad \\lt 0\\).", "C"));
        lstTests.add(new LstTest("Câu 4: Hình vẽ sau là đồ thị của hàm số \\( y = \\frac{ax + b}{cx + d} \\).Mệnh đề nào sau đây đúng?","","A. \\(bd \\lt \\), \\(ab \\gt 0\\).", "B. \\(bd \\gt 0 \\), \\(ab \\gt 0\\).", "C. \\(ad \\gt 0\\), \\(ab \\lt 0\\).", "D. \\(ab \\lt 0\\), \\(ad \\lt 0\\).", "D"));


        JSONArray jsonArray = new JSONArray();
        for (LstTest obj:lstTests) {
            jsonArray.put(obj.getJSONObject());
        }
        tvSample.setText(jsonArray.toString());
        /*DownloadFilesTask downloadFilesTask = new DownloadFilesTask();
        downloadFilesTask.execute();*/
        Toast.makeText(this, "helo", Toast.LENGTH_SHORT).show();
    }

    private class DownloadFilesTask extends AsyncTask<URL, Void, String> {
        protected String doInBackground(URL... urls) {
            return downloadRemoteTextFileContent();
        }
        protected void onPostExecute(String result) {
            if(!TextUtils.isEmpty(result)){
                String a = result;
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(a);
                    JSONObject object;
                    LstTest test;
                    tests = new ArrayList<LstTest>();

                    for (int i = 0; i< jsonArray.length(); i++){
                        object = jsonArray.getJSONObject(i);
                        //tests.add(new LstTest(object.getString("st")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ListViewAdapter adapter = new ListViewAdapter(MainActivity.this,tests);
                lvTest.setAdapter(adapter);
            }
        }
    }
    private String downloadRemoteTextFileContent(){
        URL mUrl = null;
        String content = "";
        try {
            mUrl = new URL("http://dvqchoangdang.000webhostapp.com/txt.txt");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            assert mUrl != null;
            URLConnection connection = mUrl.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            while((line = br.readLine()) != null){
                content += line;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}

class LstTest {
    String De;
    String Hinh;
    String DapAnA;
    String DapAnB;
    String DapAnC;
    String DapAnD;
    String DapAnDung;

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String hinh) {
        Hinh = hinh;
    }

    public String getDe() {
        return De;
    }

    public void setDe(String de) {
        De = de;
    }

    public String getDapAnA() {
        return DapAnA;
    }

    public void setDapAnA(String dapAnA) {
        DapAnA = dapAnA;
    }

    public String getDapAnB() {
        return DapAnB;
    }

    public void setDapAnB(String dapAnB) {
        DapAnB = dapAnB;
    }

    public String getDapAnC() {
        return DapAnC;
    }

    public void setDapAnC(String dapAnC) {
        DapAnC = dapAnC;
    }

    public String getDapAnD() {
        return DapAnD;
    }

    public void setDapAnD(String dapAnD) {
        DapAnD = dapAnD;
    }

    public String getDapAnDung() {
        return DapAnDung;
    }

    public void setDapAnDung(String dapAnDung) {
        DapAnDung = dapAnDung;
    }

    public LstTest(String de,String hinh, String dapAnA, String dapAnB, String dapAnC, String dapAnD, String dapAnDung) {
        De = de;
        this.Hinh = hinh;
        DapAnA = dapAnA;
        DapAnB = dapAnB;
        DapAnC = dapAnC;
        DapAnD = dapAnD;
        DapAnDung = dapAnDung;
    }

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("De", this.De);
            obj.put("Hinh", this.Hinh);
            obj.put("DapAnA", this.DapAnA);
            obj.put("DapAnB", this.DapAnB);
            obj.put("DapAnC", this.DapAnC);
            obj.put("DapAnD", this.DapAnD);
            obj.put("DapAnDung", this.DapAnDung);
        } catch (JSONException e) {
        }
        return obj;
    }
}

class ListViewAdapter extends BaseAdapter{

    Context ContextObj;
    List<LstTest> TempList;

    public ListViewAdapter(Context contextObj, List<LstTest> tempList) {
        ContextObj = contextObj;
        TempList = tempList;
    }

    @Override
    public int getCount() {
        return TempList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewItem viewItem = null;

        if(convertView == null)
        {
            viewItem = new ViewItem();

            LayoutInflater layoutInfiater = (LayoutInflater)this.ContextObj.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInfiater.inflate(R.layout.listview_item, null);

            viewItem.textView = (TextView)convertView.findViewById(R.id.tv_item);

            convertView.setTag(viewItem);
        }
        else
        {
            viewItem = (ViewItem) convertView.getTag();
        }

        //viewItem.textView.setText(TempList.get(position).getSt());

        return convertView;
    }
}
class ViewItem{
    TextView textView;
}



