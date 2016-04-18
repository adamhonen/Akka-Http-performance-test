package com.example.rest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicLong;

import com.typesafe.config.Config;

import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.RequestContext;
import akka.http.javadsl.server.RouteResult;
import rx.Observable;
import rx.schedulers.Schedulers;

public class HelloWorldEndpoint {

	private final String _template;

	public HelloWorldEndpoint(final Config config) {
		_template = config.getString("app.helloWorld.template");
	}

	private static AtomicLong counter = new AtomicLong(0l);

	//convert the Observable to a completeionStage
	public CompletionStage<RouteResult> sayHello(final RequestContext ctx, final String name, final int times) {
		final CompletableFuture<RouteResult> future = new CompletableFuture<>();

		//ignoring the times argument.
		sayHello(name, 1).subscribe(res -> future.complete(ctx.completeAs(Jackson.json(), res)));

		return future;
	}

	//Actual logic for this endpoint
	public Observable<Saying> sayHello(final String name, final int times) {
		return Observable.just(name)
				.observeOn(Schedulers.computation())
				.map(nam -> String.format(_template, name))
				.map(text -> new Saying(counter.incrementAndGet(), text));
	}

}
