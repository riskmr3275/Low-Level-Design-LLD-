import java.util.*;

interface ISubscriber {
    void update();
    String getName();
}

interface IChannel {
    void subscribe(ISubscriber subscriber);
    void unsubscribe(ISubscriber subscriber);
    String getVideoData();
}

class Channel implements IChannel {
    private List<ISubscriber> subscribers = new ArrayList<>();
    private String name;
    private String latestVideo;

    Channel(String name) {
        this.name = name;
        System.out.println(this.name + " created a YouTube channel ");
    }

    public void subscribe(ISubscriber subscriber) {
        subscribers.add(subscriber);
        System.out.println(subscriber.getName() + " subscribed to " + name);
    }

    public void unsubscribe(ISubscriber subscriber) {
        subscribers.remove(subscriber);
        System.out.println(subscriber.getName() + " unsubscribed from " + name);
    }

    public void uploadVideo(String title) {
        latestVideo = title;
        System.out.println("\n[" + name + " uploaded \"" + title + "\"]");
        notifySubscribers();
    }

    private void notifySubscribers() {
        for (ISubscriber sub : subscribers) {
            sub.update();
        }
    }

    public String getVideoData() {
        return "Check out our new video: " + latestVideo;
    }
}

class Subscriber implements ISubscriber {
    private String name;
    private IChannel channel;

    Subscriber(String name, IChannel channel) {
        this.name = name;
        this.channel = channel;
    }

    public void update() {
        System.out.println("Hey " + name + ", " + channel.getVideoData());
    }

    public String getName() {
        return name;
    }
}

public class ObserverDesignPatternSelf {
    public static void main(String[] args) {
        Channel channel = new Channel("CarryMinati");
        Channel channel2=new Channel("Hash-Beniwal");
        List<String> names = Arrays.asList(
            "Risu", "Rohit", "Suresh", "Rahul", "Vikas",
            "Ankit", "Neha", "Pooja", "Priya", "Karan"
        );
        List<Subscriber> persons = new ArrayList<>();

        for (int i = 0; i < names.size()/2; i++) {
            Subscriber s1 = new Subscriber(names.get(i), channel);
            Subscriber s2 = new Subscriber(names.get(i+names.size()/2), channel2);

            persons.add(s1);
            channel.subscribe(s1);
            channel2.subscribe(s2);

             
        }

        channel.uploadVideo("DSA Pattern Tutorial");
        channel2.uploadVideo("Daru With Dad Part-1");
        channel.unsubscribe(persons.get(1));

        channel.uploadVideo("Decorator Pattern Tutorial");
    }
}
