package app

import app.section1.DivRect
import processing.core.PApplet
import processing.core.PConstants.HSB

class PAppletImpl extends PApplet {

  private val w = 500
  private val h = 500
  private val divRect = DivRect.calc(10, 6)

  override def settings(): Unit = {
    size(w, h)
  }

  override def setup(): Unit = {
    frameRate(60)
    colorMode(HSB, 1)
  }

  override def draw(): Unit = {
    background(1)
    divRect.foreach { case ((xPos, yPos), width) =>
      val col = color(random(1), 1, 1)
      fill(col)
      rect(xPos, yPos, width, width)
    }
  }
}

object Main extends App {
  PApplet.main(classOf[PAppletImpl])
}
