package app.section4

import processing.core.{PApplet, PVector}

import scala.util.Random

object RecurSquare {}

class RecurSquare extends PApplet {
  private val pad = 100
  private val w = 1000
  private val h = 1000
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

    if (frameCount % 300 == 0) {
      vec = initVec
      background(255, 255, 255)
    }

    if (frameCount % 30 == 0) {
      stroke(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255))
    }

    // 配列を直に叩くのをやめたい気もする
    (0 until 4).foreach { index =>
      line(vec(index).x, vec(index).y, vec((index + 1) % 4).x, vec((index + 1) % 4).y)
    }

    // varつかうのもやめたい気もする
    vec = (0 until 4).map { index =>
      val dir = PVector.sub(vec((index + 1) % 4), vec(index)).mult(gap)
      PVector.add(vec(index), dir)
    }.toList
  }
}
