package app.section4

import processing.core.{PApplet, PVector}

import scala.util.Random

class RecurSquare extends PApplet {
  private val pad = 100
  private val w = 1000
  private val h = 1000

  // 角
  private val topLeft = new PVector(0 + pad, 0 + pad)
  private val topRight = new PVector(w, 0 + pad)
  private val bottomRight = new PVector(w, h)
  private val bottomLeft = new PVector(0 + pad, h)
  private val initVec = topLeft :: topRight :: bottomRight :: bottomLeft :: Nil

  private var vec = initVec
  private val rand = new Random()

  override def settings(): Unit = {
    size(w + pad, h + pad)
  }

  override def setup(): Unit = {
    frameRate(60)
  }

  override def draw(): Unit = {
    val gap = rand.nextInt(20) * 0.001f

    // 5秒おきにリセット
    if (frameCount % 300 == 0) {
      vec = initVec
      background(255, 255, 255)
    }

    // 0.5秒おきに色を変える
    if (frameCount % 30 == 0) {
      stroke(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255))
    }

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

    // varつかうのやめたい
    vec = diagonal.map { case (v1, v2) =>
      val dir = PVector.sub(v2, v1).mult(gap)
      PVector.add(v1, dir)
    }
  }
}
