package HelloSpringMVC.util;


public class CreateNewUserId {
    public static String getNewUserId(String id) {   //生成新的id

        String today = TimestampUtil.getTimestamp();
        if (id == null) {
            id = today + "000001";
            return id;
        } else {
            if (today.substring(2, 4).equals(id.substring(2, 4))) {
                String num = id.substring(4);
                int i = Integer.parseInt(num);
                i++;
                String nid = String.valueOf(i);
                i = 6 - nid.length();
                for (int j = 0; j < i; j++) {
                    nid = "0" + nid;
                }
                nid = today + nid;
                return nid;
            } else {
                return today + "000001";
            }
        }
    }
}
