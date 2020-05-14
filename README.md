# Модуль для взаимодействия с API

## Connecting module

### 0. Clone repository uses git submodule

git submodule add https://github.com/LimeHD/android-api-module

### 1. Add module in project
File-Project Structure-Modules

Click + button

1. Add module androidapimodule without DEMO module
2. Demo module its a example application from uses androidapimodule

### 2. Add to dependencies

implementation project(':androidapimodule')


## Use

```
String api_root = API_ROOT;

LimeApiClient limeApiClient = new LimeApiClient(api_root);
ApiValues apiValues = new ApiValues();

limeApiClient.downloadChannelList(apiValues.getSCHEME_HTTP(), apiValues.getURL_CHANNELS_PATH());
limeApiClient.setDownloadChannelListCallBack(new LimeApiClient.DownloadChannelListCallBack() {
            @Override
            public void downloadChannelListSuccess(String response) {
                //response
            }

            @Override
            public void downloadChannelListError(String message) {
		            //error message
            }
        });

String before_date = LimeRFC.timeStampToRFC(before_date_timestamp);
String after_date = LimeRFC.timeStampToRFC(after_date_timestamp);

limeApiClient.downloadBroadcast(apiValues.getSCHEME_HTTP(), apiValues.getURL_BROADCAST_PATH()
                , example_channel_id, before_date, after_date, example_time_zone);
limeApiClient.setDownloadBroadCastCallBack(new LimeApiClient.DownloadBroadCastCallBack() {
            @Override
            public void downloadBroadCastSuccess(String response) {
                
            }

            @Override
            public void downloadBroadCastError(String message) {

            }
        });
```
