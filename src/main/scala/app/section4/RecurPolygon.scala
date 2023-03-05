package app.section4

import processing.core.{PApplet, PVector}

import scala.util.Random

class RecurPolygon extends PApplet {
  private val pad = 100
  private val w = 1000
  private val h = 1000

  private val gap = 0.1f
  private val gon = 18

  private var vec = (0 to gon).map { i =>
    val angle = (2 * i * Math.PI) / gon
    val n = w / 2f
    PVector.fromAngle(angle.toFloat).mult(n)
  }.toList

  override def settings(): Unit = {
    size(w + pad, h + pad)
  }

  override def setup(): Unit = {
    frameRate(60)
  }

  override def draw(): Unit = {
    translate(width / 2, height / 2)

    val diagonal = {
      val head :: tail = vec
      val vec2 = (head :: tail.reverse).reverse
      // topLeft :: topRight :: bottomRight :: bottomLeft :: Nil
      // topRight :: bottomRight :: bottomLeft :: topLeft :: Nil
      vec.zip(vec2)
    }

    diagonal.foreach { case (v1, v2) =>
      line(v1.x, v1.y, v2.x, v2.y)
    }

    vec = diagonal.map { case (v1, v2) =>
      val dir = PVector.sub(v2, v1).mult(gap)
      PVector.add(v1, dir)
    }
  }
}
