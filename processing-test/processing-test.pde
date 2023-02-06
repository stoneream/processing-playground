import java.util.ArrayList;

class Maru {
  private float x, y;
  private int delta;
  private float red = random(255);
  private float green = random(255);
  private float blue = random(255);
  
  public Maru(float x, float y) {
    this.x = x;
    this.y = y;
    this.delta = 0;
  }
  
  public void update() {
    this.delta += 1;
  }
  
  public void render() {
    fill(red, green, blue);
    circle(x + delta, y, 100);
  }
}

ArrayList<Maru> circles = new ArrayList<Maru>();

void setup() {
  size(800, 600);
  
  for (float i = 1;i <= 5;i++) {
    circles.add(new Maru(200, 100 * i));
  }
}

void draw() {
  background(255);
  
  for (int index = 0; index < 5; index++) {
    circles.get(index).update();
    circles.get(index).render();
  }
}

