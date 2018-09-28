package com.jc.road.error.rx;

import android.text.TextUtils;

import java.io.IOException;

/**
 * Created by jc on 2016/12/24.
 * 网络错误封装
 */

public class NetError extends IOException {
    /**传入的异常类*/
    private Throwable exception;
    /**错误类型*/
    private int mErrorType = NoConnectError;
    /**错误提示信息*/
    private String mErrorMessage;
    /**数据解析异常*/
    public static final int ParseError = 0;
    /**无连接异常*/
    public static final int NoConnectError = 1;
    /**用户验证异常*/
    public static final int AuthError = 2;
    /**无数据返回异常*/
    public static final int NoDataError = 3;
    /**业务异常*/
    public static final int BusinessError = 4;
    /**其他异常*/
    public static final int OtherError = 5;
    /**网络连接超时*/
    public static final int SocketTimeoutError = 6;
    /**未知错误*/
    public static final int UknownError = 500;
    /**无法连接到服务*/
    public static final int ConnectExceptionError = 7;
    /**服务器错误*/
    public static final int HttpException = 8;
    /**登陆失效*/
    public static final int LOGIN_OUT = 401;
    /**其他*/
    public static final int OTHER = -99;
    /**没有网络*/
    public static final int UNOKE = -1;
    /**无法找到*/
    public static final int NOT_FOUND = 404;


    public NetError(Throwable exception, int mErrorType) {
        this.exception = exception;
        this.mErrorType = mErrorType;
    }

    public NetError(String message, Throwable cause) {
        super(message, cause);
    }

    public NetError(String message, int mErrorType) {
        super(message);
        this.mErrorType = mErrorType;
        this.mErrorMessage = message;
    }

    @Override
    public String getMessage() {
        if (!TextUtils.isEmpty(mErrorMessage)) {
            return mErrorMessage;
        }
        switch (mErrorType) {
            case ParseError:
                return "数据解析异常";
            case NoConnectError:
                return "无连接异常";
            case AuthError:
                return "用户验证异常";
            case NoDataError:
                return "无数据返回异常";
            case BusinessError:
                return "业务异常";
            case SocketTimeoutError:
                return "网络连接超时";
            case OTHER:
                return mErrorMessage;
            case UNOKE:
                return "当前无网络连接";
            case ConnectExceptionError:
                return "无法连接到服务器，请检查网络连接后再试！";
            case HttpException:
                try {
                    if (exception.getMessage().equals("HTTP 500 Internal Server Error")) {
                        return "服务器发生错误！";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (exception.getMessage().contains("Not Found"))
                    return "无法连接到服务器，请检查网络连接后再试！";
                return "服务器发生错误";
        }


        try {
            return exception.getMessage();
        } catch (Exception e) {
            return "未知错误";
        }

    }

    /**
     * 获取错误类型
     */
    public int getErrorType() {
        return mErrorType;
    }
}
