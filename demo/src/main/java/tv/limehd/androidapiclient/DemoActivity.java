package tv.limehd.androidapiclient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import tv.limehd.androidapiclient.Adapters.ApiAdapter;
import tv.limehd.androidapimodule.LimeApiClient;
import tv.limehd.androidapimodule.LimeRFC;
import tv.limehd.androidapimodule.Values.ApiValues;

public class DemoActivity extends Activity implements LimeApiClient.DownloadChannelListCallBack, LimeApiClient.DownloadBroadCastCallBack, LimeApiClient.DownloadPingCallBack {

    private String LIME_LOG = "limeapilog";
    //экземпляр апи клиента для запросов
    private LimeApiClient limeApiClient;
    //рут апи
    private String api_root = "api.iptv2021.com";
    //экземпляр апи значений
    private ApiValues apiValues;
    //для примера ид телеканала с телепрограммой
    private String example_channel_id = "10505";
    //для примера timestamp в пять дней для разницы при запросе ЕПГ на +- 5 дней.
    private long five_days_stamp = 432000000;
    //для примера тайм зона в формате UTC offset
    private String example_time_zone = "UTC+03:00";

    private RecyclerView recyclerView;
    private EditText answerTextView;
    private EditText curlTextView;
    private EditText urlTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        answerTextView = findViewById(R.id.textAnswer);
        curlTextView = findViewById(R.id.textCurl);
        urlTextView = findViewById(R.id.textUrl);

        //инициализация апи клиента
        limeApiClient = new LimeApiClient(api_root);

        //инициализация апи значений
        apiValues = new ApiValues();

        limeApiClient.setDownloadChannelListCallBack(this);
        limeApiClient.setDownloadBroadCastCallBack(this);
        limeApiClient.setDownloadPingCallBack(this);

        //инициализация даты для запроса по формату RFC3339
        long current_time_stamp = System.currentTimeMillis();//текущее время
        final long before_date = current_time_stamp - five_days_stamp;//левая граница - 5 дней
        final long after_date = current_time_stamp + five_days_stamp;//правая граница + 5 дней

        recyclerView = findViewById(R.id.recyclerView);


        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ApiAdapter apiAdapter = new ApiAdapter(getApplicationContext());
        apiAdapter.setApiAdapterInterface(new ApiAdapter.ApiAdapterInterface() {
            @Override
            public void onClickPing() {
                limeApiClient.downloadPing(apiValues.getSCHEME_HTTP(), apiValues.getURL_PING_PATH());
            }

            @Override
            public void onClickDownloadBroadcast() {
                //запрос списка телепередач для телеканала
                limeApiClient.downloadBroadcast(apiValues.getSCHEME_HTTP(), apiValues.getURL_BROADCAST_PATH()
                        , example_channel_id, LimeRFC.timeStampToRFC(before_date), LimeRFC.timeStampToRFC(after_date), example_time_zone);

            }

            @Override
            public void onClickDownloadChannelList() {
                //запрос списка телеканалов
                limeApiClient.downloadChannelList(apiValues.getSCHEME_HTTP(), apiValues.getURL_CHANNELS_GRECE_PATH());
            }
        });
        recyclerView.setAdapter(apiAdapter);
    }

    private void printAnswer(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (answerTextView != null)
                    answerTextView.setText(message);
            }
        });
    }

    private void printCurl(final String request) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (curlTextView != null) {
                    curlTextView.setText(request);
                }
            }
        });
    }

    private void printUrlRequest(final String request) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (curlTextView != null) {
                    urlTextView.setText(request);
                }
            }
        });
    }

    @Override
    public void downloadChannelListSuccess(String response) {
        Log.e(LIME_LOG, response);
        printAnswer(response);
    }

    @Override
    public void downloadBroadCastSuccess(String response) {
        Log.e(LIME_LOG, response);
        printAnswer(response);
    }

    @Override
    public void downloadPingSuccess(String response) {
        Log.e(LIME_LOG, response);
        printAnswer(response);
    }

    @Override
    public void downloadChannelListError(String message) {
        Log.e(LIME_LOG, message);
        printAnswer(message);
    }

    @Override
    public void downloadBroadCastError(String message) {
        Log.e(LIME_LOG, message);
        printAnswer(message);
    }

    @Override
    public void downloadPingError(String message) {
        Log.e(LIME_LOG, message);
        printAnswer(message);
    }

    @Override
    public void downloadBroadCastRequest(String request) {
        Log.e(LIME_LOG, request);
        printCurl("curl -X GET --header 'Accept: application/vnd.api+json' '" + request + "'");
        printUrlRequest(request);
    }

    @Override
    public void downloadChannelListRequest(String request) {
        Log.e(LIME_LOG, request);
        printCurl("curl -X GET --header 'Accept: application/vnd.api+json' '" + request + "'");
        printUrlRequest(request);
    }

    @Override
    public void downloadPingRequest(String request) {
        Log.e(LIME_LOG, request);
        printCurl("curl -X GET --header 'Accept: application/json' '" + request + "'");
        printUrlRequest(request);
    }
}
