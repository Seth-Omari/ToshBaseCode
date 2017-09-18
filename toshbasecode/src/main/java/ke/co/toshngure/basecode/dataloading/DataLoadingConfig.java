/*
 * Copyright (c) 2017.
 *
 * Full Name : Anthony Ngure W.
 *
 * Email : anthonyngure25@gmail.com
 */

package ke.co.toshngure.basecode.dataloading;

/**
 * Created by Anthony Ngure on 15/09/2017.
 * Email : anthonyngure25@gmail.com.
 * Company : VibeCampo Social Network..
 */

public class DataLoadingConfig {

    private int loaderId = 0;

    /*If should immediately load fresh data after loading cache*/
    private boolean autoRefreshEnabled = true;
    /*If should load data when a user pulls down*/
    private boolean refreshEnabled = true;
    /*If should load data when a user reaches at the bottom*/
    private boolean loadingMoreEnabled = true;
    /*If items are cached*/
    private boolean cacheEnabled = false;
    private int loadMoreThreshold = 0;
    private String url;
    private boolean debugEnabled = false;
    private int perPage = 10;
    private boolean connectionEnabled;

    public DataLoadingConfig() {
    }

    public DataLoadingConfig disableConnection() {
        this.refreshEnabled = false;
        this.loadingMoreEnabled = false;
        this.autoRefreshEnabled = false;
        this.connectionEnabled = false;
        return this;
    }

    public DataLoadingConfig setLoaderId(int loaderId) {
        this.loaderId = loaderId;
        return this;
    }

    public DataLoadingConfig setAutoRefreshEnabled(boolean autoRefreshEnabled) {
        this.autoRefreshEnabled = autoRefreshEnabled;
        return this;
    }

    public DataLoadingConfig setRefreshEnabled(boolean refreshEnabled) {
        this.refreshEnabled = refreshEnabled;
        return this;
    }

    public DataLoadingConfig setLoadingMoreEnabled(boolean loadingMoreEnabled) {
        this.loadingMoreEnabled = loadingMoreEnabled;
        return this;
    }

    public DataLoadingConfig setCacheEnabled(boolean cacheEnabled) {
        this.cacheEnabled = cacheEnabled;
        return this;
    }

    public DataLoadingConfig setLoadMoreThreshold(int loadMoreThreshold) {
        this.loadMoreThreshold = loadMoreThreshold;
        return this;
    }

    public DataLoadingConfig setUrl(String url) {
        this.url = url;
        return this;
    }

    public DataLoadingConfig setDebugEnabled(boolean debugEnabled) {
        this.debugEnabled = debugEnabled;
        return this;
    }

    public DataLoadingConfig setPerPage(int perPage) {
        this.perPage = perPage;
        return this;
    }

    int getLoaderId() {
        return loaderId;
    }


    boolean isAutoRefreshEnabled() {
        return autoRefreshEnabled;
    }

    public boolean isLoadingMoreEnabled() {
        return loadingMoreEnabled;
    }

    int getLoadMoreThreshold() {
        return loadMoreThreshold;
    }

    String getUrl() {
        return url;
    }

    public boolean isDebugEnabled() {
        return debugEnabled;
    }

    public boolean isCacheEnabled() {
        return cacheEnabled;
    }

    public boolean isRefreshEnabled() {
        return refreshEnabled;
    }

    @Override
    public String toString() {
        return "DataLoadingConfig{" +
                "loaderId=" + loaderId +
                ", autoRefreshEnabled=" + autoRefreshEnabled +
                ", refreshEnabled=" + refreshEnabled +
                ", loadingMoreEnabled=" + loadingMoreEnabled +
                ", cacheEnabled=" + cacheEnabled +
                ", loadMoreThreshold=" + loadMoreThreshold +
                ", url='" + url + '\'' +
                ", debugEnabled=" + debugEnabled +
                '}';
    }


    public int getPerPage() {
        return perPage;
    }

    public boolean isConnectionEnabled() {
        return connectionEnabled;
    }
}
