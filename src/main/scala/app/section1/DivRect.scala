package app.section1

import processing.core.PApplet
import processing.core.PConstants.HSB

import scala.annotation.tailrec

object DivRect {
  @tailrec
  private def gcd(a: Int, b: Int, ls: List[(Int, Int)] = Nil): List[(Int, Int)] =
    if (b == 0) (a, b) :: ls
    else gcd(b, a % b, (a, b) :: ls)

  /**
   * @return (x,y)
   *         ax + by = gcd(a,b)
   */
  private def extGCD(a: Int, b: Int): (Int, Int) = {
    if (b == 0) {
      (1, 0)
    } else {
      val (y, x) = extGCD(b, a % b)
      (x, y - a / b * x)
    }
  }

  /**
   * @return ((X座標, Y座標), 一辺の長さ)
   */
  def calc(a: Int, b: Int): List[((Int, Int), Int)] = {
    val scale = 50
    val scaledA = a * scale
    val scaledB = b * scale

    val divs = gcd(scaledA, scaledB)
    divs.zipWithIndex.foldLeft(Nil: List[((Int, Int), Int)]) { case (z, ((a, b), index)) =>
      val isEven = (index + 1) % 2 == 0

      z match {
        case Nil =>
          ((0, 0), b) :: z
        case head :: _ =>
          val ((prevX, prevY), prevWidth) = head
          if (isEven) {
            val x = prevX
            val y = prevY + prevWidth
            val width = b
            ((x, y), width) :: z
          } else {
            val x = prevX + prevWidth
            val y = prevY
            val width = b
            ((x, y), width) :: z
          }
      }
    }
  }
}

class DivRect extends PApplet {

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
