package com.wuzz.demo;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * @description:
 * @author: Wuzhenzhao@hikvision.com.cn
 * @time 2020/4/14 20:39
 * @since 1.0
 **/
public class RxJavaTest {
    public static void main(String[] args) {
        //例子1
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello RxJava");
                subscriber.onNext("I am程序猿DD");
                subscriber.onCompleted();
            }
        });

        //创建订阅者subscriber
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Subscriber : onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("Subscriber : " + s);
            }
        };
        //订阅
        observable.subscribe(subscriber);
        //例子2
        System.out.println("=========================================");
        //正常情况是先产生数据，再注册，再消费。
        Observable.just(dataProducer()).doOnSubscribe(() -> {
            System.out.println("Subscribe!");
        }).subscribe(s -> {
            System.out.println("Consume Data :" + s);
        });

        //使用了defer之后，是先注册，再生产数据，再消费。
        Observable.defer(() -> {
            return Observable.just(dataProducer());
        }).doOnSubscribe(() -> {
            System.out.println("Subscribe!");
        }).subscribe(s -> {
            System.out.println("Consume Data :" + s);
        });

        System.out.println("=========================================");
        //我们不关心onError和onComplete，所以只需要第一个参数就可以
        //例子3
        Observable<String> myObservable = Observable.just("Hello, world! just");

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };

        myObservable.subscribe(onNextAction);


    }

    private static String dataProducer() {
        String str = "Hello,RxJava!";
        System.out.println("Produce Data :" + str);
        return str;
    }

