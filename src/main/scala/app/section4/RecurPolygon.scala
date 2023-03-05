package app.section4

import processing.core.{PApplet, PVector}

class RecurPolygon extends PApplet {
  private val pad = 100
  private val w = 1000
  private val h = 1000

  private val gap = 0.1f
  private val gon = 18

  private val initVec = (0 to gon).map { i =>
    val angle = (2 * i * Math.PI) / gon
    val n = w / 2f
    PVector.fromAngle(angle.toFloat).mult(n)
  }.toList

  def diagonal(ls: List[PVector]): List[(PVector, PVector)] = {
    val head :: tail = ls
    val ls2 = (head :: tail.reverse).reverse
    // topLeft :: topRight :: bottomRight :: bottomLeft :: Nil
    // topRight :: bottomRight :: bottomLeft :: topLeft :: Nil
    ls.zip(ls2)
  }

  def f(resetFrameCount: Int): List[List[PVector]] = {
    (0 to resetFrameCount).foldLeft(initVec :: Nil) { case (z, frameCount) =>
      val head :: tail = z
      diagonal(head).map { case (v1, v2) =>
        val dir = PVector.sub(v2, v1).mult(gap)
        PVector.add(v1, dir)
      } :: z
    }
  }

  override def settings(): Unit = {
    size(w + pad, h + pad)
  }

  override def setup(): Unit = {
    frameRate(60)
  }

  override def draw(): Unit = {
    translate(width / 2, height / 2)

    f(300).reverse.take(frameCount % 300).foreach { vec =>
      diagonal(vec).foreach { case (v1, v2) =>
        line(v1.x, v1.y, v2.x, v2.y)
      }
    }
  }
}
