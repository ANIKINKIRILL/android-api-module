package tv.limehd.androidapimodule.Download;

import tv.limehd.androidapimodule.Values.ApiValues;

public class ClientDownloading {

    private ApiValues apiValues;

    public ClientDownloading() {
        apiValues = new ApiValues();
    }

    public void downloadChannelList(String scheme, String api_root, String endpoint_channels) {
        ChannelListDownloading channelListDownloading = new ChannelListDownloading();
        channelListDownloading.setCallBackDownloadChannelListInterface(new ChannelListDownloading.CallBackDownloadChannelListInterface() {
            @Override
            public void callBackDownloadedChannelListSuccess(String response) {
                if (callBackDownloadInterface != null)
                    callBackDownloadInterface.callBackDownloadedSuccess(response);
            }

            @Override
            public void callBackDownloadedChannelListError(String error_message) {
                if (callBackDownloadInterface != null)
                    callBackDownloadInterface.callBackDownloadedError(error_message);
            }

            @Override
            public void callBackRequestChannelList(String request) {
                if (callBackDownloadInterface != null)
                    callBackDownloadInterface.callBackDowloadedRequest(request);
            }
        });
        channelListDownloading.loadingRequestChannelList(scheme, api_root, endpoint_channels);
    }

    public void downloadBroadCast(String scheme, String api_root, String endpoint_broadcast
            , String channel_id, String before_date, String after_date, String time_zone) {
        BroadcastDownloading broadcastDownloading = new BroadcastDownloading();
        broadcastDownloading.setCallBackDownloadBroadCastInterface(new BroadcastDownloading.CallBackDownloadBroadCastInterface() {
            @Override
            public void callBackDownloadedBroadCastSucces(String response) {
                if (callBackDownloadInterface != null)
                    callBackDownloadInterface.callBackDownloadedSuccess(response);
            }

            @Override
            public void callBackDownloadedBroadCastError(String error_message) {
                if (callBackDownloadInterface != null)
                    callBackDownloadInterface.callBackDownloadedError(error_message);
            }

            @Override
            public void callBackRequestBroadCast(String request) {
                if (callBackDownloadInterface != null)
                    callBackDownloadInterface.callBackDowloadedRequest(request);
            }
        });
        broadcastDownloading.loadingRequestBroadCast(scheme, api_root, endpoint_broadcast, channel_id, before_date, after_date, time_zone);
    }
    public void dowloadPing(String scheme, String api_root, String endpoint_ping)
    {
        PingApi pingApi = new PingApi();
        pingApi.setCallBackPing(new PingApi.CallBackPing() {
            @Override
            public void callBackSuccess(String response) {
                if(callBackDownloadInterface != null)
                    callBackDownloadInterface.callBackDownloadedSuccess(response);
            }

            @Override
            public void callBackError(String message) {
                if(callBackDownloadInterface != null)
                    callBackDownloadInterface.callBackDownloadedError(message);
            }

            @Override
            public void callBackRequest(String request) {
                if(callBackDownloadInterface != null)
                    callBackDownloadInterface.callBackDowloadedRequest(request);
            }
        });
        pingApi.pingApiRequest(scheme, api_root, endpoint_ping);
    }
    public interface CallBackDownloadInterface {
        void callBackDownloadedSuccess(String response);

        void callBackDownloadedError(String error_message);

        void callBackDowloadedRequest(String request);
    }

    private CallBackDownloadInterface callBackDownloadInterface;

    public void setCallBackDownloadInterface(CallBackDownloadInterface callBackDownloadInterface) {
        this.callBackDownloadInterface = callBackDownloadInterface;
    }
}
