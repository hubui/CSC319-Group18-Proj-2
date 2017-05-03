/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postofficeproblem;

/**
 *
 * @author Ly
 */

import java.util.*;
public class Resource {
    // -1,1,0 described in compare method
    // compare by activationTime
  class CompareActivity implements Comparator<Activity> {
        @Override
        public int compare(Activity a1, Activity a2) {
            if(a1.getActivation()< a2.getActivation()){
            return -1;
            }
            if(a1.getActivation()< a2.getActivation()){
            return 1;
            }
            return 0;
        }
  }

  private String name;
  private ArrayList activities = new ArrayList();
  private double gain = 0.0;

  public Resource(String name) {
    this.name = name;
  }

  public String getName() { return name; }

  public void addActivity(Activity activity) {
    activities.add(activity);
  }

  public void addTask(Task task) {
    Iterator iterator = task.getActivities();
    Activity activity;
    while(iterator.hasNext())
      activities.add( (Activity) iterator.next() );
  }

  public void setGain(double gain) {
    this.gain = gain;
  }

  public void reset() {
    Iterator iterator = activities.iterator();
    Activity activity;
    while(iterator.hasNext()) {
      activity = (Activity) iterator.next();
      activity.reset();
    }
  }

  public void schedule() {
    // updates the activationTime of each activity
    for(int i=0; i < activities.size(); i++)
      ((Activity) activities.get(i)).update(gain);

    // sorts the list of activities for activationTime
    Collections.sort(activities, new CompareActivity());

    // evaluates the new completionTime of each activity
    Activity previous = (Activity) activities.get(0);
    for(int i=1; i < activities.size(); i++) {
      Activity activity = (Activity) activities.get(i);
      activity.serialize(previous);
      previous = activity;
    }
  }

  public double getPerformance() {
    double performance = 0.0;
    for(int i=0; i < activities.size(); i++) {
      Activity activity = (Activity) activities.get(i);
      performance += activity.getPerformance();
    }
    return performance;
  }

  public void store() {
    for(int i=0; i < activities.size(); i++) {
      Activity activity = (Activity) activities.get(i);
      activity.store();
    }
  }

  public void restore() {
    for(int i=0; i < activities.size(); i++) {
      Activity activity = (Activity) activities.get(i);
      activity.restore();
    }
  }

  public String toString() {
    String result = "\nResource " + name;
    for(int i=0; i < activities.size(); i++)
      result = result.concat("\n   "+((Activity) activities.get(i)).toString());
    return result;
  }
}
