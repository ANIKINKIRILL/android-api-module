package tv.limehd.androidapimodule;

import tv.limehd.androidapimodule.Download.ClientDownloading;

public class LimeApiClient {

    private String api_root;

    public LimeApiClient(String apiRoot) {
        this.api_root = apiRoot;
    }

    /*Download channel List*/
    //region Download channel List
    public void downloadChannelList(String scheme, String endpoint_channels) {
        if (api_root != null) {
            ClientDownloading clientDownloading = new ClientDownloading();
            clientDownloading.setCallBackDownloadInterface(new ClientDownloading.CallBackDownloadInterface() {
                @Override
                public void callBackDownloadedSuccess(String response) {
                    if (downloadChannelListCallBack != null)
                        downloadChannelListCallBack.downloadSuccess(response);
                }

                @Override
                public void callBackDownloadedError(String error_message) {
                    if (downloadChannelListCallBack != null)
                        downloadChannelListCallBack.downloadError(error_message);
                }
            });
            clientDownloading.setCallBackRequestInterface(new ClientDownloading.CallBackRequestInterface() {
                @Override
                public void callBackRequest(String request) {
                    if (requestChannelList != null)
                        requestChannelList.callBackRequest(request);
                }
            });
            clientDownloading.downloadChannelList(scheme, api_root, endpoint_channels);
        }
    }

    /*Download broadcast*/
    //region DownloadBroadcast
    public void downloadBroadcast(String scheme, String endpoint_broadcast, String channel_id, String before_date, String after_date, String time_zone) {
        if (api_root != null) {
            ClientDownloading clientDownloading = new ClientDownloading();
            clientDownloading.setCallBackDownloadInterface(new ClientDownloading.CallBackDownloadInterface() {
                @Override
                public void callBackDownloadedSuccess(String response) {
                    if (downloadBroadCastCallBack != null)
                        downloadBroadCastCallBack.downloadSuccess(response);
                }

                @Override
                public void callBackDownloadedError(String error_message) {
                    if (downloadBroadCastCallBack != null)
                        downloadBroadCastCallBack.downloadError(error_message);
                }
            });
            clientDownloading.setCallBackRequestInterface(new ClientDownloading.CallBackRequestInterface() {
                @Override
                public void callBackRequest(String request) {
                    if (requestBroadCastCallBack != null)
                        requestBroadCastCallBack.callBackRequest(request);
                }
            });
            clientDownloading.downloadBroadCast(scheme, api_root, endpoint_broadcast, channel_id, before_date, after_date, time_zone);
        }
    }

    //region Download ping
    public void downloadPing(String scheme, String endpoint_ping) {
        if (api_root != null) {
            ClientDownloading clientDownloading = new ClientDownloading();
            clientDownloading.setCallBackDownloadInterface(new ClientDownloading.CallBackDownloadInterface() {
                @Override
                public void callBackDownloadedSuccess(String response) {
                    if (downloadPingCallBack != null)
                        downloadPingCallBack.downloadSuccess(response);
                }

                @Override
                public void callBackDownloadedError(String error_message) {
                    if (downloadPingCallBack != null)
                        downloadPingCallBack.downloadError(error_message);
                }
            });
            clientDownloading.setCallBackRequestInterface(new ClientDownloading.CallBackRequestInterface() {
                @Override
                public void callBackRequest(String request) {
                    if (requestPingCallBack != null)
                        requestPingCallBack.callBackRequest(request);
                }
            });
            clientDownloading.dowloadPing(scheme, api_root, endpoint_ping);
        }
    }

    //region RequestCallBack
    public interface RequestCallBack {
        void callBackRequest(String request);
    }

    private RequestCallBack requestBroadCastCallBack;
    private RequestCallBack requestPingCallBack;
    private RequestCallBack requestChannelList;

    public void setRequestBroadCastCallBack(RequestCallBack requestBroadCastCallBack) {
        this.requestBroadCastCallBack = requestBroadCastCallBack;
    }

    public void setRequestPingCallBack(RequestCallBack requestPingCallBack) {
        this.requestPingCallBack = requestPingCallBack;
    }

    public void setRequestChannelList(RequestCallBack requestChannelList) {
        this.requestChannelList = requestChannelList;
    }
    //endregion

    //region DownloadCallBack
    //Используй для передачи полученных данных
    public interface DownloadCallBack {
        void downloadSuccess(String response);

        void downloadError(String message);
    }

    private DownloadCallBack downloadPingCallBack;
    private DownloadCallBack downloadChannelListCallBack;
    private DownloadCallBack downloadBroadCastCallBack;

    public void setDownloadPingCallBack(DownloadCallBack downloadPingCallBack) {
        this.downloadPingCallBack = downloadPingCallBack;
    }

    public void setDownloadChannelListCallBack(DownloadCallBack downloadChannelListCallBack) {
        this.downloadChannelListCallBack = downloadChannelListCallBack;
    }

    public void setDownloadBroadCastCallBack(DownloadCallBack downloadBroadCastCallBack) {
        this.downloadBroadCastCallBack = downloadBroadCastCallBack;
    }
}
