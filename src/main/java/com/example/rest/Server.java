package com.example.rest;

import java.io.IOException;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorSystem;

public class Server {
	public static void main(final String[] args) throws IOException {
		// boot up server using the route as defined below
		final ActorSystem system = ActorSystem.create();

		// HttpApp.bindRoute expects a route being provided by HttpApp.createRoute
		final Config config = ConfigFactory.load();
		new Router(config).bindRoute("0.0.0.0", 3000, system);
		System.out.println("Type RETURN to exit");
		System.in.read();
		system.terminate();
	}
}
