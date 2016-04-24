package com.example.rest

import akka.NotUsed
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.scaladsl.Flow
import akka.stream.{ActorMaterializer, ActorMaterializerSettings, Fusing}

object WebServer extends App {
  implicit val system = ActorSystem()
  implicit val materializer =  ActorMaterializer(ActorMaterializerSettings(system).withAutoFusing(false))
  implicit val executionContext = system.dispatcher

  val requestHandler: HttpRequest => HttpResponse = {
    case _ => HttpResponse(entity = HttpEntity(ContentTypes.`text/plain(UTF-8)`,"Ok"))
  }

  val flow: Flow[HttpRequest, HttpResponse, NotUsed] = Flow[HttpRequest] map requestHandler
  val prefused = Fusing.aggressive(flow)
  val httpHandler = Flow.fromGraph(prefused)

  Http().bindAndHandle(handler = httpHandler, interface = "0.0.0.0", port = 3000)
}