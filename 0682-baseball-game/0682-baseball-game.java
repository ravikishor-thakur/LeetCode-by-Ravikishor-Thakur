class Solution {
    public int calPoints(String[] operations) {
        Stack<Integer> st = new Stack<>();

        for (String op : operations) {
            if (op.equals("+")) {
                int a = st.pop();
                int b = st.peek();
                st.push(a);
                st.push(a + b);
            } else if (op.equals("D")) {
                st.push(st.peek() * 2);
            } else if (op.equals("C")) {
                st.pop();
            } else {
                st.push(Integer.parseInt(op));
            }
        }

        int sum = 0;
        while (!st.isEmpty())
            sum += st.pop();

        return sum;
    }
}