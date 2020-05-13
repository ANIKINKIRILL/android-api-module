package tv.limehd.androidapimodule.Download;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tv.limehd.androidapimodule.LimeUri;
import tv.limehd.androidapimodule.Values.ApiValues;

public class PingApi {
    private ApiValues apiValues;

    public PingApi() {
        apiValues = new ApiValues();
    }

    public void pingApiRequest(final String scheme, final String api_root, final String endpoint_ping) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(LimeUri.getUriChannelList(scheme, api_root, endpoint_ping))
                        .addHeader(apiValues.getACCEPT_KEY(), apiValues.getACCEPT_VALUE()).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        if (callBackPing != null)
                            callBackPing.callBackError(e.getMessage());
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        }
                        if (callBackPing != null)
                            callBackPing.callBackSuccess(response.body().string());
                    }
                });
            }
        }).start();
        if (callBackPing != null)
            callBackPing.callBackRequest(LimeUri.getUriChannelList(scheme, api_root, endpoint_ping));
    }

    public interface CallBackPing {
        void callBackSuccess(String response);

        void callBackError(String message);

        void callBackRequest(String request);
    }

    private CallBackPing callBackPing;

    public void setCallBackPing(CallBackPing callBackPing) {
        this.callBackPing = callBackPing;
    }
}
