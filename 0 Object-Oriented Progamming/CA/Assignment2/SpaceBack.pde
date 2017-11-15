class SpaceBack
{
  float xpos, ypos;
  
  SpaceBack()
  {
    xpos = random(0,width);
    ypos = random(0,height);
    sphereDetail((int)xpos % 5);
    sphere(2);
  }
}