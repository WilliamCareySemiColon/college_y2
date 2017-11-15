
ArrayList<SpaceBack> Stars = new ArrayList<SpaceBack>();
void setup()
{
  size(600,400);
  for (int i = 0; i < 200; i++)
  {
    SpaceBack star = new SpaceBack();
    Stars.add(star);
  }
} 
void draw()
{
  background(0);
  stroke(255);
  fill(255);
  /*for (SpaceBack star: Stars)
  {
    //ellipse(star.xpos,star.ypos,10,10);
    sphere(star.xpos);
  }*/
}