//    public Observable<R> toObservable() {
//        final AbstractCommand<R> _cmd = this;
//        //会在Observable结束前触发回调该call方法，无论是正常还是异常终止
//        //doOnCompleted handler already did all of the SUCCESS work
//        //doOnError handler already did all of the FAILURE/TIMEOUT/REJECTION/BAD_REQUEST work
//        final Action0 terminateCommandCleanup = new Action0() {
//
//            @Override
//            public void call() {
//                if (_cmd.commandState.compareAndSet(CommandState.OBSERVABLE_CHAIN_CREATED, CommandState.TERMINAL)) {
//                    handleCommandEnd(false); //user code never ran
//                } else if (_cmd.commandState.compareAndSet(CommandState.USER_CODE_EXECUTED, CommandState.TERMINAL)) {
//                    handleCommandEnd(true); //user code did run
//                }
//            }
//        };
//
//        //取消订阅时的监听会进行回调该 call方法
//        //mark the command as CANCELLED and store the latency (in addition to standard cleanup)
//        final Action0 unsubscribeCommandCleanup = new Action0() {
//            @Override
//            public void call() {
//                if (_cmd.commandState.compareAndSet(CommandState.OBSERVABLE_CHAIN_CREATED, CommandState.UNSUBSCRIBED)) {
//                    if (!_cmd.executionResult.containsTerminalEvent()) {
//                        _cmd.eventNotifier.markEvent(HystrixEventType.CANCELLED, _cmd.commandKey);
//                        try {
//                            executionHook.onUnsubscribe(_cmd);
//                        } catch (Throwable hookEx) {
//                            logger.warn("Error calling HystrixCommandExecutionHook.onUnsubscribe", hookEx);
//                        }
//                        _cmd.executionResultAtTimeOfCancellation = _cmd.executionResult
//                                .addEvent((int) (System.currentTimeMillis() - _cmd.commandStartTimestamp), HystrixEventType.CANCELLED);
//                    }
//                    handleCommandEnd(false); //user code never ran
//                } else if (_cmd.commandState.compareAndSet(CommandState.USER_CODE_EXECUTED, CommandState.UNSUBSCRIBED)) {
//                    if (!_cmd.executionResult.containsTerminalEvent()) {
//                        _cmd.eventNotifier.markEvent(HystrixEventType.CANCELLED, _cmd.commandKey);
//                        try {
//                            executionHook.onUnsubscribe(_cmd);
//                        } catch (Throwable hookEx) {
//                            logger.warn("Error calling HystrixCommandExecutionHook.onUnsubscribe", hookEx);
//                        }
//                        _cmd.executionResultAtTimeOfCancellation = _cmd.executionResult
//                                .addEvent((int) (System.currentTimeMillis() - _cmd.commandStartTimestamp), HystrixEventType.CANCELLED);
//                    }
//                    handleCommandEnd(true); //user code did run
//                }
//            }
//        };
//
//        final Func0<Observable<R>> applyHystrixSemantics = new Func0<Observable<R>>() {
//            @Override
//            public Observable<R> call() {
//                if (commandState.get().equals(CommandState.UNSUBSCRIBED)) {
//                    return Observable.never(); // 立即终止整个流程。
//                }//返回执行命令的Observable
//                return applyHystrixSemantics(_cmd);
//            }
//        };
//
//        final Func1<R, R> wrapWithAllOnNextHooks = new Func1<R, R>() {
//            @Override
//            public R call(R r) {
//                R afterFirstApplication = r;
//
//                try {
//                    afterFirstApplication = executionHook.onComplete(_cmd, r);
//                } catch (Throwable hookEx) {
//                    logger.warn("Error calling HystrixCommandExecutionHook.onComplete", hookEx);
//                }
//
//                try {
//                    return executionHook.onEmit(_cmd, afterFirstApplication);
//                } catch (Throwable hookEx) {
//                    logger.warn("Error calling HystrixCommandExecutionHook.onEmit", hookEx);
//                    return afterFirstApplication;
//                }
//            }
//        };
//
//        final Action0 fireOnCompletedHook = new Action0() {
//            @Override
//            public void call() {
//                try {
//                    executionHook.onSuccess(_cmd);
//                } catch (Throwable hookEx) {
//                    logger.warn("Error calling HystrixCommandExecutionHook.onSuccess", hookEx);
//                }
//            }
//        };
//
//        return Observable.defer(new Func0<Observable<R>>() {
//            @Override
//            public Observable<R> call() {
//                /* this is a stateful object so can only be used once */
//                // CAS保证命令只执行一次
//                if (!commandState.compareAndSet(CommandState.NOT_STARTED, CommandState.OBSERVABLE_CHAIN_CREATED)) {
//                    IllegalStateException ex = new IllegalStateException("This instance can only be executed once. Please instantiate a new instance.");
//                    //TODO make a new error type for this
//                    throw new HystrixRuntimeException(FailureType.BAD_REQUEST_EXCEPTION, _cmd.getClass(), getLogMessagePrefix() + " command executed multiple times - this is not permitted.", ex, null);
//                }
//                // 命令开始时间戳
//                commandStartTimestamp = System.currentTimeMillis();
//                // 打印日志
//                if (properties.requestLogEnabled().get()) {
//                    // log this command execution regardless of what happened
//                    if (currentRequestLog != null) {
//                        currentRequestLog.addExecutedCommand(_cmd);
//                    }
//                }
//                // 缓存开关，缓存KEY（这个是Hystrix中请求缓存功能，hystrix支持将一个请求结果缓存起来，
//                // 下一个具有相同key的请求将直接从缓存中取出结果，减少请求开销）
//                final boolean requestCacheEnabled = isRequestCachingEnabled();
//                final String cacheKey = getCacheKey();
//
//                /* try from cache first */
//                if (requestCacheEnabled) {//如果开启了缓存机制，则从缓存中获取结果
//                    HystrixCommandResponseFromCache<R> fromCache = (HystrixCommandResponseFromCache<R>) requestCache.get(cacheKey);
//                    if (fromCache != null) {
//                        isResponseFromCache = true;
//                        return handleRequestCacheHitAndEmitValues(fromCache, _cmd);
//                    }
//                }
//                // 声明执行命令的Observable
//                Observable<R> hystrixObservable =
//                        Observable.defer(applyHystrixSemantics)
//                                .map(wrapWithAllOnNextHooks);
//
//                Observable<R> afterCache;
//
//                // put in cache  保存请求结果到缓存中
//                if (requestCacheEnabled && cacheKey != null) {
//                    // wrap it for caching
//                    HystrixCachedObservable<R> toCache = HystrixCachedObservable.from(hystrixObservable, _cmd);
//                    HystrixCommandResponseFromCache<R> fromCache = (HystrixCommandResponseFromCache<R>) requestCache.putIfAbsent(cacheKey, toCache);
//                    if (fromCache != null) {
//                        // another thread beat us so we'll use the cached value instead
//                        toCache.unsubscribe();
//                        isResponseFromCache = true;
//                        return handleRequestCacheHitAndEmitValues(fromCache, _cmd);
//                    } else {
//                        // we just created an ObservableCommand so we cast and return it
//                        afterCache = toCache.toObservable();
//                    }
//                } else {
//                    afterCache = hystrixObservable;
//                }
//
//                return afterCache
//                        //会在Observable结束前触发回调，无论是正常还是异常终止
//                        .doOnTerminate(terminateCommandCleanup)     // perform cleanup once (either on normal terminal state (this line), or unsubscribe (next line))
//                        //取消订阅时的监听
//                        .doOnUnsubscribe(unsubscribeCommandCleanup) // perform cleanup once
//                        //Observable正常终止时的监听
//                        .doOnCompleted(fireOnCompletedHook);
//            }
//        });
//    }
}
