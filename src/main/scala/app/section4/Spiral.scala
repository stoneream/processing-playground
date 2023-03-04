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

  def f(theta: Float, mouseX: Int, radF: Float => Float)(width: Int, height: Int, step: Float) = {
    val scalar = Math.pow(10, (mouseX / width).toFloat) * (height / 2)
    val x1 = scalar * radF(theta) * Math.cos(theta)
    val y1 = scalar * radF(theta) * Math.sin(theta)
    val x2 = scalar * radF(theta + step) * Math.cos(theta + step)
    val y2 = scalar * radF(theta + step) * Math.sin(theta + step)
    (x1.toFloat, y1.toFloat, x2.toFloat, y2.toFloat)
  }
}

class Spiral extends PApplet {
  private val w = 500
  private val h = 500
  private val step: Float = 2f * Math.PI.toFloat * 0.01f

  override def settings(): Unit = {
    size(w, h)
  }

  override def setup(): Unit = {
    frameRate(60)
  }

  override def draw(): Unit = {
    background(255)

    val txt = s"mouseX = $mouseX / frameCount = $frameCount"
    fill(255, 0, 0)
    text(txt, 40, 120)

    translate(width / 2, height / 2)
    (0 to 1000)
      .foldLeft(Nil: List[Float]) { case (ls, _) =>
        ls match {
          case Nil => 0 :: Nil
          case prev :: _ =>
            prev - step :: ls
        }
      }
      .foreach { theta =>
        val (x1, y1, x2, y2) = Spiral.f(theta, mouseX, Spiral.powSpiral)(width, height, step)
        line(x1, y1, x2, y2)
      }

  }
}
