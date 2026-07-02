class Solution {

    private final int[][] directions = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {

        int curHealth = health - grid.get(0).get(0);

        if (curHealth <= 0) {
            return false;
        }

        int rows = grid.size();
        int cols = grid.get(0).size();

        // Max Heap based on remaining health
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);

        int[][] bestHealth = new int[rows][cols];

        for (int[] row : bestHealth) {
            Arrays.fill(row, -1);
        }

        bestHealth[0][0] = curHealth;
        pq.offer(new int[]{0, 0, curHealth});

        while (!pq.isEmpty()) {

            int[] current = pq.poll();

            int row = current[0];
            int col = current[1];
            int remainingHealth = current[2];

            if (row == rows - 1 && col == cols - 1) {
                return true;
            }

            if (remainingHealth < bestHealth[row][col]) {
                continue;
            }

            for (int[] direction : directions) {

                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (newRow < 0 || newCol < 0 || newRow >= rows || newCol >= cols) {
                    continue;
                }

                int newHealth = remainingHealth - grid.get(newRow).get(newCol);

                if (newHealth <= 0) {
                    continue;
                }

                if (newHealth > bestHealth[newRow][newCol]) {

                    bestHealth[newRow][newCol] = newHealth;

                    pq.offer(new int[]{newRow, newCol, newHealth});
                }
            }
        }

        return false;
    }
}