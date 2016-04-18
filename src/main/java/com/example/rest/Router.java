package com.example.rest;

import com.typesafe.config.Config;

import akka.http.javadsl.model.ContentTypes;
import akka.http.javadsl.server.HttpApp;
import akka.http.javadsl.server.RequestVal;
import akka.http.javadsl.server.Route;
import akka.http.javadsl.server.values.Parameters;

public class Router extends HttpApp {

	private final RequestVal<String> _name;
	private final RequestVal<Integer> _times;

	private final Route _helloRoute;

	public Router(final Config config) {
		final HelloWorldEndpoint helloWorldEndpoint = new HelloWorldEndpoint(config);
		_name = Parameters.stringValue("name").withDefault(config.getString("app.helloWorld.defaultName"));
		_times = Parameters.intValue("times").withDefault(1);
		_helloRoute = handleWithAsync2(_name, _times, helloWorldEndpoint::sayHello);
	}

	@Override
	public Route createRoute() {
		return route(
				get(
						pathSingleSlash().route(
								complete(ContentTypes.TEXT_HTML_UTF8,
										"<html><body>Hello world!</body></html>")),
						path("ping").route(
								complete("PONG!")),
						path("hello").route(
								_helloRoute)));
	}

}
