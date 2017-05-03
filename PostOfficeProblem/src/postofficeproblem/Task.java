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
public class Task {
  private ArrayList activities = new ArrayList();

  public Task() { }
  
  public int addActivity(Activity activity) {
    activity.setID(activities.size());
    activities.add(activity);
    
    return activity.getID();
  }
  public Activity getActivity(int id) {
    Iterator iterator = activities.iterator();
    Activity activity;
    while(iterator.hasNext()) {
      activity = (Activity) iterator.next();
      if(activity.getID() == id)
        return activity;
    }
    return null;
  }
  
  public Iterator getActivities() { return activities.iterator();}
}
