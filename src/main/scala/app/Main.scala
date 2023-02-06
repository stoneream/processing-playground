package app

import processing.core.PApplet

class PAppletImpl extends PApplet {

  private val w = 1280
  private val h = 640
  private val tikaTika = TikaTika(w, h, 20)

  override def settings(): Unit = {
    size(w, h)
  }

  override def setup(): Unit = {
    frameRate(60)
  }

  override def draw(): Unit = {
    background(255)

    tikaTika.f.foreach { case (x, y, (r, g, b)) =>
      fill(r, g, b)
      rect(x, y, tikaTika.size, tikaTika.size)
    }

    // render debug info
    textSize(24)
    fill(255, 0, 0, 127)
    text(s"fps $frameRate", 10, 24)
  }
}

object Main extends App {
  PApplet.main(classOf[PAppletImpl])
}
