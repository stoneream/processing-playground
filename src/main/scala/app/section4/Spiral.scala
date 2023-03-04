package app.section4

import processing.core.PApplet

object Spiral {

  def archimedeanSpiral(t: Float): Float = {
    5 * t
  }
  def fermatSpiral(t: Float): Float = {
    20 * Math.sqrt(t).toFloat
  }
  def powSpiral(t: Float): Float = {
    Math.pow(1.1, t).toFloat
  }
}

class Spiral extends PApplet {
  private val w = 1200
  private val h = 640
  private var theta = 0f
  private val step: Float = 2f * Math.PI.toFloat * 0.01f

  override def settings(): Unit = {
    size(w, h)
  }

  override def setup(): Unit = {
    frameRate(60)
  }

  override def draw(): Unit = {
//    background(255)

    translate(width / 2, height / 2)

    val f = (t: Float) => Spiral.fermatSpiral(t)

    val x1 = f(theta) * Math.cos(theta).toFloat
    val y1 = f(theta) * Math.sin(theta).toFloat
    val x2 = f(theta + step) * Math.cos(theta + step).toFloat
    val y2 = f(theta + step) * Math.sin(theta + step).toFloat

    line(x1, y1, x2, y2)

    theta += step
  }
}
