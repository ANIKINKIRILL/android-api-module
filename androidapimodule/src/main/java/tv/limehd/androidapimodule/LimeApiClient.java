package tv.limehd.androidapimodule;

import tv.limehd.androidapimodule.Download.ClientDownloading;

public class LimeApiClient {

    private String api_root;

    public LimeApiClient(String apiRoot) {
        this.api_root = apiRoot;
    }

    /*Download channel List*/
    //region Download channel List
    public void downloadChannelList(String scheme, String endpoint_channels ) {
        if (api_root != null){
            ClientDownloading clientDownloading = new ClientDownloading();
            clientDownloading.setCallBackDownloadInterface(new ClientDownloading.CallBackDownloadInterface() {
                @Override
                public void callBackDownloadedSuccess(String response) {
                    if (downloadChannelListCallBack != null)
                        downloadChannelListCallBack.downloadChannelListSuccess(response);
                }

                @Override
                public void callBackDownloadedError(String error_message) {
                    if (downloadChannelListCallBack != null)
                        downloadChannelListCallBack.downloadChannelListError(error_message);
                }

                @Override
                public void callBackDowloadedRequest(String request) {
                    if(downloadChannelListCallBack != null)
                        downloadChannelListCallBack.downloadChannelListRequest(request);
                }
            });
            clientDownloading.downloadChannelList(scheme, api_root, endpoint_channels);
        }
    }

    public interface DownloadChannelListCallBack {
        void downloadChannelListSuccess(String response);
        void downloadChannelListError(String message);
        void downloadChannelListRequest(String request);
    }

    private DownloadChannelListCallBack downloadChannelListCallBack;

    public void setDownloadChannelListCallBack(DownloadChannelListCallBack downloadChannelListCallBack) {
        this.downloadChannelListCallBack = downloadChannelListCallBack;
    }
    //endregion

    /*Download broadcast*/
    //region DownloadBroadcast
    public void downloadBroadcast(String scheme, String endpoint_broadcast, String channel_id, String before_date, String after_date, String time_zone) {
        if (api_root != null) {
            ClientDownloading clientDownloading = new ClientDownloading();
            clientDownloading.setCallBackDownloadInterface(new ClientDownloading.CallBackDownloadInterface() {
                @Override
                public void callBackDownloadedSuccess(String response) {
                    if (downloadBroadCastCallBack != null)
                        downloadBroadCastCallBack.downloadBroadCastSuccess(response);
                }

                @Override
                public void callBackDownloadedError(String error_message) {
                    if (downloadBroadCastCallBack != null)
                        downloadBroadCastCallBack.downloadBroadCastError(error_message);
                }

                @Override
                public void callBackDowloadedRequest(String request) {
                    if(downloadBroadCastCallBack != null)
                        downloadBroadCastCallBack.downloadBroadCastRequest(request);
                }
            });
            clientDownloading.downloadBroadCast(scheme, api_root, endpoint_broadcast, channel_id, before_date, after_date, time_zone);
        }
    }

    public interface DownloadBroadCastCallBack{
        void downloadBroadCastSuccess(String response);
        void downloadBroadCastError(String message);
        void downloadBroadCastRequest(String request);
    }

    private DownloadBroadCastCallBack downloadBroadCastCallBack;

    public void setDownloadBroadCastCallBack(DownloadBroadCastCallBack downloadBroadCastCallBack) {
        this.downloadBroadCastCallBack = downloadBroadCastCallBack;
    }
    //endregion

    //region Download ping
    public void downloadPing(String scheme, String endpoint_ping)
    {
        if(api_root != null)
        {
            ClientDownloading clientDownloading = new ClientDownloading();
            clientDownloading.setCallBackDownloadInterface(new ClientDownloading.CallBackDownloadInterface() {
                @Override
                public void callBackDownloadedSuccess(String response) {
                    if(downloadPingCallBack != null)
                        downloadPingCallBack.downloadPingSuccess(response);
                }

                @Override
                public void callBackDownloadedError(String error_message) {
                    if(downloadPingCallBack != null)
                        downloadPingCallBack.downloadPingError(error_message);
                }

                @Override
                public void callBackDowloadedRequest(String request) {
                    if(downloadPingCallBack != null)
                        downloadPingCallBack.downloadPingRequest(request);
                }
            });
            clientDownloading.dowloadPing(scheme, api_root, endpoint_ping);
        }
    }

    public interface DownloadPingCallBack{
        void downloadPingSuccess(String response);
        void downloadPingError(String message);
        void downloadPingRequest(String request);
    }
    private DownloadPingCallBack downloadPingCallBack;

    public void setDownloadPingCallBack(DownloadPingCallBack downloadPingCallBack) {
        this.downloadPingCallBack = downloadPingCallBack;
    }

    //endregion
}
