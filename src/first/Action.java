package first;

import static java.lang.Integer.parseInt;

public class Action {
    Integer id; // a value between 1 and 10
    public Action(Integer id) {
        this.id = id;
    }
    public Action(String id) {
        this.id = parseInt(id);
    }
    public Integer getId() {
        return this.getId();
    }
}

class ActionHistory {
    private Action[] actions; // a maximum of 25 actions at a time
    private Integer effectiveActions = 0;
    public ActionHistory() {
        actions = new Action[25];
        // each one is null, for now
    }
    public void addAction(Integer id) {
        actions[effectiveActions] = new Action(id);
        effectiveActions++;
    }
    public void addAction(String id) {
        actions[effectiveActions] = new Action(id);
        effectiveActions++;
    }
    public String previousAction() {
        return actions[actions.length - 1].getId().toString();
    }
    public void show() {
        StringBuilder sb = new StringBuilder("\n");
        Integer max, max2, used[];
        used = new Integer[10];
        for (int i = 0; i < actions.length; i++)
            used[actions[i].getId()]++;
        max = Math.max(used[0], used[1]);
        max2 = Math.min(used[0], used[1]);
        for (int i = 2; i < 10; i++)
            if (used[i] > max) {
                max2 = max;
                max = used[i];
            } else if (used[i] > max2) {
                max2 = used[i];
            }
        sb.append("The most commons actions were ");
        sb.append(max);
        sb.append(", then ");
        sb.append(max2);
        sb.append("\nCongrats on rushing this project from the first stage!!!");
        System.out.println(sb.toString());
    }
}