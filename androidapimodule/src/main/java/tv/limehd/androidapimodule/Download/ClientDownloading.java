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
        });
        channelListDownloading.loadingRequestChannelList(scheme, api_root, endpoint_channels);
    }

    public void downloadBroadCast(String scheme, String api_root, String endpoint_broadcast
            , String channel_id, String before_date, String after_date, String time_zone){
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
        });
        broadcastDownloading.loadingRequestBroadCast(scheme, api_root, endpoint_broadcast, channel_id, before_date, after_date, time_zone);
}

    public interface CallBackDownloadInterface {
        void callBackDownloadedSuccess(String response);

        void callBackDownloadedError(String error_message);
    }

    private CallBackDownloadInterface callBackDownloadInterface;

    public void setCallBackDownloadInterface(CallBackDownloadInterface callBackDownloadInterface) {
        this.callBackDownloadInterface = callBackDownloadInterface;
    }
}
