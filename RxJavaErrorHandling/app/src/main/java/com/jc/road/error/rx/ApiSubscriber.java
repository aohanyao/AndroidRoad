package com.jc.road.error.rx;

import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.reactivestreams.Subscriber;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;


/**
 * <p>1.错误处理转换</p>
 */
public abstract class ApiSubscriber<T> extends ResourceSubscriber<T> {
    private BaseView v;

    private ApiSubscriber() {
    }

    /**
     * 使用这个构造函数时候 将错误提示交给 v来处理
     *
     * @param v
     */
    public ApiSubscriber(BaseView v) {
        this.v = v;
    }

    public ApiSubscriber(Subscriber<?> subscriber, BaseView v) {
        this.v = v;
    }

    public ApiSubscriber(Subscriber<?> subscriber, boolean shareSubscriptions, BaseView v) {
        this.v = v;
    }

    @Override
    public void onError(Throwable e) {
        NetError error = null;
        if (e != null) {
            // 判断异常类型
            if (!(e instanceof NetError)) {
                if (e instanceof UnknownHostException) {
                    error = new NetError(e, NetError.NoConnectError);
                } else if (e instanceof JSONException || e instanceof JsonParseException) {
                    error = new NetError(e, NetError.ParseError);
                } else if (e instanceof SocketTimeoutException) {
                    error = new NetError(e, NetError.SocketTimeoutError);
                } else if (e instanceof ConnectException) {
                    error = new NetError(e, NetError.ConnectExceptionError);
                } else if (e instanceof HttpException) {
                    //处理网络状态码失败的情况
                    error = handlerRequestFail((HttpException) e);
                } else {
                    error = new NetError(e, NetError.OtherError);
                }
            } else {
                error = new NetError("很抱歉，我们发生一些错误", NetError.OTHER);
            }


            if (v != null) {
                v.onFail(error);
            } else {
                onFail(error);
            }

        }

    }

    /**
     * 处理请求码中的错误
     *
     * @param e 抛出错误
     * @return
     */
    private NetError handlerRequestFail(HttpException e) {
        try {
            // token失效 或者未登录
            if (e.code() == 401) {
                return new NetError("您的登陆令牌已失效，请重新登陆！", NetError.LOGIN_OUT);
            }
            // TODO 其他服务器约定错误处理

        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return new NetError(e, NetError.HttpException);
    }

    /**
     * 发生错误
     *
     * @param error
     */
    public void onFail(NetError error) {
        error.printStackTrace();
    }


    @Override
    public void onComplete() {
    }


}
