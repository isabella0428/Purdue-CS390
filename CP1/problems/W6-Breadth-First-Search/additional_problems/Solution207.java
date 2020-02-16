import java.util.ArrayDeque;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int matrix[][] =  new int[numCourses][numCourses];
        int inDegree[] = new int[numCourses];
        for (int i = 0; i < prerequisites.length; ++i) {
            int cur = prerequisites[i][0];
            int next = prerequisites[i][1];

            if (matrix[next][cur] == 0) {
                matrix[next][cur] = 1;
            }
            ++inDegree[cur];
        }

        int courseTaken = 0;
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        for (int i = 0; i < numCourses; ++i) {
            if (inDegree[i] == 0) {
                ad.addLast(i);
            }
        }

        while (ad.size() > 0) {
            int cur = ad.removeFirst();
            ++courseTaken;

            for(int i = 0; i < numCourses; ++i) {
                if (matrix[cur][i] == 1 && --inDegree[i] == 0) {
                    ad.addLast(i);
                }
            }
        }

        return courseTaken == numCourses;
    }
}