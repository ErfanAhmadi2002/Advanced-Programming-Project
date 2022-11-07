public interface BotFunctions {
    String[] handleRequest(String command , int senderId , int groupId);
    /*
    the structure of String Array answer :
    first element:
        introduces the type of the answer;
        0 : send nothing
        1 : message in Pv
        2 : message in Group
        3 : tweet
        4 : comment
    second element:
        introduces the receivers :
        1 : receiver(s) Id
        2 : group Id
        3 : 0
        4 : tweet Id
    third element:
        introduces the text
     */
}
