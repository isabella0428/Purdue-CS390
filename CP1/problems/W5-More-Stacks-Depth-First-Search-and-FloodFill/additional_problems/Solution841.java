import java.util.List;
import java.util.HashSet;

class Solution {
    private HashSet<Integer> seen;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        seen = new HashSet<>();
        seen.add(0);

        List<Integer> room0 = rooms.get(0);
        for (int i : room0) {
            dfs(i, rooms);
        }
        return seen.size() == rooms.size();
    }

    private void dfs(int i, List<List<Integer>> rooms) {
        if (seen.contains(i))
            return;

        seen.add(i);
        for (int j : rooms.get(i)) {
            dfs(j, rooms);
        }
        return;
    }
}