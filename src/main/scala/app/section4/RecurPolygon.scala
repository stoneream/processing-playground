package app.section4

import processing.core.{PApplet, PVector}

class RecurPolygon extends PApplet {
  private val pad = 100
  private val w = 1000
  private val h = 1000

  private val gon = 18

  private val initVec = (0 to gon).map { i =>
    val angle = (2 * i * Math.PI) / gon
    val n = w / 2f
    PVector.fromAngle(angle.toFloat).mult(n)
  }.toList

  private val resetCount = 300

  // 対角
  def diagonal(ls: List[PVector]): List[(PVector, PVector)] = {
    val head :: tail = ls
    val ls2 = (head :: tail.reverse).reverse
    ls.zip(ls2)
  }

  /**
   * @param gap 内接する正多角形のズレ
   * @param resetFrameCount 何フレームで書き直すか
   * @param currentFrameCount 現在のフレームカウント
   * @return
   */
  def f(gap: Float = 0.15f, resetFrameCount: Int)(currentFrameCount: Int): List[List[PVector]] = {
    // 描画しない図形を描画しないようにしてる
    val n = Math.min(resetFrameCount, currentFrameCount % resetFrameCount)
    (0 to n)
      .foldLeft(initVec :: Nil) { case (z, frameCount) =>
        val head :: tail = z
        diagonal(head).map { case (v1, v2) =>
          val dir = PVector.sub(v2, v1).mult(gap)
          PVector.add(v1, dir)
        } :: z
      }
      .reverse
  }

  override def settings(): Unit = {
    size(w + pad, h + pad)
  }

  override def setup(): Unit = {
    frameRate(60)
  }

  override def draw(): Unit = {
    background(255, 255, 255)

    // debug print
    val txt =
      s"""fps = $frameRate
         |frameCount = $frameCount
         |""".stripMargin
    fill(255, 0, 0)
    textSize(24)
    text(txt, 50, 100)

    translate(width / 2, height / 2)
    f(resetFrameCount = resetCount)(frameCount).foreach { vec =>
      diagonal(vec).foreach { case (v1, v2) =>
        line(v1.x, v1.y, v2.x, v2.y)
      }
    }
  }
}
