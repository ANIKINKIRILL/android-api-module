package tv.limehd.androidapiclient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import tv.limehd.androidapimodule.LimeApiClient;
import tv.limehd.androidapimodule.LimeRFC;
import tv.limehd.androidapimodule.Values.ApiValues;

public class DemoActivity extends Activity implements LimeApiClient.DownloadChannelListCallBack, LimeApiClient.DownloadBroadCastCallBack {

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        //инициализация апи клиента
        limeApiClient = new LimeApiClient(api_root);

        //инициализация апи значений
        apiValues = new ApiValues();

        //запрос списка телеканалов
        limeApiClient.downloadChannelList(apiValues.getSCHEME_HTTP(), apiValues.getURL_CHANNELS_GRECE_PATH());
        limeApiClient.setDownloadChannelListCallBack(this);

        //инициализация даты для запроса по формату RFC3339
        long current_time_stamp = System.currentTimeMillis();//текущее время
        long before_date = current_time_stamp - five_days_stamp;//левая граница - 5 дней
        long after_date = current_time_stamp + five_days_stamp;//правая граница + 5 дней
        //запрос списка телепередач для телеканала
        limeApiClient.downloadBroadcast(apiValues.getSCHEME_HTTP(), apiValues.getURL_BROADCAST_PATH()
                , example_channel_id, LimeRFC.timeStampToRFC(before_date), LimeRFC.timeStampToRFC(after_date), example_time_zone);
        limeApiClient.setDownloadBroadCastCallBack(this);
    }

    @Override
    public void downloadChannelListSuccess(String response) {
        Log.e(LIME_LOG, response);
    }

    @Override
    public void downloadChannelListError(String message) {
        Log.e(LIME_LOG, message);
    }

    @Override
    public void downloadBroadCastSuccess(String response) {
        Log.e(LIME_LOG, response);
    }

    @Override
    public void downloadBroadCastError(String message) {
       Log.e(LIME_LOG, message);
    }
}
