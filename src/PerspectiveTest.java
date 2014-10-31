
import processing.core.PApplet;

public class PerspectiveTest extends PApplet {
	
	public Vector3[] testPoints;
	public int n = 2;
	
	public float cX = 5, cY = 0, cZ = 0;
	
	public void setup()
	{
		size(1500,900,P3D);
		testPoints = new Vector3[n];
		for (int i = 0; i < n; i++)
		{
			testPoints[i] = new Vector3(
					(float)Math.random()*500-250,
					(float)Math.random()*500-250,
					(float)Math.random()*500-250
					);
		}
	}
	
	public void draw()
	{
		background(255);
		fill(255,0,0);
		camera(cX,cY,cZ,0,0,-10,0,1,0);
		perspective();
		for (int i = 0; i < n; i++)
		{
			pushMatrix();
			translate(testPoints[i].x,testPoints[i].y,testPoints[i].z);
			box(5);
			popMatrix();
		}
		hint(DISABLE_DEPTH_TEST);
		camera();
		for (int i = 0; i < n; i++)
		{
			Vector2 temp = project(new Vector3(testPoints[i].x,testPoints[i].y,testPoints[i].z));
			text((int)temp.x + ", " + (int)temp.y, temp.x, temp.y);
			System.out.println(temp.x + " " + temp.y);
		}
		hint(ENABLE_DEPTH_TEST);
	}
	
	/*public Vector2 projection(Vector3 a, Vector3 c, Vector3 theta, Vector3 e)
	{
		
	}*/
	
	public Vector2 project(Vector3 p)
	{
		Vector3 rel = new Vector3(p.x - cX, p.y - cY, p.z - cZ);
		return new Vector2(rel.x/rel.z + width/2,-rel.y/rel.z + height/2);
	}
	
	public class Vector2
	{
		public float x,y;
		public Vector2(float a, float b)
		{
			x = a; y = b;
		}
	}
	public class Vector3
	{
		public float x,y,z;
		public Vector3(float a, float b, float c)
		{
			x = a; y = b; z = c;
		}
	}
	
}
