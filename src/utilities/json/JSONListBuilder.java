package utilities.json;

import fileIO.writers.JSONWritable;
import utilities.queue.implementations.JSONEntryQueue;

public class JSONListBuilder {
    private final JSONEntryQueue queue;
    
    protected JSONListBuilder(){
        queue = new JSONEntryQueue();
    }

    public void addNumbers(Number... numbers){
        for (Number n : numbers) {
            queue.enqueue(new JSONEntry(n.toString()));
        }
    }
    public void addBooleans(Boolean... booleans){
        for (Boolean bool : booleans) {
            queue.enqueue(new JSONEntry(bool.toString()));
        }
    }
    public void addStrings(String... strings){
        for (String s : strings){
            queue.enqueue(new JSONEntry('"' + s + '"'));
        }
    }
    public void addObjects(JSONWritable... objects){
        for (JSONWritable o : objects){
            JSONBuilder b = new JSONBuilder();
            o.writeJSON(b);
            queue.enqueue(b.toEntry());
        }
    }

    protected JSONEntry toEntry(){
        StringBuilder b = new StringBuilder();
        b.append("[");
        while (queue.isEmpty()){
            b.append(queue.dequeue());
            if(!queue.isEmpty())
                b.append(",");
            b.append("\n");
        }
        b.append("]");

        return new JSONEntry(b.toString());
    }
}
