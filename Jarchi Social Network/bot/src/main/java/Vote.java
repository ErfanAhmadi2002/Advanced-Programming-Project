import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Vote implements BotFunctions{
    /* Vote Commands :
    /newPoll : starts a new poll in group with this structure: question + "  " + first Item + "  " second Item + ...
    /vote : if there was a poll with in group then the user would vote for it
    /getResult : if there was a poll with in group then you can see the result so far
    /finishPoll : shows the final result and removes the poll
    /help : send command tutorial
     */
    private final HashMap<Integer , Poll> allPolls;

    public Vote() {
        this.allPolls = new HashMap<>();
    }

    @Override
    public String[] handleRequest(String command, int senderId , int groupId) {
        if (command.startsWith("/newPoll")) {
            return handleNewPollCommand(command, senderId , groupId);
        } else if (command.startsWith("/vote")) {
            return handleVoteCommand(command, senderId , groupId);
        } else if (command.startsWith("/getResult")) {
            return handleGetResultCommand(command, senderId , groupId);
        } else if (command.startsWith("/finishPoll")) {
            return handleFinishPollCommand(command, senderId , groupId);
        } else if (command.startsWith("/help")) {
            return handleHelpCommand(command, senderId , groupId);
        }
        return new String[]{"0", "0", "0"};
    }

    private String[] handleNewPollCommand (String command, int senderId , int groupId){
        command = command.substring(9);
        int index = command.indexOf("  ");
        String question = command.substring(0 , index);
        command = command.substring(index+1);
        ArrayList<String> options = new ArrayList<>();
        while (command.length() > 0){
            index = command.indexOf("  ");
            if (index == -1){
                options.add(command);
                break;
            }
            String option = command.substring(0 , index);
            command = command.substring(index+2);
            options.add(option);
        }
        Poll poll = new Poll(question , options);
        allPolls.put(groupId , poll);
        return new String[]{"2" , String.valueOf(groupId) , poll.print()};
    }

    private String[] handleVoteCommand (String command, int senderId , int groupId){
        if (allPolls.containsKey(groupId)) {
            Poll poll = allPolls.get(groupId);
            int index = Integer.parseInt(command.substring(6));
            poll.vote(senderId , index);
            return new String[]{"2" , String.valueOf(groupId) , poll.print()};
        }
        return new String[]{"0", "0", "0"};
    }

    private String[] handleGetResultCommand (String command, int senderId , int groupId){
        if (allPolls.containsKey(groupId)) {
            Poll poll = allPolls.get(groupId);
            return new String[]{"2" , String.valueOf(groupId) , poll.print()};
        }
        return new String[]{"0", "0", "0"};
    }

    private String[] handleFinishPollCommand (String command, int senderId , int groupId){
        if (allPolls.containsKey(groupId)) {
            Poll poll = allPolls.get(groupId);
            String text = poll.print();
            allPolls.remove(groupId);
            return new String[]{"2" , String.valueOf(groupId) , text};
        }
        return new String[]{"0", "0", "0"};
    }

    private String[] handleHelpCommand (String command, int senderId , int groupId){
        String text = "/newPoll : starts a new poll in group with this structure: question + \"  \" + first Item + \"  \" second Item + ... \n" +
                "/vote : if there was a poll with in group then the user would vote for it \n" +
                "/getResult : if there was a poll with in group then you can see the result so far \n" +
                "/finishPoll : shows the final result and removes the poll \n" +
                "/help : send command tutorial";
        return new String[]{"2", String.valueOf(groupId), text};
    }

    private static class Poll {
        private String question;
        private HashMap<String, HashSet<Integer>> votes;
        private ArrayList<String> options;

        public Poll(String question, ArrayList<String> options) {
            this.question = question;
            this.options = options;
            votes = new HashMap<>();
            for(String option: options) votes.put(option,new HashSet<>());
        }

        public String getQuestion() {
            return question;
        }
        public HashMap<String, HashSet<Integer>> getVotes() {
            return votes;
        }
        public ArrayList<String> getOptions() {
            return options;
        }

        public void setQuestion(String question) {
            this.question = question;
        }
        public void setVotes(HashMap<String, HashSet<Integer>> votes) {
            this.votes = votes;
        }
        public void setOptions(ArrayList<String> options) {
            this.options = options;
        }

        public void vote(int id, int index){
            remove(id);
            String option = options.get(index);
            votes.get(option).add(id);
        }

        public void remove(int id){
            for(Map.Entry<String,HashSet<Integer>> m: votes.entrySet()) {
                m.getValue().remove(id);
            }
        }

        public String print(){
            int totalCount=0;
            for(Map.Entry<String,HashSet<Integer>> m: votes.entrySet()) totalCount+=m.getValue().size();
            StringBuilder answer= new StringBuilder();
            answer.append(question).append("\n");
            for(Map.Entry<String,HashSet<Integer>> m: votes.entrySet()){
                answer.append("  ").append(m.getKey()).append(": ");
                if(m.getValue().size()==0) answer.append("0%");
                else answer.append(m.getValue().size() * 100 / totalCount).append("%");
            }
            return answer.toString();
        }
    }
}
