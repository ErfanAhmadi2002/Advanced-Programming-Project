import java.util.HashMap;

public class Calculator implements BotFunctions {

    public Calculator() {
    }

    @Override
    public String[] handleRequest(String command, int senderId , int groupId) {
        String[] response = new String[4];
        response[0] = "1";
        response[1] = String.valueOf(senderId);
        response[2] = combined(command);
        return response;
    }

    private int powFinder(String A) {
        boolean t = true;
        int k = 0;
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == 'x') {
                t = true;
                k = i;
                break;
            } else {
                t = false;
            }
        }
        if (t) {
            if (k == A.length() - 1) {
                return 1;
            } else {
                return Integer.parseInt(A.substring(k + 2));
            }
        } else {
            return 0;
        }
    }

    private int coefficientFind(String A) {
        boolean t = false;
        int k = 0;
        int k2 = 10000000;
        boolean e = false;
        boolean e2 = true;
        boolean e3 = true;
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == 'x') {
                t = true;
                k = i;
                break;
            } else {
                t = false;
            }
        }
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == '^') {
                e = true;
                k2 = i;
                break;
            }
        }
        if (e && t && k2 > k && k != 0) {
            if (k == 1) {
                if (A.charAt(0) == '+') {
                    return 1;
                }
                if (A.charAt(0) == '-') {
                    return -1;
                }
            }
            e3 = false;
            return Integer.parseInt(A.substring(0, k));
        }
        if (A.charAt(0) == '-') {
            if (k == 1) {
                e2 = false;
                return -1;
            }
            if (!t && !e) {
                e2 = false;
                return Integer.parseInt(A);
            }
            if (!t && e) {
                e2 = false;
                return (int) -Math.pow(Integer.parseInt(A.substring(1, k2)), Integer.parseInt(A.substring(k2 + 1)));
            }
            if (t && e && e3) {
                e2 = false;
                return (int) -Math.pow(Integer.parseInt(A.substring(1, k2)), Integer.parseInt(A.substring(k2 + 1, k)));
            }
            if (e2) {
                return Integer.parseInt(A.substring(0, k));
            }
        } else {
            if (k == 0 && t) {
                e2 = false;
                return 1;
            }
            if (k == 1 && A.charAt(0) == '+') {
                return 1;
            }
            if (!t && !e) {
                e2 = false;
                return Integer.parseInt(A);
            }
            if (!t && e) {
                e2 = false;
                return (int) Math.pow(Integer.parseInt(A.substring(0, k2)), Integer.parseInt(A.substring(k2 + 1)));
            }
            if (t && e && e3) {
                e2 = false;
                return (int) Math.pow(Integer.parseInt(A.substring(0, k2)), Integer.parseInt(A.substring(k2 + 1, k)));
            }
            if (e2) {
                return Integer.parseInt(A.substring(0, k));
            }
        }
        return 0;
    }

    private HashMap<Integer, Integer> polynomial(String A, HashMap<Integer, Integer> map) {
        A = standardParentheses(A);
        A = priorityParentheses(A);
        int j;
        int i2 = A.length();
        for (int i = A.length() - 1; i >= 0; i--) {
            if (A.charAt(i) == '+' || A.charAt(i) == '-') {
                int pow = powFinder(A.substring(i, i2));
                int coef = coefficientFind(A.substring(i, i2));
                if (map.containsKey(pow)) {
                    j = map.get(pow);
                    map.replace(pow, j + coef);
                } else {
                    map.put(pow, coef);
                }
                i2 = i;
            } else {
                if (i == 0) {
                    int pow = powFinder(A.substring(i, i2));
                    int coef = coefficientFind(A.substring(i, i2));
                    if (map.containsKey(pow)) {
                        j = map.get(pow);
                        map.replace(pow, j + coef);
                    } else {
                        map.put(pow, coef);
                    }
                }
            }
        }
        return map;
    }

    private String mapToStr(HashMap<Integer, Integer> map) {
        StringBuilder R = new StringBuilder();
        boolean empty = true;
        for (Integer i : map.keySet()) {
            if (map.get(i) > 0) {
                if (map.get(i) == 1) {
                    if (i == 0 || i == 1) {
                        if (i == 0) {
                            R.insert(0, "+" + map.get(i));
                        } else {
                            R.insert(0, "+" + "x");
                        }
                    } else {
                        R.insert(0, "+" + "x" + "^" + i);
                    }
                } else {
                    if (i == 0 || i == 1) {
                        if (i == 0) {
                            R.insert(0, "+" + map.get(i));
                        } else {
                            R.insert(0, "+" + map.get(i) + "x");
                        }
                    } else {
                        R.insert(0, "+" + map.get(i) + "x" + "^" + i);
                    }
                }
            }
            if (map.get(i) < 0) {
                if (map.get(i) == -1) {
                    if (i == 0 || i == 1) {
                        if (i == 0) {
                            R.insert(0, map.get(i));
                        } else {
                            R.insert(0, "-" + "x");
                        }
                    } else {
                        R.insert(0, "-" + "x" + "^" + i);
                    }
                } else {
                    if (i == 0 || i == 1) {
                        if (i == 0) {
                            R.insert(0, map.get(i));
                        } else {
                            R.insert(0, map.get(i) + "x");
                        }
                    } else {
                        R.insert(0, map.get(i) + "x" + "^" + i);
                    }
                }
            }
            if (map.get(i) == 0) {
            }
        }
        for (Integer i : map.keySet()) {
            if (map.get(i) != 0) {
                empty = false;
            }
        }
        if (empty) {
            R.append(0);
        }
        String R2 = R.toString();
        return R2;
    }

    private String priorityParentheses(String A) {
        A = standardParentheses(A);
        StringBuilder A1 = new StringBuilder();
        A1.append(A);
        int open = 0;
        int count = 1;
        int close = 0;
        boolean t = false;
        boolean t2 = true;
        for (int i = 0; i < A1.length(); i++) {
            if (A1.charAt(i) == '(') {
                open = i;
                t = true;
                break;
            }
        }
        if (t) {
            for (int i = (open + 1); i < A1.length(); i++) {
                if (A1.charAt(i) == '(') {
                    count++;
                }
                if (A1.charAt(i) == ')') {
                    count--;
                }
                if (count == 0) {
                    close = i;
                    break;
                }
            }
            if (A1.charAt(open - 1) == '+') {
                A1.deleteCharAt(close);
                A1.replace(open - 1, open + 1, "");
                t2 = false;
            }
            if (t2 && A1.charAt(open - 1) == '-') {
                A1.deleteCharAt(close);
                A1.replace(open - 1, open + 1, "");
                String A2 = A1.substring(open - 1, close - 2);
                HashMap<Integer, Integer> map = new HashMap<>();
                map = polynomial(A2, map);
                for (int i : map.keySet()) {
                    map.replace(i, -map.get(i));
                }
                A2 = mapToStr(map);
                A1.replace(open - 1, close - 2, A2);
            }
            String A2 = A1.toString();
            A = priorityParentheses(A2);
        }
        return A;
    }

    private String standardParentheses(String A) {
        StringBuilder A2 = new StringBuilder();
        A2.append(A);
        boolean t = true;
        for (int i = 0; i < A2.length(); i++) {
            if (A2.charAt(i) == '(') {
                if (i == 0) {
                    A2.insert(0, "+0+");
                    i = i + 3;
                } else {
                    if (A2.charAt(i - 1) == '-') {
                        t = false;
                    }
                    if (A2.charAt(i - 1) == '+') {
                        A2.insert(i, "0+");
                        t = false;
                        i = i + 2;
                    }
                    if (t) {
                        A2.insert(i, "+0+");
                        i = i + 3;
                    }
                    t = true;
                }
            }
            if (A2.charAt(i) == '1' || A2.charAt(i) == '2' || A2.charAt(i) == '3' || A2.charAt(i) == '4' || A2.charAt(i) == '5' || A2.charAt(i) == '6' || A2.charAt(i) == '7' || A2.charAt(i) == '8' || A2.charAt(i) == '9' || A2.charAt(i) == 'x') {
                if (i != 0 && A2.charAt(i - 1) == '(') {
                    A2.insert(i, "+0+");
                    i = i + 3;
                }
            }
        }
        A = A2.toString();
        return A;
    }

    private String multiplyParentheses(String A) {
        StringBuilder A1 = new StringBuilder();
        A1.append(A);
        int open1 = 0;
        int close1 = 0;
        int open2 = 0;
        int close2 = 0;
        int count1 = 1;
        int count2 = 1;
        boolean t = false;
        for (int i = 0; i < A1.length() - 1; i++) {
            if (i != A1.length() && A1.charAt(i) == ')' && A1.charAt(i + 1) == '(') {
                t = true;
                close1 = i;
                break;
            }
        }
        if (t) {
            open2 = close1 + 1;
            for (int i = (open2 + 1); i < A1.length(); i++) {
                if (A1.charAt(i) == '(') {
                    count1++;
                }
                if (A1.charAt(i) == ')') {
                    count1--;
                }
                if (count1 == 0) {
                    close2 = i;
                    break;
                }
            }
            for (int i = (close1 - 1); i >= 0; i--) {
                if (A1.charAt(i) == ')') {
                    count2++;
                }
                if (A1.charAt(i) == '(') {
                    count2--;
                }
                if (count2 == 0) {
                    open1 = i;
                    break;
                }
            }
            String firstpar = new String();
            String secondpar = new String();
            StringBuilder A2 = new StringBuilder();
            if (close1 == 0) {
            } else {
                A2.append(A1.substring(0, open1));
            }
            firstpar = combined(A1.substring(open1 + 1, close1));
            secondpar = combined(A1.substring(open2 + 1, close2));
            HashMap<Integer, Integer> map1 = new HashMap<>();
            HashMap<Integer, Integer> map2 = new HashMap<>();
            HashMap<Integer, Integer> mapres = new HashMap<>();
            map1 = polynomial(firstpar, map1);
            map2 = polynomial(secondpar, map2);
            for (int i : map1.keySet()) {
                for (int j : map2.keySet()) {
                    int key = i + j;
                    int value = map1.get(i) * map2.get(j);
                    if (mapres.containsKey(key)) {
                        mapres.replace(key, mapres.get(key) + value);
                    } else {
                        mapres.put(key, value);
                    }
                }
            }
            String res = mapToStr(mapres);
            A2.append('(');
            A2.append(res);
            A2.append(')');
            if (close2 == A1.length()) {
            } else {
                A2.append(A1.substring(close2 + 1));
            }
            A1 = A2;
            A = A1.toString();
            A = multiplyParentheses(A);
        }
        return A;
    }

    private String hashtagEditor(String A) {
        StringBuilder A1 = new StringBuilder();
        A1.append(A);
        int open1 = 0;
        int close1 = 0;
        int open2 = 0;
        int close2 = 0;
        int count1 = 1;
        int count2 = 1;
        boolean t = false;
        for (int i = 0; i < A1.length(); i++) {
            if (A1.charAt(i) == '#') {
                t = true;
                close1 = i - 1;
                break;
            }
        }
        if (t) {
            open2 = close1 + 2;
            for (int i = (open2 + 1); i < A1.length(); i++) {
                if (A1.charAt(i) == '(') {
                    count1++;
                }
                if (A1.charAt(i) == ')') {
                    count1--;
                }
                if (count1 == 0) {
                    close2 = i;
                    break;
                }
            }
            for (int i = (close1 - 1); i >= 0; i--) {
                if (A1.charAt(i) == ')') {
                    count2++;
                }
                if (A1.charAt(i) == '(') {
                    count2--;
                }
                if (count2 == 0) {
                    open1 = i;
                    break;
                }
            }
            String firstpar = new String();
            String secondpar = new String();
            StringBuilder A2 = new StringBuilder();
            if (close1 == 0) {
            } else {
                A2.append(A1.substring(0, open1));
            }
            firstpar = combined(A1.substring(open1 + 1, close1));
            secondpar = combined(A1.substring(open2 + 1, close2));
            HashMap<Integer, Integer> map1 = new HashMap<>();
            HashMap<Integer, Integer> map2 = new HashMap<>();
            HashMap<Integer, Integer> mapres = new HashMap<>();
            map1 = polynomial(firstpar, map1);
            map2 = polynomial(secondpar, map2);
            for (int i : map1.keySet()) {
                for (int j : map2.keySet()) {
                    if (i == j) {
                        int value = map1.get(i) * map2.get(j);
                        mapres.put(i, value);
                    }
                }
            }
            String res = mapToStr(mapres);
            A2.append('(');
            A2.append(res);
            A2.append(')');
            if (close2 == A1.length()) {
            } else {
                A2.append(A1.substring(close2 + 1));
            }
            A1 = A2;
            A = A1.toString();
            A = hashtagEditor(A);
        }
        return A;
    }

    public String combined(String A) {
        try {
            A = hashtagEditor(A);
            A = multiplyParentheses(A);
            A = priorityParentheses(A);
            HashMap<Integer, Integer> map = new HashMap<>();
            map = polynomial(A, map);
            String A2 = mapToStr(map);
            return A2;
        } catch (Throwable throwable) {
            return "Wrong Format";
        }
    }

}
