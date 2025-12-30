import java.util.*;

// Observer Interface
interface Observer {
    void update(Job job);
}

// Subject Interface
interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

// Job class
class Job {
    String title;
    String company;
    String location;

    Job(String title, String company, String location) {
        this.title = title;
        this.company = company;
        this.location = location;
    }
}

// Concrete Subject
class JobPortal implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private Job latestJob;

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void addNewJob(Job job) {
        this.latestJob = job;
        notifyObservers();
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(latestJob);
        }
    }
}

// Concrete Observer
class JobSeeker implements Observer {
    private String name;

    JobSeeker(String name) {
        this.name = name;
    }

    public void update(Job job) {
        System.out.println(
            name + " notified: " + job.title +
            " at " + job.company +
            " (" + job.location + ")"
        );
    }
}

// Main class
public class Jobalert {
    public static void main(String[] args) {
        JobPortal portal = new JobPortal();

        Observer s1 = new JobSeeker("Risu");
        Observer s2 = new JobSeeker("Amit");
        Observer s3=new JobSeeker("Priya");

        portal.registerObserver(s1);
        portal.registerObserver(s2);
        portal.registerObserver(s3);

        portal.addNewJob(
            new Job("Backend Developer", "Amazon", "Bangalore")
        );
        portal.addNewJob(
            new Job("SDE Intern", "RedHat", "Bangalore")
        );
    }
}
