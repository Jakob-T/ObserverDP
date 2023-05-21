import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


interface Observer
{
  void Update ();
}

class Car implements Observer
{
  private TrafficLight trafficLight;
  private String name;

  public Car (TrafficLight trafficLight, String Name)
  {
    this.trafficLight = trafficLight;
    this.name = Name;
  }

  public void Update ()
  {
    String color = trafficLight.GetCurrentColor();
    if (color.equals ("Green"))
      {
	System.out.println ("Go" +" "+ this.name);
      }
    else
      {
	System.out.println ("Stop" +" "+ this.name);
      }
  }
}


class TrafficLight
{
  private String currentColor;
  private List < Observer > observers = new ArrayList <> ();

  public String GetCurrentColor ()
  {
    return this.currentColor;
  }

  public void SetCurrentColor (String color)
  {
    this.currentColor = color;
    NotifyObservers ();
  }

  public void Attach (Observer observer)
  {
    observers.add (observer);
  }

  public void Detach (Observer observer)
  {
    observers.remove (observer);
  }

  public void NotifyObservers ()
  {
  for (Observer observer:observers)
      {
	observer.Update ();
      }
  }


}


public class Main{
public static void main (String[]args)
{

  TrafficLight trafficLight = new TrafficLight ();
  Car car1 = new Car (trafficLight, "car1");
  Car car2 = new Car (trafficLight, "car2");

  trafficLight.Attach (car1);
  trafficLight.Attach (car2);

  trafficLight.SetCurrentColor ("Red");
  trafficLight.SetCurrentColor ("Green");

  trafficLight.Detach (car1);

  trafficLight.SetCurrentColor ("Red");
  trafficLight.SetCurrentColor ("Green");
}
}
