package app

import processing.core.PApplet

class PAppletImpl extends PApplet {

  override def settings(): Unit = {
    size(600, 400)
  }

  override def setup(): Unit = {
    frameRate(60)
  }

  override def draw(): Unit = {
    background(255)

    fill(255, 0, 0)
    textSize(52)
    text("HELLO?", 100, 100)

    println(frameCount)
  }
}

object Main extends App {
  PApplet.main(classOf[PAppletImpl])
}
