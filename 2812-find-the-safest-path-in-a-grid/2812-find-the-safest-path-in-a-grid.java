class Solution {

    int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {

        int n = grid.size();

        int[][] dist = new int[n][n];
        for (int[] row : dist) Arrays.fill(row, -1);

        Queue<int[]> queue = new LinkedList<>();

        // Multi-Source BFS
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    queue.offer(new int[]{i, j});
                    dist[i][j] = 0;
                }
            }
        }

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int row = current[0];
            int col = current[1];

            for (int[] dir : directions) {

                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow < 0 || newCol < 0 || newRow >= n || newCol >= n)
                    continue;

                if (dist[newRow][newCol] != -1)
                    continue;

                dist[newRow][newCol] = dist[row][col] + 1;
                queue.offer(new int[]{newRow, newCol});
            }
        }

        PriorityQueue<int[]> pq =
                new PriorityQueue<>((a, b) -> b[0] - a[0]);

        boolean[][] visited = new boolean[n][n];

        pq.offer(new int[]{dist[0][0], 0, 0});

        while (!pq.isEmpty()) {

            int[] current = pq.poll();

            int safe = current[0];
            int row = current[1];
            int col = current[2];

            if (visited[row][col])
                continue;

            visited[row][col] = true;

            if (row == n - 1 && col == n - 1)
                return safe;

            for (int[] dir : directions) {

                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow < 0 || newCol < 0 || newRow >= n || newCol >= n)
                    continue;

                if (visited[newRow][newCol])
                    continue;

                int newSafe = Math.min(safe, dist[newRow][newCol]);

                pq.offer(new int[]{newSafe, newRow, newCol});
            }
        }

        return 0;
    }
}