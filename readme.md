# Usage

Start up the sample with `gradlew run` and goto `http://localhost:8080/error`.

# Question - Wildfly 14 doesn't always call AsyncListener.onComplete()

In undertow Version 2.0.12.Final we observed a behavior change in asynchronous servlet processing. 
Since 2.0.12.Final the `AsyncListener#onComplete()` method is no longer called in our sample - AsyncErrorServlet.
As we try to release allocated resources in the `onComplete()` callback as suggested by the servlet specification, 
we gently ask, if the new behavior is intended or might be a bug introduced in this version.

Servlet code from AsyncErrorServlet
``` java
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.startAsync(); 
    throw new NullPointerException();
}
```
I would expect that both onError() and onComplete() callbacks are called by undertow.
> LoggingAsyncListener.onError(status = 200)
>   throwable = java.lang.NullPointerException
> LoggingAsyncListener.onComplete(status = 500)

Since version 2.0.12.Final the onComplete() callback is missing. 
Could you please clarify, if this is a bug in undertow or intended behavior?
Thanks for your support,
