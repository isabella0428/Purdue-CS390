class Solution11 {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int vol = 0;
        while (l < r) {
            int l_h = height[l];
            int r_h = height[r];
            int h = l_h;
            if (r_h < h)
                h = r_h;
            int new_vol = (r - l) * h;
            if (new_vol > vol)
                vol = new_vol;
            
            while(height[l] <= h && l < r) ++l;
            while(height[r] <= h && r > l) --r;
        }
        return vol;
    }
}